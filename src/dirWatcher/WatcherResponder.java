package dirWatcher;

import javafx.application.Platform;
import javafx.scene.control.Button;
import logic.decoRecord.DecoRecord;
import sun.font.Decoration;
import userInterface.decoListPanes.DecoListContainer;
import userInterface.statusBar.StatusBar;


/**
 * Custom listener for adding next set and refreshing ui
 */
public class WatcherResponder implements WatcherListener{


    private StatusBar statusBar;
    private DecoRecord decoRecord;
    private Button b1;
    private Button b2;
    private Button b3;
    private DecoListContainer decoListContainer;

    public WatcherResponder(StatusBar statusBar, DecoRecord decoRecord, Button b1, Button b2, Button b3, DecoListContainer decoListContainer){

        this.statusBar = statusBar;
        this.decoRecord = decoRecord;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.decoListContainer = decoListContainer;
    }
    @Override
    public void actionPerformed() {
        platformfx();
    }

    private void platformfx(){
        Platform.runLater(() -> {
            statusBar.clear();
            decoRecord.nextSet();
            b1.setText("1");
            b2.setText("2");
            b3.setText("3");
            decoListContainer.resetFocusedProperty();
            decoListContainer.updateRecord();
        });
    }
}
