package observerPatternTests;

import java.util.ArrayList;
import java.util.List;

public class Tests {


    interface WatcherListener {
        void handleEvent();
    }

    static class Initiator{

        private List<WatcherListener> listeners = new ArrayList<>();

        public void addListener(WatcherListener listener){
            listeners.add(listener);
        }

        public void initiate() {
            System.out.println("Initiated");

            for (WatcherListener cl : listeners) {
                cl.handleEvent();
            }
        }
    }

    static class Responder implements WatcherListener {

        @Override
        public void handleEvent() {
            System.out.println("Responded");
        }


    }


    public static void main(String[] args) {

        Initiator initiator = new Initiator();
        Responder responder = new Responder();

        initiator.addListener(responder);

        initiator.initiate();


    }
}
