package de.leeksanddragons.tools.dialog.javafx.controller;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Justin on 29.08.2017.
 */
public class MainWindowController implements FXMLController, Initializable {

    @FXML
    protected Stage stage = null;

    public MainWindowController () {
        //
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

    @Override
    public void run() {
        //
    }

}
