package userInterface.ValuableChooserWindow;

import decos.Deco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import logic.questsCounter.DesiredDecos;

import java.util.ArrayList;


public class ValuableChooserWindow {

    private DesiredDecos desiredDecos;
    private Stage stage;
    private Button btnAdd;
    private Button btnDel;
    private ListView<Deco> sourceListView;
    private ListView<Deco> targetListView;
    private ObservableList<Deco> srcList;
    private ObservableList<Deco> targetList;
    private Insets insets;


    public ValuableChooserWindow(DesiredDecos desiredDecos){
        insets = new Insets(10, 10, 10, 10);
        this.stage = new Stage();
        sourceListView = new ListView<>();
        targetListView = new ListView<>();
        this.desiredDecos = desiredDecos;
    }

    public Stage getStage() {
        build();
        return stage;
    }

    public void build(){
        HBox region = new HBox();
        Scene scene = new Scene(region, 600, 300);
        stage.setScene(scene);
        region.setAlignment(Pos.CENTER);
        HBox.setMargin(sourceListView, insets);
        HBox.setMargin(targetListView, insets);

        VBox vBoxChooseButtons = new VBox();
        btnAdd = new Button("+");
        btnAdd.setPrefSize(25, 25);
        btnDel = new Button("-");
        btnDel.setPrefSize(25, 25);
        vBoxChooseButtons.setSpacing(5);
        vBoxChooseButtons.getChildren().addAll(btnAdd, btnDel);
        vBoxChooseButtons.setAlignment(Pos.CENTER);

        srcList = createList(Deco.getDecosList());
        targetList = createList();

        sourceListView.setItems(srcList);
        this.targetListView.setItems(targetList);

        region.getChildren().addAll(sourceListView, vBoxChooseButtons, targetListView);

        addHandlers();

    }

    /**
     * Creates new ObservableList populated with items from given parameter
     * @param initList list containing items to populate new observablelist with.
     * @return new non-empty ObservableList
     */
    public ObservableList<Deco> createList (ArrayList<Deco> initList){
        ObservableList<Deco> observableList = FXCollections.observableArrayList();
        observableList.addAll(initList);

        return observableList;
    }

    /**
     * Creates new unpopulated Observablelist
     * @return new empty ObservableList
     */
    public ObservableList<Deco> createList (){
        ObservableList<Deco> observableList = FXCollections.observableArrayList();

        return observableList;
    }


    public void moveItem(Deco item, ObservableList<Deco> source, ObservableList<Deco> target){
        if(source.contains(item)){
            target.add(item);
            source.remove(item);
        }
        else {
            System.out.println("Item " + item + " does not exist in source list");
        }
    }

    public void addHandlers(){
        this.btnAdd.setOnAction(event -> {
            Deco item = this.sourceListView.getSelectionModel().getSelectedItem();
            System.out.println(item);
            moveItem(item, srcList, targetList);

            this.sourceListView.setItems(srcList);

            desiredDecos.setValuablesList(Deco.listDecoToId(new ArrayList<>(targetList)));
        });

        this.btnDel.setOnAction(event -> {
            Deco item = this.targetListView.getSelectionModel().getSelectedItem();
            System.out.println(item);
            moveItem(item, targetList, srcList);

            this.targetListView.setItems(targetList);

            desiredDecos.setValuablesList(Deco.listDecoToId(new ArrayList<>(targetList)));
        });
    }

}
