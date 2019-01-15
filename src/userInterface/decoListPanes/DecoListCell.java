package userInterface.decoListPanes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import userInterface.customControl.DecoNode;

public class DecoListCell extends HBox {

    private Label labelNumber;
    private Pane decoSlot1;
    private Pane decoSlot2;
    private Pane decoSlot3;

    private Background defaultBackground;

    public DecoListCell(
            String content_1,
            DecoNode content_2,
            DecoNode content_3,
            DecoNode content_4) {

        setSpacing(1);

        labelNumber = new Label();
        labelNumber.setAlignment(Pos.CENTER);
        decoSlot1 = new Pane();
        decoSlot2 = new Pane();
        decoSlot3 = new Pane();

        getChildren().addAll(
                labelNumber,
                decoSlot1,
                decoSlot2,
                decoSlot3);

        setLabelsContent(
                content_1,
                content_2,
                content_3,
                content_4);

        defaultBackground = getBackground();

        getStylesheets().add(getClass().getResource("decoListCell.css").toExternalForm());
        decoSlot1.getStyleClass().add("pane");
        decoSlot2.getStyleClass().add("pane");
        decoSlot3.getStyleClass().add("pane");
    }

    private void setLabelsContent(String no, DecoNode... content){
        labelNumber.setText(no);
        decoSlot1.getChildren().add(content[0]);
        decoSlot2.getChildren().add(content[1]);
        decoSlot3.getChildren().add(content[2]);
    }

    public void setBkgOnFocus(boolean focusedPropety){

        Background focusedBackground = new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY));
        if(focusedPropety){
            backgroundProperty().set(focusedBackground);
        }
        else{
            backgroundProperty().set(defaultBackground);
        }
    }
}
