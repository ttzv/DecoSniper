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
    private WatcherInitiator watcherInitiator;
    private volatile Thread processingThread;

    public Watcher(WatcherInitiator watcherInitiator) throws IOException{
        this.watcherInitiator = watcherInitiator;
        watcher = FileSystems.getDefault().newWatchService();
        registered = false;
    }

    public Watcher (Path dir, WatcherInitiator watcherInitiator) throws IOException {
        this(watcherInitiator);
        this.dir = dir;
        register(dir.getParent());
    }

    public void register(Path dir){
        try {
            dir.getParent().register(watcher, ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        registered = true;
        this.dir = dir;
    }

    private void processEvents() throws InterruptedException{
        if(isRegistered()) {
            while(true) {

                WatchKey key;

                key = watcher.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    if (kind == OVERFLOW) {
                        System.out.println("Overflow");
                        continue;
                    }

                    if (kind == ENTRY_MODIFY) {
                        try {
                            modified(dir);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }

        } else {
            System.err.println("Watcher dir not registered");
        }

    }

    public void startWatching(){
        processingThread = new Thread(() -> {
            try {
                this.processEvents();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        processingThread.start();
    }

    public void stopWatching(){
        Thread thread = processingThread;
        if(thread != null){
            thread.interrupt();
        }
    }

    private void modified(Path path) throws IOException{

        if(Files.getLastModifiedTime(path).toMillis() - lastModTime > 1000){
            performAction();
        }

        lastModTime = Files.getLastModifiedTime(path).toMillis();

    }

    private void performAction(){
        System.out.println("modified");
        watcherInitiator.throwEvent();
    }

    public boolean isRegistered() {
        return registered;
    }
}
