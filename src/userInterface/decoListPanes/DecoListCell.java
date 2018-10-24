package userInterface.historyRecord;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DecoListCell extends HBox {

    private Label labelNumber;
    private Label labelSlot1;
    private Label labelSlot2;
    private Label labelSlot3;

    private Background defaultBackground;

    public DecoListCell(
            String content_1,
            String content_2,
            String content_3,
            String content_4) {

        setSpacing(5);

        labelNumber = new Label();
        labelSlot1 = new Label();
        labelSlot2 = new Label();
        labelSlot3 = new Label();

        getChildren().addAll(
                labelNumber,
                labelSlot1,
                labelSlot2,
                labelSlot3);

        setLabelsContent(
                content_1,
                content_2,
                content_3,
                content_4);

        defaultBackground = getBackground();
    }

    private void setLabelsContent(String... content){
        labelNumber.setText(content[0]);
        labelSlot1.setText(content[1]);
        labelSlot2.setText(content[2]);
        labelSlot3.setText(content[3]);
    }

    public void setBkgOnFocus(boolean focusedPropety){

        Background focusedBackground = new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY));
        if(focusedPropety){
            backgroundProperty().set(focusedBackground);
        }
        else{
            backgroundProperty().set(defaultBackground);
        }
    }
}
