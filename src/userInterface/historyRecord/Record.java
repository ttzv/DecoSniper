package userInterface.historyRecord;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Record extends TableView {

    private ObservableList<String> sampleData;

    public Record() {

        sampleData = FXCollections.observableArrayList();
        sampleData.addAll("First", "Second", "Third");
        setItems(sampleData);
    }
}
