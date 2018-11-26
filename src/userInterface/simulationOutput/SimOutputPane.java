package userInterface.simulationOutput;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import logic.decoRecord.DecoRecord;
import logic.questsCounter.DesiredDecos;
import logic.questsCounter.RotationSimulator;

public class SimOutputPane extends VBox {

    private Label remQuests;
    private Label remMelds;
    private DecoRecord decoRecord;
    private RotationSimulator rotationSimulator;
    private String defText1 = "Quests left: ";
    private String defText2 = "Melds left: ";

    public SimOutputPane(DecoRecord decoRecord) {
        this.decoRecord = decoRecord;
        this.remQuests = new Label(defText1);
        this.remMelds = new Label(defText2);

        setPadding(new Insets(10, 10, 10, 10));
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        this.getStyleClass().add("style");
        this.getChildren().addAll(remQuests, remMelds);

    }

    public void updateSimInfo(DesiredDecos desiredDecos){
        this.rotationSimulator = new RotationSimulator(desiredDecos.getValuableDecosMap(), decoRecord.getRotationStatus());
        System.out.println("Valuables:" + desiredDecos.getValuableDecosMap());
        rotationSimulator.simulate();
        remQuests.setText(defText1 + rotationSimulator.getRemainingQuests());
        remMelds.setText(defText2 + rotationSimulator.getRemainingMelds());
    }

    public void clear(){
        this.remQuests.setText(defText1);
        this.remMelds.setText(defText2);
    }
}
