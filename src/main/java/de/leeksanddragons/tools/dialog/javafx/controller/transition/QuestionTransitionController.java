package de.leeksanddragons.tools.dialog.javafx.controller.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.controller.MainWindowController;
import de.leeksanddragons.tools.dialog.javafx.controller.TransitionPaneController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import de.leeksanddragons.tools.dialog.model.transition.AddItemTransition;
import de.leeksanddragons.tools.dialog.model.transition.QuestionTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Justin on 31.08.2017.
 */
public class QuestionTransitionController implements FXMLController, Initializable {

    @FXML
    protected ChoiceBox<String> questionChoiceBox;

    @FXML
    protected Button addButton;

    protected TransitionPaneController paneController = null;
    protected QuestionEntry entry = null;
    protected int index = 0;

    public QuestionTransitionController (TransitionPaneController paneController, QuestionEntry entry, int index) {
        this.paneController = paneController;
        this.entry = entry;
        this.index = index;
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        //create list with all questions
        ObservableList<String> questions = FXCollections.observableArrayList();

        for (String question : MainWindowController.listQuestions()) {
            questions.add(question);
        }

        this.questionChoiceBox.setItems(questions);

        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //add transition

                String questionName = questionChoiceBox.getSelectionModel().getSelectedItem();

                QuestionTransition transition = new QuestionTransition(questionName);
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
