package de.leeksanddragons.tools.dialog.javafx.controller;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
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
    protected ListView<Pane> listView;

    public TransitionPaneController (QuestionEntry entry, int index) {
        //
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        //
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
