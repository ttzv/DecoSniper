package userInterface.buttons;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import logic.decoRecord.DecoRecord;

public class ButtonRotation extends Button {

    private DecoRecord decoRecord;
    private Label[] labels;
    private String defaultTextStyle;

    public ButtonRotation(DecoRecord decoRecord){
        this.decoRecord = decoRecord;

        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        HBox hBoxColored = new HBox(0);
        /**
         * index 0
         */
        Label[] labels = {new Label("-"), new Label("1"), new Label("1"), new Label("2"), new Label("-")};

        hBoxColored.getChildren().addAll(labels[1], labels[0], labels[2], labels[4], labels[3]);

        this.setGraphic(hBoxColored);

        this.setOnAction(event -> {
           decoRecord.nextRotationStatus();
           updateLabel(labels);
            System.out.println(decoRecord.getRotationStatus() + " " + decoRecord.getSkipRotation());
        });

        defaultTextStyle = labels[0].getStyle();
        updateLabel(labels);
    }

    private void updateLabel(Label[] labels){
        for (Label l : labels){
            l.setStyle(defaultTextStyle);
        }
        if(decoRecord.getRotationStatus() != 0) {
            labels[decoRecord.getRotationStatus()].setStyle("-fx-effect: innershadow( gaussian, red, 5, 0, 0, 0);");
        }
    }

}
