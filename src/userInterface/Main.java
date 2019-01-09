package userInterface;

import backupHandler.FileBackup;
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
import logic.decoRecord.DecoRecord;
import logic.questsCounter.DesiredDecos;
import userInterface.scene.valuables.ValuablesWindow;
import userInterface.decoListPanes.DecoListView;
import userInterface.buttons.ButtonRotation;
import userInterface.decoListPanes.DecoListContainer;
import userInterface.simulationOutput.SimOutputPane;
import userInterface.stages.steamDir.OptionsWindow;
import userInterface.statusBar.StatusBar;


public class Main extends Application{

    /* TODO: separate Buttons */

    private Button button_1;
    private Button button_2;
    private Button button_3;
    private DecoRecord decoRecord;

   /* //Backup configuration fields
    private final Path srcPath;
    private final Path bkpPath;
    */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Initialization of logic
        decoRecord = new DecoRecord();

        //Initialization of backups function
        FileBackup fileBackup = new FileBackup();

        //Initialization of configuration files and objects
        primaryStage.setTitle("DecoSniper");

        //GUI containers
        BorderPane borderPane = new BorderPane();
        VBox vBoxCenterPane = new VBox();
        vBoxCenterPane.setAlignment(Pos.CENTER);
        HBox hBoxSlots = new HBox();
        HBox hBoxAction = new HBox();

        SimOutputPane simOutputPane = new SimOutputPane(decoRecord);

        DecoListContainer decoListContainer = new DecoListContainer(decoRecord.getDecoList());
        decoListContainer.getStylesheets().add(Main.class.getResource("styleRecord.css").toExternalForm());
        borderPane.setRight(decoListContainer);
        BorderPane.setMargin(decoListContainer, new Insets(25, 25, 25, 25));

