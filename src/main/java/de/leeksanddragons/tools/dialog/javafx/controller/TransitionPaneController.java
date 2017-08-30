package de.leeksanddragons.tools.dialog.javafx.controller;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.FXMLWindow;
import de.leeksanddragons.tools.dialog.javafx.SaveableTab;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import de.leeksanddragons.tools.dialog.model.transition.Transition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Justin on 31.08.2017.
 */
public class TransitionPaneController implements FXMLController, Initializable {

    @FXML
    protected Button addTransitionButton;

    @FXML
    protected ChoiceBox<String> typeChoiceBox;

    @FXML
    protected ListView<Pane> listView;

    protected QuestionEntry entry = null;
    protected int index = 0;

    protected Stage stage = null;

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

                addTransition(Transition.getTransitionByTitle(selectedType).getType());
            }
        });
    }

    protected void addTransition (String typeName) {
        System.out.println("add transition: " + typeName);

        //get fxml path
        String fxmlPath = Transition.getTransitionByType(typeName).getFXMLPath();

        FXMLWindow dialog = new FXMLWindow("Add Transition", 440, 240, fxmlPath, Transition.getTransitionByType(typeName).createFXMLController(entry, index));

        Stage stage = dialog.getStage();
        stage.hide();

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.stage);

        stage.showAndWait();

        refreshListView();
    }

    public void refreshListView () {
        //TODO: add code here
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
