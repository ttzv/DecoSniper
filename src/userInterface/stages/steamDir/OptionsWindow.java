package userInterface.stages.steamDir;

import backupHandler.FileBackup;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utility.Utility;

import java.io.IOException;
import java.nio.file.Path;


public class OptionsWindow {
    private Stage stage;
    private Scene scene;
    private VBox layout;
    private Label dirLabel;
    private Label statusLabel;
    private Button btnChange;
    private Button btnBackup;

    private SaveDetector saveDetector;

    private FileBackup fileBackup;

    private Path saveDir;



    public OptionsWindow(){
        stage = new Stage();
        fileBackup = new FileBackup();
        saveDetector = new SaveDetector(stage);

        saveDir = saveDetector.getGameSaveDirProp();
        if(saveDir != null){
            fileBackup.setSrcPath(saveDir);
        }

        fileBackup.setBackupPath(Utility.defBakPath);
    }

    public Stage getStage(){
        return this.stage;
    }

    public void build(){
        layout = new VBox();
        layout.setSpacing(5);
        layout.setPadding(new Insets(15, 15, 15, 15));
        scene = new Scene(layout, 600, 300);
        stage.setScene(scene);

        dirLabel = new Label("Empty");

        statusLabel = new Label("Not detected");

        btnChange = new Button("Change...");
        addBtnChangeHandler();

        btnBackup = new Button("Backup savefile");
        btnBackup.disableProperty().setValue(true);
        if(saveDir != null) {
            dirLabel.setText(saveDir.toString());
            changeStatus(true);
        }else{
            changeStatus(false);
        }
        addBtnBackupHandler();

        layout.getChildren().addAll(dirLabel, btnChange, statusLabel, btnBackup);


    }

    private void setDirString(String s){
        this.dirLabel.setText(s);
    }

    private void addBtnChangeHandler(){
        this.btnChange.setOnAction(event -> {
            saveDetector.show();
            Path saveDir = saveDetector.getGameSaveDir();
            if(saveDir != null) {
                setDirString(saveDir.toString());
                changeStatus(true);
                fileBackup.setSrcPath(saveDir);
            } else {
                changeStatus(false);
            }

        });
    }

    private void addBtnBackupHandler(){
        this.btnBackup.setOnAction(event -> {
            try {
                fileBackup.backup();
            } catch ( IOException e ){
                e.printStackTrace();
            }
        });
    }

    private void changeStatus(boolean status){
        if(status){
            btnBackup.disableProperty().setValue(false);
            statusLabel.setText("Found");
        } else {
            statusLabel.setText("Not Found");
            btnBackup.disableProperty().setValue(true);
        }
    }








}
