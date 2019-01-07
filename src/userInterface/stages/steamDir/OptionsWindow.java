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

    public OptionsWindow(){
        fileBackup = new FileBackup();
    }

    public Stage getStage(){
        return this.stage;
    }

    public void build(){
        stage = new Stage();
        layout = new VBox();
        layout.setSpacing(5);
        layout.setPadding(new Insets(15, 15, 15, 15));
        scene = new Scene(layout, 600, 300);
        stage.setScene(scene);

        dirLabel = new Label("Empty");
        statusLabel = new Label("Not detected");

        btnChange = new Button("Change...");
        addbtnChangeHandler();

        btnBackup = new Button("Backup savefile");
        btnBackup.disableProperty().setValue(true);
        addBtnBackupHandler();

        layout.getChildren().addAll(dirLabel, btnChange, statusLabel, btnBackup);

        saveDetector = new SaveDetector(stage);
    }

    private void setDirString(String s){
        this.dirLabel.setText(s);
    }

    private void addbtnChangeHandler(){
        this.btnChange.setOnAction(event -> {
            saveDetector.show();
            if(saveDetector.getGameSaveDir() != null) {
                Path saveDir = saveDetector.getGameSaveDir();
                setDirString(saveDir.toString());
                changeStatus(true);
                fileBackup.setSrcPath(saveDir);
                fileBackup.setBackupPath(Utility.defBakPath);
            } else {
                changeStatus(false);
            }

        });
    }

    private void addBtnBackupHandler(){
        this.btnBackup.setOnAction(event -> {
            try {
                fileBackup.backup();
            } catch ( IOException e){
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
