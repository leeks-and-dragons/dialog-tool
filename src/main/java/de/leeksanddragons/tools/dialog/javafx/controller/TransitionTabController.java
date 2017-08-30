package de.leeksanddragons.tools.dialog.javafx.controller;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.SaveableTab;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import de.leeksanddragons.tools.dialog.model.QuestionLangEntry;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Justin on 30.08.2017.
 */
public class TransitionTabController implements FXMLController, Initializable, SaveableTab {

    protected Stage stage = null;

    protected static final String TRANSITION_PANE_FXML = "./data/ui/transition_titled_pane.fxml";

    protected QuestionEntry entry = null;

    @FXML
    protected Accordion accordion;

    protected List<TransitionPaneController> controllerList = new ArrayList<>();

    public TransitionTabController (QuestionEntry entry) {
        this.entry = entry;
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        this.stage = stage;

        //clear all titled panes
        accordion.getPanes().clear();

        //add start titled pane
        addStartTitledPane();

        //add titled pane for every choice
        for (int i = 1; i <= QuestionEntry.MAX_CHOICES; i++) {
            addChoiceTitledPane(i);
        }
    }

    protected void addStartTitledPane () {
        TitledPane titledPane = new TitledPane();
        titledPane.setText("Transition before choice (on start)");
        titledPane.setAnimated(true);

        // load fxml
        try {
            FXMLLoader loader = new FXMLLoader(new File(TRANSITION_PANE_FXML).toURI().toURL());

            //set controller
            TransitionPaneController controller = new TransitionPaneController(this.entry, 0);
            this.controllerList.add(controller);
            loader.setController(controller);

            Pane rootPane = loader.load();//FXMLLoader.load(new File(fxmlPath).toURI().toURL());

            //initialize tab controller
            controller.init(stage, null, rootPane);

            titledPane.setContent(rootPane);
            titledPane.setExpanded(true);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        accordion.getPanes().add(titledPane);
    }

    protected void addChoiceTitledPane (int index) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText("Choice " + index);
        titledPane.setAnimated(true);

        // load fxml
        try {
            FXMLLoader loader = new FXMLLoader(new File(TRANSITION_PANE_FXML).toURI().toURL());

            //set controller
            TransitionPaneController controller = new TransitionPaneController(this.entry, index);
            this.controllerList.add(controller);
            loader.setController(controller);

            Pane rootPane = loader.load();//FXMLLoader.load(new File(fxmlPath).toURI().toURL());

            //initialize tab controller
            controller.init(stage, null, rootPane);

            titledPane.setContent(rootPane);
            titledPane.setExpanded(true);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        accordion.getPanes().add(titledPane);
    }

    public void saveTab () {
        //
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
