package dirWatcher;


import javafx.beans.value.ObservableBooleanValue;

import java.io.IOException;
import java.nio.file.*;


import static java.nio.file.StandardWatchEventKinds.*;

public class Watcher {

    private WatchService watcher;
    private Path dir;
    private long lastModTime = 0;
    private boolean registered;

    public Watcher() throws IOException{
        watcher = FileSystems.getDefault().newWatchService();
        registered = false;
    }

    public Watcher (Path dir) throws IOException {
        this();
        this.dir = dir;
        register(dir.getParent());
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
    }

}
