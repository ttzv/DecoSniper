package userInterface;

import decos.Deco;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.DecoRecord;
import userInterface.buttonControls.DecoListView;
import userInterface.historyRecord.DecoListContainer;


public class SniperGUI extends Application{

    private Button button_1;
    private Button button_2;
    private Button button_3;
    private DecoRecord decoRecord;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        decoRecord = new DecoRecord();

        primaryStage.setTitle("DecoSniper");
        //primaryStage.setResizable(false);
        BorderPane borderPane = new BorderPane();
        VBox vBoxCenterPane = new VBox();
        vBoxCenterPane.setAlignment(Pos.CENTER);
        HBox hBoxSlots = new HBox();
        HBox hBoxAction = new HBox();
        DecoListContainer decoListContainer = new DecoListContainer(decoRecord.getDecoList());
        decoListContainer.getStylesheets().add(SniperGUI.class.getResource("styleRecord.css").toExternalForm());
        borderPane.setRight(decoListContainer);
        BorderPane.setMargin(decoListContainer, new Insets(25, 25, 25, 25));
        vBoxCenterPane.getChildren().addAll(hBoxSlots, hBoxAction);
        hBoxSlots.setAlignment(Pos.CENTER);
        hBoxAction.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBoxCenterPane);
        hBoxSlots.setSpacing(10);
        hBoxSlots.setPadding(new Insets(25, 25, 25, 25));
        hBoxAction.setPadding(new Insets(0,0,25,0));
        Scene scene = new Scene(borderPane, 600, 300);
        scene.getStylesheets().add(SniperGUI.class.getResource("style.css").toExternalForm());

        button_1 = new Button("1");
        button_1.setPrefSize(50,50);
        button_1.setOnMouseClicked(event -> {
            DecoListView decoListView = new DecoListView(decoRecord, decoListContainer);
            if(decoListView.isShowing()) {
                decoListView.close();
            }
            else{
                decoListView.open(event.getScreenX(), event.getScreenY());
                decoListView.handleSelection(button_1, 1);
            }
        });

        button_2 = new Button("2");
        button_2.setPrefSize(50,50);
        button_2.setOnMouseClicked(event -> {
            DecoListView decoListView = new DecoListView(decoRecord, decoListContainer);

            if(decoListView.isShowing()) {
                decoListView.close();
            }
            else{
                decoListView.open(event.getScreenX(), event.getScreenY());
                decoListView.handleSelection(button_2, 2);
            }
        });

        button_3 = new Button("3");
        button_3.setPrefSize(50,50);
        button_3.setOnMouseClicked(event -> {
            DecoListView decoListView = new DecoListView(decoRecord, decoListContainer);
            if(decoListView.isShowing()) {
                decoListView.close();
            }
            else{
                decoListView.open(event.getScreenX(), event.getScreenY());
                decoListView.handleSelection(button_3, 3);
            }
        });

        Button buttonNextSet = new Button(">");
        buttonNextSet.setPrefSize(25, 50);
        buttonNextSet.setOnAction(event -> {
            System.out.println("Next");
            decoRecord.focusedSetPropertyIncrement();
            updateSlotsInfo();
            System.out.println("Focusedproperty: " + decoRecord.getFocusedSetProperty() + "numberofsets: " + decoRecord.getNumberOfSets());
            decoListContainer.incrementFocusedProperty();
            decoListContainer.updateFocusedSet();
        });

        Button buttonPrevSet = new Button("<");
        buttonPrevSet.setPrefSize(25, 50);
        buttonPrevSet.setOnAction(event -> {
            System.out.println("Previous");
            decoRecord.focusedSetPropertyDecrement();
            updateSlotsInfo();
            decoListContainer.decrementFocusedProperty();
            decoListContainer.updateFocusedSet();
        });

        hBoxSlots.getChildren().add(buttonPrevSet);
        hBoxSlots.getChildren().add(button_1);
        hBoxSlots.getChildren().add(button_2);
        hBoxSlots.getChildren().add(button_3);
        hBoxSlots.getChildren().add(buttonNextSet);



        Button button_A = new Button("Next");
        button_A.setPrefSize(100, 30);
        button_A.setOnAction(event -> {
            decoRecord.nextSet();
            System.out.println(decoRecord.getDecoList());
            clearSlots();
            decoListContainer.updateRecord();
        });
        button_A.setOnMouseReleased(event -> {
            decoListContainer.setVvalue(1.0);
        });
        hBoxAction.getChildren().add(button_A);

        MenuBar menuBar = new MenuBar();
        Menu menuActions = new Menu("Actions");
        Menu menuOptions = new Menu("Options");
        menuBar.getMenus().addAll(menuActions, menuOptions);

        MenuItem menuActNew = new MenuItem("New");
        menuActNew.setOnAction(event -> {
            decoRecord.nextSet();
        });
        MenuItem menuActPrevious = new MenuItem("Previous");
        MenuItem menuActNext = new MenuItem("Next");
        Menu menuActMeldLevel = new Menu("Streamstone");
            MenuItem menuStreamstoneShard = new MenuItem("Streamstone Shard (Lv. 1)");
            MenuItem menuStreamstone = new MenuItem("Streamstone (Lv. 2)");
            MenuItem menuGleamingStreamstone = new MenuItem("Gleaming Streamstone (Lv. 3)");
            menuActMeldLevel.getItems().addAll(menuStreamstoneShard, menuStreamstone, menuGleamingStreamstone);
        MenuItem menuActClear = new MenuItem("Clear");
        menuActClear.setOnAction(event -> {
            decoRecord.clearAll();
            clearSlots();

        });
        menuActions.getItems().addAll(menuActNew, menuActPrevious, menuActNext, menuActMeldLevel, menuActClear);
        MenuItem menuOptSave = new MenuItem("Save");
        MenuItem menuOptLoad = new MenuItem("Load");
        MenuItem menuOptRecord = new MenuItem("Record");
        menuOptRecord.setOnAction(event -> {
            /*if(DecoListContainer.isVisible()){
                DecoListContainer.setVisible(false);
            }
            else{
                DecoListContainer.setVisible(true);
            }*/
            decoListContainer.updateRecord();
        });
        menuOptions.getItems().addAll(menuOptLoad, menuOptSave, menuOptRecord);

        borderPane.setTop(menuBar);



        primaryStage.setScene(scene);

        primaryStage.show();



    }

    public void updateSlotsInfo(){
        /*  if(decoRecord.getNumberOfSets() > decoRecord.getFocusedSetProperty() && decoRecord.getFocusedSetProperty() > 0) {
            int decoid = decoRecord.getFocusedSet().get(0);
            System.out.println(decoid);
            String deconame = Deco.getDecoByID(decoid).getName();
            System.out.println(deconame);
            System.out.println("FocusedProperty: " + decoRecord.getFocusedSetProperty());

            button_1.setText(deconame);
          */

            button_1.setText(Deco.getDecoByID(decoRecord.getFocusedSet().get(0)).getName());
            button_2.setText(Deco.getDecoByID(decoRecord.getFocusedSet().get(1)).getName());
            button_3.setText(Deco.getDecoByID(decoRecord.getFocusedSet().get(2)).getName());

    }

    public void clearSlots(){
        button_1.setText("1");
        button_2.setText("2");
        button_3.setText("3");
    }

}


