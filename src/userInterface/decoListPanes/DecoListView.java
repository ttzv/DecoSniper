package userInterface.buttonControls;

import decos.Deco;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.DecoRecord;
import userInterface.historyRecord.DecoListContainer;

import java.util.ArrayList;

public class DecoListView extends Stage {

   private ListView listView;
   private VBox listBox;
   private TextField searchInput;
   private ObservableList<Deco> decoObservableList;

   private DecoRecord decoRecord;
   private DecoListContainer decoListContainer;

    public DecoListView(DecoRecord decoRecord, DecoListContainer decoListContainer){
        super(StageStyle.UNDECORATED);

        this.decoRecord = decoRecord;
        this.decoListContainer = decoListContainer;

        decoObservableList = FXCollections.observableArrayList();
        decoObservableList.addAll(Deco.getDecosList());
        listBox = new VBox();
        searchInput = new TextField();
        listView = new ListView<Deco>();
        for (Deco d : decoObservableList) {
            listView.getItems().add(d.getName());
        }
        setScene(new Scene(listBox, 250, 300));
        listBox.getChildren().addAll(searchInput, listView);
        focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!observable.getValue() && isShowing()){
                hide();
            }
        });

        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            search(newValue);
            System.out.println("oldval: " + oldValue);
            System.out.println("newval: " + newValue);
        });
    }

    public void handleSelection(Button node, int slot){
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            node.setText(newValue.toString());
            Deco choice = Deco.getDecoByName(newValue.toString());
            decoRecord.setSlotInFocusedSet(slot, choice.getId());
            DecoListView.this.hide();
            decoListContainer.updateRecord();
        });
    }

    /**
     * Encapsulates show(), opens a window at given coordinates
     * @param locX horizontal coordinate on the screen
     * @param locY vertical coordinate on the screen
     */
    public void open(double locX, double locY){
        setX(locX);
        setY(locY);
        show();
    }

    public void search(String newVal){
        ObservableList<Deco> foundEntries = FXCollections.observableArrayList();
        for (Deco d : decoObservableList){
            if (d.getName().toLowerCase().contains(newVal.toLowerCase().trim()))
                foundEntries.add(d);
        }
        listView.setItems(foundEntries);
    }


}
