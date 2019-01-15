package dirWatcher;

import backupHandler.FileBackup;
import javafx.application.Platform;
import javafx.scene.control.Button;
import logic.decoRecord.DecoRecord;
import userInterface.decoListPanes.DecoListContainer;
import userInterface.stages.steamDir.OptionsWindow;
import userInterface.statusBar.StatusBar;

import java.io.IOException;


/**
 * Custom listener for adding next setVanishingText and refreshing ui
 */
public class WatcherResponder implements WatcherListener{


    private StatusBar statusBar;
    private DecoRecord decoRecord;
    private Button b1;
    private Button b2;
    private Button b3;
    private DecoListContainer decoListContainer;
    private FileBackup fileBackup;
    private OptionsWindow optionsWindow;
    private Boolean autoBackup;

    public WatcherResponder(StatusBar statusBar, DecoRecord decoRecord, Button b1, Button b2, Button b3, DecoListContainer decoListContainer, FileBackup fileBackup, OptionsWindow optionsWindow){

        this.statusBar = statusBar;
        this.decoRecord = decoRecord;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.decoListContainer = decoListContainer;
        this.fileBackup = fileBackup;
        this.optionsWindow = optionsWindow;
    }
    @Override
    public void actionPerformed() {
        platformfx();
    }

    private void platformfx(){
        Platform.runLater(() -> {
            statusBar.clear();
            decoRecord.nextSet();
            b1.setText("");
            b2.setText("");
            b3.setText("");
            b1.setGraphic(null);
            b2.setGraphic(null);
            b3.setGraphic(null);
            decoListContainer.resetFocusedProperty();
            decoListContainer.updateRecord();
            if(optionsWindow.isAutoBak()){
                try {
                    fileBackup.backup();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
