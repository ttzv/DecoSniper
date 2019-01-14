package userInterface.statusBar;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Timer;
import java.util.TimerTask;

public class StatusBar {
    private Label statusLabel;
    private HBox statushBox;

    public StatusBar() {
        this.statusLabel = new Label( "");
        this.statusLabel.setPadding(new Insets(0, 0, 0, 5));
        this.statushBox = new HBox();
        this.statushBox.getChildren().add(statusLabel);
        statushBox.getStylesheets().add(getClass().getResource("statusbar.css").toExternalForm());
        statushBox.getStyleClass().add("statusbar");
    }

    public void setVanishingText(String text){
        int timeToClear = 5000;
        setText(text);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() ->{
                    clear();
                    timer.cancel();
                });
            }
        };
        timer.schedule(timerTask, timeToClear);
    }

    private void setText(String text){
        this.statusLabel.setText(text);
    }

    public HBox getStatusBar(){
        return this.statushBox;
    }

    public void clear(){
        this.statusLabel.setText("");
    }
}
