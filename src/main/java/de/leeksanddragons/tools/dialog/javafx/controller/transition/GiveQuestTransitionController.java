package de.leeksanddragons.tools.dialog.javafx.controller.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.controller.TransitionPaneController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import de.leeksanddragons.tools.dialog.model.transition.FinishQuestTransition;
import de.leeksanddragons.tools.dialog.model.transition.GiveQuestTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Justin on 31.08.2017.
 */
public class GiveQuestTransitionController implements FXMLController, Initializable {

    @FXML
    protected TextField questNameTextField;

    @FXML
    protected Button addButton;

    protected TransitionPaneController paneController = null;
    protected QuestionEntry entry = null;
    protected int index = 0;

    public GiveQuestTransitionController (TransitionPaneController paneController, QuestionEntry entry, int index) {
        this.paneController = paneController;
        this.entry = entry;
        this.index = index;
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //add transition

                String questName = questNameTextField.getText();

                if (questName.isEmpty()) {
                    return;
                }

                GiveQuestTransition transition = new GiveQuestTransition(questName);
                entry.getTranstionList(index).add(transition);

                //refresh listview
                paneController.refreshListView();

                stage.close();
            }
        });
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