        vBoxCenterPane.getChildren().addAll(hBoxSlots, hBoxAction, simOutputPane);
        hBoxSlots.setAlignment(Pos.CENTER);
        hBoxAction.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBoxCenterPane);
        hBoxSlots.setSpacing(10);
        hBoxSlots.setPadding(new Insets(25, 25, 25, 25));
        hBoxAction.setPadding(new Insets(0,0,25,0));

        Scene scene = new Scene(borderPane, 600, 300);
        scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());

        ButtonRotation buttonRotation = new ButtonRotation(decoRecord);

        StatusBar statusBar = new StatusBar();
        borderPane.setBottom(statusBar.getStatusBar());

        DesiredDecos desiredDecos = new DesiredDecos(decoRecord.getDecoList());

        //Initialize VCW window object. getScene to remain in the same window, getStage to show in new window
        ValuablesWindow VCW = new ValuablesWindow(desiredDecos);
        //set returning point when leaving VCW scene
        VCW.setScenePrev(scene);
        VCW.build();

        VBox vBoxLeftPaneButtons = new VBox();

        Button btnShowValStage = new Button("Valuables...");
        btnShowValStage.setOnAction(event -> {
            primaryStage.setScene(VCW.getScene());
            //VCW.getStage().show(); UNUSED
        });

        Button btnSimulate = new Button("Simulate");
        btnSimulate.setOnAction(event -> {
            System.out.println(desiredDecos.getValuableDecosMap());
            simOutputPane.updateSimInfo(desiredDecos);
        });
        //Checkbox for turning save listener on or off
        CheckBox cbxAutoNextSet = new CheckBox();
        cbxAutoNextSet.setText("AutoDetect Set");
        cbxAutoNextSet.setSelected(false);

        vBoxLeftPaneButtons.getChildren().addAll(buttonRotation, btnShowValStage, btnSimulate, cbxAutoNextSet);
        borderPane.setLeft(vBoxLeftPaneButtons);

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
            statusBar.clear();
            if(!decoRecord.focusedSetPropertyIncrement()){
                statusBar.set("No more sets to load");
            }
            updateSlotsInfo();
            System.out.println("Focusedproperty: " + decoRecord.getFocusedSetProperty() + "numberofsets: " + decoRecord.getNumberOfSets());
            decoListContainer.incrementFocusedProperty();
            decoListContainer.updateFocusedSet();
        });


        Button buttonPrevSet = new Button("<");
        buttonPrevSet.setPrefSize(25, 50);
        buttonPrevSet.setOnAction(event -> {
            System.out.println("Previous");
            statusBar.clear();
            if(!decoRecord.focusedSetPropertyDecrement()){
                statusBar.set("No previous sets");
            }
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
        button_A.setOnMousePressed(event -> {
            statusBar.clear();
            decoRecord.nextSet();
            System.out.println(decoRecord.getDecoList());
            clearSlots();
            decoListContainer.resetFocusedProperty();
            decoListContainer.updateRecord();
        });
        button_A.setOnMouseReleased(event -> {
            decoListContainer.setVvalue(1.0);
        });

        //listener for cbx. Disable Button A if is ticked
        cbxAutoNextSet.selectedProperty().addListener((observable, oldValue, newValue) -> {
            button_A.disableProperty().setValue(newValue);
        });

        hBoxAction.getChildren().add(button_A);

        MenuBar menuBar = new MenuBar();
        Menu menuActions = new Menu("Actions");
        Menu menuOptions = new Menu("Options");
        menuBar.getMenus().addAll(menuActions, menuOptions);

        MenuItem menuActNew = new MenuItem("New");
        menuActNew.setOnAction(event -> {
            simOutputPane.clear();
            decoRecord.clearAll();
            decoRecord.nextSet();
            clearSlots();
            decoListContainer.updateRecord();
        });
        MenuItem menuActPrevious = new MenuItem("Previous");
        MenuItem menuActNext = new MenuItem("Next");
        Menu menuActMeldLevel = new Menu("Streamstone");
            MenuItem menuStreamstoneShard = new MenuItem("Streamstone Shard (Lv. 1)");
            MenuItem menuStreamstone = new MenuItem("Streamstone (Lv. 2)");
            MenuItem menuGleamingStreamstone = new MenuItem("Gleaming Streamstone (Lv. 3)");
            menuActMeldLevel.getItems().addAll(menuStreamstoneShard, menuStreamstone, menuGleamingStreamstone);
        MenuItem menuActClear = new MenuItem("Clear");
        MenuItem menuSimulate = new MenuItem("Simulate");
        menuSimulate.setOnAction(event -> {

            System.out.println(desiredDecos.getValuableDecosMap());
            simOutputPane.updateSimInfo(desiredDecos);

        });
        menuActClear.setOnAction(event -> {
            simOutputPane.clear();
            decoRecord.clearAll();
            decoRecord.nextSet();
            desiredDecos.clear();
            clearSlots();
            decoListContainer.updateRecord();
        });
        menuActions.getItems().addAll(menuActNew, menuActPrevious, menuActNext, menuActMeldLevel, menuActClear, menuSimulate);
        MenuItem menuSteamLoc = new MenuItem("Steam Location");
        OptionsWindow optionsWindow = new OptionsWindow();
        optionsWindow.build();
        menuSteamLoc.setOnAction(event -> {

            optionsWindow.getStage().show();
            //saveDetector.show();
            //fileBackup.setSrcPath(saveDetector.getGameSaveDir());
        });

        MenuItem menuOptLoad = new MenuItem("Load");
        menuOptLoad.setOnAction(event -> {

        });
        MenuItem menuOptRecord = new MenuItem("Record");
        menuOptRecord.setOnAction(event -> {
            /*if(DecoListContainer.isVisible()){
                DecoListContainer.setVisible(false);
            }
            else{
                DecoListContainer.setVisible(true);
            }*/
            decoListContainer.setVvalue(1.0);
        });
        menuOptions.getItems().addAll(menuOptLoad, menuSteamLoc, menuOptRecord);

        borderPane.setTop(menuBar);

        primaryStage.setScene(scene);

        primaryStage.show();

        //primaryStage.setOnCloseRequest();

    }

    public void updateSlotsInfo(){

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


