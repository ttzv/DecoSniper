package userInterface.customControl;

import decos.Deco;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Class used to create node with custom customizable view
 */
public class DecoNode extends GridPane{

    private Deco deco;

    private Path imgPath;
    private Image image;

    private Text rLabel;
    private Text nLabel;
    private Text mLabel;

    //two spacer panes to fill empty gaps
    private Pane spacerRectH;
    private Pane spacerSq;
    private Pane spacerRectV;
    private boolean clickable;

    /**
     * All Deco parameters (max lv, rarity, image) are resolved based on given Deco object, each control need its own object to interact with rest of UI elements
     * @param deco
     */
    public DecoNode(Deco deco, boolean clickable){
        this.clickable = clickable;

        this.setPadding(new Insets(2, 2, 1, 2));
        this.getStylesheets().add(getClass().getResource("decoNode.css").toExternalForm());
        this.deco = deco;

        imgPath = FileSystems.getDefault().getPath("img", "deco.png");
        image = new Image(imgPath.toUri().toString(), 20, 20, false, false);
        ImageView imgDeco = new ImageView(image);

        rLabel = new Text();
        nLabel = new Text();
        mLabel = new Text();

        rLabel.setText(String.valueOf(deco.getRarity()));
        nLabel.setText(deco.getName());
        mLabel.setText(String.valueOf(deco.getMaxLv()));

        spacerRectH = new Pane();
        spacerRectH.setPrefSize(30, 10);

        spacerSq = new Pane();
        spacerSq.setPrefSize(10, 10);

        spacerRectV = new Pane();
        spacerRectV.setPrefSize(10, 30);
        Pane spacerRectV1 = new Pane();
        spacerRectV1.setPrefSize(10, 30);

        this.setGridLinesVisible(false);
        this.setPrefHeight(50);
        this.setPrefWidth(50);

        this.add(rLabel, 0, 0);
        this.add(spacerRectH, 1, 0);
        this.add(mLabel, 2, 0);
        this.add(spacerRectV, 0, 1);
        this.add(imgDeco, 1, 1);
        GridPane.setHalignment(imgDeco, HPos.CENTER);
        GridPane.setValignment(imgDeco, VPos.CENTER);
        this.add(spacerRectV1, 2, 1);
        this.add(nLabel, 0, 2, 3, 1);
        GridPane.setHalignment(nLabel, HPos.CENTER);

        if(clickable){
            makeClickable();
        }
    }

    public DecoNode(Deco deco){
        this(deco, true);
    }

    public Deco getDeco() {
        return deco;
    }

    private void makeClickable(){
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("clicked on: " + deco.getName());
        });

        this.setCursor(Cursor.HAND);
    }
}
