package userInterface.historyRecord;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
//TODO change vbox to scrollpane and nest vbox in it
public class DecoListContainer extends VBox {

    private ArrayList<ArrayList<Integer>> decoList;
    private ArrayList<DecoListCell> cellsList;
    private int focusedSet;

    public DecoListContainer(ArrayList<ArrayList<Integer>> decoList) {
        super(5);
        setStyle("-fx-background-color: #4286f4");
        setPadding(new Insets(5, 5, 5, 5));
        this.decoList = decoList;
        focusedSet = decoList.size() - 1;
        cellsList = new ArrayList<>();

    }

    public void updateRecord(){
        clear();
        Integer counter = 1;
        for (ArrayList decoSet : decoList){
            cellsList.add(new DecoListCell(
                    counter.toString(),
                    decoSet.get(0).toString(),
                    decoSet.get(1).toString(),
                    decoSet.get(2).toString()));
            System.out.println(cellsList);
            counter++;
        }
        getChildren().addAll(cellsList);
        resetFocusedProperty();
        updateFocusedSet();
    }

    public void clear(){
        cellsList.clear();
        getChildren().clear();
    }

    public void updateFocusedSet(){
        for (DecoListCell d : cellsList) {
            d.setBkgOnFocus(false);
        }
        cellsList.get(focusedSet).setBkgOnFocus(true);
    }

    public void decrementFocusedProperty(){
        if ( focusedSet > 0 )
            focusedSet--;
    }
    public void incrementFocusedProperty(){
        if ( focusedSet < decoList.size() - 1 )
            focusedSet++;
    }

    public void resetFocusedProperty(){
        focusedSet = decoList.size() - 1;
    }
}
