package userInterface.stages.steamDir;

import backupHandler.FileBackup;
import dirWatcher.Watcher;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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
    private Label watcherLabel;
    private Button btnChange;
    private Button btnBackup;
    private Button btnAttach;

    private CheckBox cbxAutoBak;
    public boolean autoBak;

    private SaveDetector saveDetector;

    private FileBackup fileBackup;

    private Path saveDir;

    private Watcher watcher;


    public OptionsWindow(FileBackup fileBackup, Watcher watcher){
        this.watcher = watcher;
        stage = new Stage();
        this.fileBackup = fileBackup;
        saveDetector = new SaveDetector(stage);

        saveDir = saveDetector.getGameSaveDirProp();
        if(saveDetector.isValid(saveDir)) {
            if (saveDir != null) {
                fileBackup.setSrcPath(saveDir);
            }
        } else {
            saveDir = null;
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

        watcherLabel = new Label("Not attached");

        btnChange = new Button("Change...");
        addBtnChangeHandler();

        btnAttach = new Button ("Attach");
        addBtnAttachHandler();

        btnBackup = new Button("Backup savefile");
        btnBackup.disableProperty().setValue(true);
        if(saveDir != null) {
            dirLabel.setText(saveDir.toString());
            watcherLabel.setText("Watcher dir: \n" + saveDir.toString());
            changeStatus(true);
            watcher.register(saveDir);
        }else{
            changeStatus(false);
        }
        addBtnBackupHandler();

        cbxAutoBak = new CheckBox("AutoBackup");
        cbxAutoBakDisabled(false);
        cbxAutoBak.setTooltip(new Tooltip("When checked, backups of savefile are automatically made when game Save File changes. Works only when AutoDetect Set is checked (e.g. Savefile is being watched"));
        addCbxAutoBakHandler();

        layout.getChildren().addAll(dirLabel, btnChange, statusLabel, btnBackup, cbxAutoBak, watcherLabel, btnAttach);

    }

    private void setDirString(String s){
        this.dirLabel.setText(s);
        this.watcherLabel.setText("Watcher dir: \n" + s);
    }

    private void addBtnChangeHandler(){
        this.btnChange.setOnAction(event -> {
            saveDetector.show();
            saveDir = saveDetector.getGameSaveDir();
            if(saveDir != null) {
                fileBackup.setSrcPath(saveDir);
                setDirString(saveDir.toString());
                changeStatus(true);
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

    private void addBtnAttachHandler(){
        btnAttach.setOnAction(event -> {
            watcher.register(saveDir);
        });
    }

    private void addCbxAutoBakHandler(){
        cbxAutoBak.selectedProperty().addListener((observable, oldValue, newValue) -> {
            autoBak = newValue;
        });
    }


    private void changeStatus(boolean status){
        if(status){
            btnBackup.disableProperty().setValue(false);
            btnAttach.disableProperty().setValue(false);
            statusLabel.setText("Found");
        } else {
            statusLabel.setText("Not Found");
            btnBackup.disableProperty().setValue(true);
            btnAttach.disableProperty().setValue(true);
        }
    }

    public boolean isAutoBak() {
        return autoBak;
    }

    public void cbxAutoBakDisabled(boolean b){
        cbxAutoBak.setDisable(!b);
    }
    public void cbxAutoBakUnselect(){
        cbxAutoBak.setSelected(false);
        autoBak = false;
    }
}
