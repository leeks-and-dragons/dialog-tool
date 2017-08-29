package de.leeksanddragons.tools.dialog.javafx.controller;

import de.leeksanddragons.tools.dialog.i18n.LangLoader;
import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

/**
 * Created by Justin on 29.08.2017.
 */
public class MainWindowController implements FXMLController, Initializable {

    @FXML
    protected Stage stage = null;

    @FXML
    protected ListView<String> questionList;

    @FXML
    protected TextField newQuestionTextField;

    @FXML
    protected Button newQuestionButton;

    @FXML
    protected TabPane tabPane;

    /**
    * instance of language loader
    */
    protected LangLoader langLoader = null;

    /**
    * map with all questions of dialog
    */
    protected Map<String,QuestionEntry> questionMap = new HashMap<>();

    public MainWindowController (LangLoader loader) {
        this.langLoader = loader;
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        this.stage = stage;

        this.newQuestionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String questionName = newQuestionTextField.getText().trim();

                if (!questionName.isEmpty()) {
                    //check, if question is already in list
                    if (!questionList.getItems().contains(questionName)) {
                        addQuestion(questionName);
                    }
                }
            }
        });

        //hide all widgets, if no dialog was loaded
        this.hideAllWidgets();
    }

    protected void hideAllWidgets () {
        //hide some widgets, if no dialog was loaded
        tabPane.setVisible(false);
        questionList.setVisible(false);
        newQuestionTextField.setVisible(false);
        newQuestionButton.setVisible(false);
    }

    protected void showAllWidgets () {
        //show some widgets, if no dialog was loaded
        tabPane.setVisible(true);
        questionList.setVisible(true);
        newQuestionTextField.setVisible(true);
        newQuestionButton.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

    @Override
    public void run() {
        //
    }

    protected void addQuestion (String questionName) {
        System.out.println("add question: " + questionName);

        //create question entry and add to list
        QuestionEntry entry = new QuestionEntry(questionName);
        this.questionMap.put(questionName, entry);

        //refresh listview
        this.refreshListView();
    }

    protected void refreshListView () {

        this.questionList.getItems().clear();

        //create list with all questions
        ObservableList<String> questions = FXCollections.observableArrayList();

        //iterate through all questions
        for (Map.Entry<String,QuestionEntry> entry : this.questionMap.entrySet()) {
            //add question name to list
            questions.add(entry.getKey());
        }

        this.questionList.setItems(questions);
    }

}
