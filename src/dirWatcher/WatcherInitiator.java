package dirWatcher;

import observerPatternTests.Tests;

import java.util.ArrayList;
import java.util.List;

public class WatcherInitiator {

    private List<WatcherListener> listeners = new ArrayList<>();

    public void addListener(WatcherListener listener){
        listeners.add(listener);
    }

    public void throwEvent() {
        System.out.println("Event thrown");

        for (WatcherListener cl : listeners) {
            cl.actionPerformed();
        }
    }
}
