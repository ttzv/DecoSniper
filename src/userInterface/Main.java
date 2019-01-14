package userInterface;

import backupHandler.FileBackup;
import decos.Deco;
import dirWatcher.Watcher;
import dirWatcher.WatcherInitiator;
import dirWatcher.WatcherListener;
import dirWatcher.WatcherResponder;
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
    private SimOutputPane simOutputPane;
    private DesiredDecos desiredDecos;
    private DecoListContainer decoListContainer;

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
        WatcherInitiator watcherInitiator = new WatcherInitiator();
        Watcher watcher = new Watcher (watcherInitiator);
        //Create Options Window
        OptionsWindow optionsWindow = new OptionsWindow(fileBackup, watcher);
        optionsWindow.build();

        //GUI containers
        BorderPane borderPane = new BorderPane();
        VBox vBoxCenterPane = new VBox();
        vBoxCenterPane.setAlignment(Pos.CENTER);
        HBox hBoxSlots = new HBox();
        HBox hBoxAction = new HBox();

        simOutputPane = new SimOutputPane(decoRecord);

        decoListContainer = new DecoListContainer(decoRecord.getDecoList());
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


        desiredDecos = new DesiredDecos(decoRecord.getDecoList());

        //Initialize VCW window object. getScene to remain in the same window, getStage to show in new window
        ValuablesWindow VCW = new ValuablesWindow(desiredDecos);
        //setVanishingText returning point when leaving VCW scene
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

        Label watchStatusLabel = new Label("Watching!");
        watchStatusLabel.setVisible(false);

        vBoxLeftPaneButtons.getChildren().addAll(buttonRotation, btnShowValStage, btnSimulate, cbxAutoNextSet, watchStatusLabel);
        borderPane.setLeft(vBoxLeftPaneButtons);

        button_1 = new Button("Empty");
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

        button_2 = new Button("Empty");
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

        button_3 = new Button("Empty");
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
                statusBar.setVanishingText("No more sets to load");
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
                statusBar.setVanishingText("No previous sets");
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
            clearSlots();
            decoListContainer.resetFocusedProperty();
            decoListContainer.updateRecord();
        });

        //listener for cbx. Disable Button A if cBox is ticked and start watching on attached directory
        WatcherListener watcherListener = new WatcherResponder(statusBar, decoRecord, button_1, button_2, button_3, decoListContainer, fileBackup, optionsWindow);
        watcherInitiator.addListener(watcherListener);

        cbxAutoNextSet.selectedProperty().addListener((observable, oldValue, newValue) -> {
            button_A.disableProperty().setValue(newValue);
            if (newValue) {
                watcher.startWatching();
                watchStatusLabel.setVisible(watcher.isRegistered());
                optionsWindow.cbxAutoBakDisabled(newValue);
            } else {
                watcher.stopWatching();
                watchStatusLabel.setVisible(newValue);
                optionsWindow.cbxAutoBakDisabled(newValue);
                optionsWindow.cbxAutoBakUnselect();
            }
        });

        hBoxAction.getChildren().add(button_A);

        MenuBar menuBar = new MenuBar();
        Menu menuActions = new Menu("Actions");
        Menu menuOptions = new Menu("Options");
        menuBar.getMenus().addAll(menuActions, menuOptions);

        MenuItem menuActClear = new MenuItem("Clear");

        MenuItem menuSimulate = new MenuItem("Simulate");
        menuSimulate.setOnAction(event -> {

            System.out.println(desiredDecos.getValuableDecosMap());
            simOutputPane.updateSimInfo(desiredDecos);

        });
        menuActClear.setOnAction(event -> {
            simOutputPane.clear();
            decoRecord.clearAll();
            desiredDecos.clear();
            clearSlots();
            decoListContainer.updateRecord();
        });
        menuActions.getItems().addAll(menuActClear, menuSimulate);
        MenuItem menuSteamLoc = new MenuItem("Steam Location");

        menuSteamLoc.setOnAction(event -> {

            optionsWindow.getStage().show();
            //saveDetector.show();
            //fileBackup.setSrcPath(saveDetector.getGameSaveDir());
        });

        menuOptions.getItems().addAll(menuSteamLoc);

        borderPane.setTop(menuBar);

        primaryStage.setScene(scene);

        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            watcher.stopWatching();
        });

        //primaryStage.setOnCloseRequest();

    }

    public void updateSlotsInfo(){
        if(decoRecord.getNumberOfSets() > 0) {
            button_1.setText(Deco.getDecoByID(decoRecord.getFocusedSet().get(0)).getName());
            button_2.setText(Deco.getDecoByID(decoRecord.getFocusedSet().get(1)).getName());
            button_3.setText(Deco.getDecoByID(decoRecord.getFocusedSet().get(2)).getName());
        }
    }

    public void clearSlots(){
        button_1.setText("Empty");
        button_2.setText("Empty");
        button_3.setText("Empty");
    }


}


