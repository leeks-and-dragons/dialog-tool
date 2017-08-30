package de.leeksanddragons.tools.dialog.javafx.controller;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.SaveableTab;
import de.leeksanddragons.tools.dialog.model.QuestionLangEntry;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Justin on 30.08.2017.
 */
public class TabController implements FXMLController, Initializable, SaveableTab {

    protected Tab tab = null;

    /**
    * question language entry
    */
    protected QuestionLangEntry entry = null;

    @FXML
    protected TextArea textArea;

    @FXML
    protected TextField choise1TextField;

    @FXML
    protected TextField choise2TextField;

    @FXML
    protected TextField choise3TextField;

    public TabController (Tab tab, QuestionLangEntry entry) {
        this.tab = tab;
        this.entry = entry;
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        //fill widgets
        this.textArea.setText(entry.getText());
        this.choise1TextField.setText(entry.getChoise1Text());
        this.choise2TextField.setText(entry.getChoise2Text());
        this.choise3TextField.setText(entry.getChoise3Text());

        this.textArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("text: " + textArea.getText());

                entry.setText(textArea.getText());
            }
        });
    }

    public void saveTab () {
        System.out.println("text: " + textArea.getText());

        entry.setText(textArea.getText());
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void reset () {
        this.textArea.setText("");
        this.choise1TextField.setText("");
        this.choise2TextField.setText("");
        this.choise3TextField.setText("");
    }

}
