package dirWatcher;

import javafx.scene.control.Button;
import logic.decoRecord.DecoRecord;
import userInterface.decoListPanes.DecoListContainer;
import userInterface.statusBar.StatusBar;

import java.io.IOException;
import java.nio.file.*;


import static java.nio.file.StandardWatchEventKinds.*;

public class Watcher {

    private WatchService watcher;
    private Path dir;
    private long lastModTime = 0;
    private boolean registered;
    private StatusBar statusBar;
    private DecoRecord decoRecord;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private DecoListContainer decoListContainer;

    public Watcher() throws IOException{
        watcher = FileSystems.getDefault().newWatchService();
        registered = false;
    }

    public Watcher (Path dir) throws IOException {
        this();
        this.dir = dir;
        register(dir.getParent());
    }

    public Watcher(Path savePath, StatusBar statusBar, DecoRecord decoRecord, Button button_1, Button button_2, Button button_3, DecoListContainer decoListContainer) throws IOException {
        this(savePath);
        this.statusBar = statusBar;
        this.decoRecord = decoRecord;
        this.button_1 = button_1;
        this.button_2 = button_2;
        this.button_3 = button_3;
        this.decoListContainer = decoListContainer;
    }

    public void register(Path dir){
        try {
            dir.register(watcher, ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        registered = true;
    }

    private void processEvents() throws InterruptedException{
        while(true){
            WatchKey key;

            key = watcher.take();

            for(WatchEvent<?> event : key.pollEvents()){
                WatchEvent.Kind<?> kind = event.kind();

                if(kind == OVERFLOW){
                    System.out.println("Overflow");
                    continue;
                }

                if(kind == ENTRY_MODIFY){
                    try {
                        modified(dir);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            boolean valid = key.reset();
            if(!valid){
                break;
            }
        }
    }

    public void startWatching(){
        Thread processingThread = new Thread(() -> {
            try {
                this.processEvents();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        processingThread.start();
    }

    private void modified(Path path) throws IOException{

        if(Files.getLastModifiedTime(path).toMillis() - lastModTime > 1000){
            performAction();
        }

        lastModTime = Files.getLastModifiedTime(path).toMillis();

    }

    private void performAction(){
        decoRecord.nextSet();
        button_1.setText("1");
        button_2.setText("2");
        button_3.setText("3");
        decoListContainer.resetFocusedProperty();
        decoListContainer.updateRecord();
    }

}
