package de.leeksanddragons.tools.dialog.javafx.controller;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.FXMLWindow;
import de.leeksanddragons.tools.dialog.javafx.SaveableTab;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import de.leeksanddragons.tools.dialog.model.transition.Transition;
import de.leeksanddragons.tools.dialog.utils.JavaFXUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Justin on 31.08.2017.
 */
public class TransitionPaneController implements FXMLController, Initializable {

    protected static final String ROW_FXML = "./data/ui/transition_row.fxml";

    @FXML
    protected Button addTransitionButton;

    @FXML
    protected ChoiceBox<String> typeChoiceBox;

    @FXML
    protected ListView<Pane> listView;

    protected QuestionEntry entry = null;
    protected int index = 0;

    protected Stage stage = null;

    protected List<TransitionRowController> controllerList = new ArrayList<>();

    public TransitionPaneController (QuestionEntry entry, int index) {
        this.entry = entry;
        this.index = index;
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        this.stage = stage;

        //create list with all transition types
        ObservableList<String> list = FXCollections.observableArrayList();

        for (String typeName : Transition.listTransitionTypeNames()) {
            list.add(typeName);
        }

        //set transition types to choice box
        typeChoiceBox.setItems(list);
        typeChoiceBox.getSelectionModel().select(0);

        addTransitionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String selectedType = typeChoiceBox.getSelectionModel().getSelectedItem();

                try {
                    addTransition(Transition.getTransitionByTitle(selectedType).getType());
                } catch (Exception e) {
                    JavaFXUtils.showErrorDialog("Cannot create transition", "Cannot create transition, maybe transition isnt supported yet. Please contact developers!");

                    return;
                }
            }
        });

        Platform.runLater(() -> {
            refreshListView();
        });
    }

    protected void addTransition (String typeName) {
        System.out.println("add transition: " + typeName);

        //get fxml path
        String fxmlPath = Transition.getTransitionByType(typeName).getFXMLPath();

        FXMLWindow dialog = null;

        try {
            dialog = new FXMLWindow("Add Transition", 440, 240, fxmlPath, Transition.getTransitionByType(typeName).createFXMLController(this, entry, index));
        } catch (Exception e) {
            JavaFXUtils.showExceptionDialog("Exception", "Cannot add transition, exception was thrown. Please copy this stacktrace and send it to developers!", e);

            return;
        }

        Stage stage = dialog.getStage();
        stage.hide();

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.stage);

        stage.showAndWait();

        refreshListView();
    }

    public void refreshListView () {
        //clear all items
        this.listView.getItems().clear();

        System.out.println("count of transitions for index " + index + ": " + listTransitions().size());

        for (Transition transition : listTransitions()) {
            addRow(transition);
        }
    }

    protected List<Transition> listTransitions () {
        return this.entry.getTranstionList(this.index);
    }

    protected void addRow (Transition transition) {
        // load fxml
        try {
            FXMLLoader loader = new FXMLLoader(new File(ROW_FXML).toURI().toURL());

            //set controller
            TransitionRowController controller = new TransitionRowController(this, this.entry, this.index, transition);
            this.controllerList.add(controller);
            loader.setController(controller);

            Pane rootPane = loader.load();//FXMLLoader.load(new File(fxmlPath).toURI().toURL());

            //initialize tab controller
            controller.init(stage, null, rootPane);

            this.listView.getItems().add(rootPane);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /*public void removeRow (Transition transition, Pane rootPane) {
        //
    }*/

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
