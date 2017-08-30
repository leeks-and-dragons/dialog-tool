package de.leeksanddragons.tools.dialog.javafx.controller;

import de.leeksanddragons.tools.dialog.Main;
import de.leeksanddragons.tools.dialog.i18n.LangLoader;
import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.window.OpenFileDialog;
import de.leeksanddragons.tools.dialog.javafx.window.SaveFileDialog;
import de.leeksanddragons.tools.dialog.model.LangEntry;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import de.leeksanddragons.tools.dialog.utils.FileUtils;
import de.leeksanddragons.tools.dialog.utils.JavaFXUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
    protected Button deleteButton;

    @FXML
    protected Label questionsLabel;

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

    /**
    * menu items
    */

    @FXML
    protected MenuItem newDialogMenuItem;

    @FXML
    protected MenuItem openDialogMenuItem;

    @FXML
    protected MenuItem saveDialogMenuItem;

    @FXML
    protected MenuItem savePathDialogMenuItem;

    @FXML
    protected MenuItem closeDialogMenuItem;

    @FXML
    protected MenuItem aboutMenuItem;

    protected String lastSavePath = "";

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

        this.deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (questionList.getItems().size() <= 0) {
                    return;
                }

                //search for selected item
                String selectedName = questionList.getSelectionModel().getSelectedItem();

                //remove question
                removeQuestion(selectedName);
            }
        });

        this.newQuestionTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    String questionName = newQuestionTextField.getText().trim();

                    if (!questionName.isEmpty()) {
                        //check, if question is already in list
                        if (!questionList.getItems().contains(questionName)) {
                            addQuestion(questionName);
                        }
                    }
                }
            }
        });

        this.newDialogMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO: add an confirmation dialog, if an dialog is already opened

                //create new dialog
                createNewDialog();
            }
        });

        this.openDialogMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openDialog();
            }
        });

        //set key shortcut
        this.openDialogMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        this.saveDialogMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openSaveDialog();
            }
        });

        //set key shortcut
        this.saveDialogMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        this.savePathDialogMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openSaveToDialog();
            }
        });

        this.closeDialogMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideAllWidgets();
            }
        });

        this.aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JavaFXUtils.showInfoDialog("About", "This Dialog Tool was created for gam Leeks & Dragons.\nVersion: " + Main.VERSION_NUMBER + ".\n\nhttp://leeks-and-dragons.de\nCopyright (c) 2017, All Rights Reserved.");
            }
        });

        this.questionList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                refreshTabPane();
            }
        });

        //hide all widgets, if no dialog was loaded
        this.hideAllWidgets();
    }

    protected void hideAllWidgets () {
        //hide some widgets, if no dialog was loaded
        tabPane.setVisible(false);
        questionsLabel.setVisible(false);
        questionList.setVisible(false);
        newQuestionTextField.setVisible(false);
        deleteButton.setVisible(false);
        newQuestionButton.setVisible(false);

        //disable save menu items
        this.saveDialogMenuItem.setDisable(true);
        this.savePathDialogMenuItem.setDisable(true);
        this.closeDialogMenuItem.setDisable(true);
    }

    protected void showAllWidgets () {
        //show some widgets, if no dialog was loaded
        tabPane.setVisible(true);
        questionsLabel.setVisible(true);
        questionList.setVisible(true);
        newQuestionTextField.setVisible(true);
        deleteButton.setVisible(true);
        newQuestionButton.setVisible(true);

        //enable save menu items
        this.saveDialogMenuItem.setDisable(false);
        this.savePathDialogMenuItem.setDisable(false);
        this.closeDialogMenuItem.setDisable(false);
    }

    protected void createNewDialog () {
        //clear map
        this.questionMap.clear();

        //clear last save path
        this.lastSavePath = "";

        //refresh list
        this.refreshListView();

        //refresh tab pane
        this.refreshTabPane();

        //show all widgets
        this.showAllWidgets();
    }

    protected void openSaveDialog () {
        //check, if dialog is opened
        if (!this.questionList.isVisible()) {
            JavaFXUtils.showErrorDialog("Error", "No Dialog is opened!");

            return;
        }

        if (this.lastSavePath.isEmpty()) {
            //no save path is given, so open "save to" dialog
            this.openSaveToDialog();

            return;
        }

        this.save(this.lastSavePath);
    }

    protected void openSaveToDialog () {
        //check, if dialog is opened
        if (!this.questionList.isVisible()) {
            JavaFXUtils.showErrorDialog("Error", "No Dialog is opened!");

            return;
        }

        SaveFileDialog dialog = new SaveFileDialog(this.stage);
        File saveFile = dialog.open();

        if (saveFile == null) {
            //user has aborted dialog

            return;
        }

        //set last save path
        this.lastSavePath = saveFile.getAbsolutePath();

        //save dialog
        this.save(this.lastSavePath);
    }

    protected void openDialog () {
        OpenFileDialog dialog = new OpenFileDialog(this.stage);
        File file = dialog.open();

        if (file == null) {
            //user has aborted dialog

            return;
        }

        //set last save path
        this.lastSavePath = file.getAbsolutePath();

        //open dialog
        this.load(this.lastSavePath);
    }

    protected void save (String filePath) {
        System.out.println("save path: " + filePath);

        //create new json object
        JSONObject json = new JSONObject();

        //set tool name and version
        json.put("tool_name", "Leeks & Dragons - Dialog Tool");
        json.put("tool_version", Main.VERSION_NUMBER);

        //save supported languages
        JSONArray jsonArray = new JSONArray();

        for (LangEntry langEntry : this.langLoader.listSupportedLanguages()) {
            jsonArray.put(langEntry.getTokenName());
        }

        json.put("supported_languages", jsonArray);

        //save questions
        JSONArray jsonArray1 = new JSONArray();

        //iterate through all questions of dialog
        for (Map.Entry<String,QuestionEntry> entry : this.questionMap.entrySet()) {
            jsonArray1.put(entry.getValue().toJSON());
        }

        json.put("questions", jsonArray1);

        //save file
        try {
            FileUtils.writeFile(filePath, json.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();

            JavaFXUtils.showExceptionDialog("Exception", "Exception while saving file", "An exception occurred while save file! Please report to developers! ", e);
        }
    }

    protected void load (String filePath) {
        //
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

        //add languages
        for (LangEntry langEntry : this.langLoader.listSupportedLanguages()) {
            entry.addLang(langEntry.getTokenName());
        }

        this.questionMap.put(questionName, entry);

        //refresh listview
        this.refreshListView();

        //select question
        this.questionList.getSelectionModel().select(questionName);
        this.refreshTabPane();
    }

    protected void removeQuestion (String questionName) {
        if (!JavaFXUtils.showConfirmationDialog("Remove Question", "Really remove question '" + questionName + "'?")) {
            return;
        }

        //remove question from map
        this.questionMap.remove(questionName);

        //refresh listview and tabpane
        this.refreshListView();
        this.refreshTabPane();
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

        Collections.sort(questions);

        this.questionList.setItems(questions);
    }

    protected void refreshTabPane () {
        //check, if list contains entries
        if (this.questionMap.size() <= 0) {
            //remove all tabs
            tabPane.getTabs().clear();

            return;
        }

        //clear all tabs
        tabPane.getTabs().clear();

        //search for selected item
        String selectedName = this.questionList.getSelectionModel().getSelectedItem();

        //System.out.println("selected question: " + selectedName);

        if (!this.questionMap.containsKey(selectedName)) {
            System.err.println("question map doesnt contains question name: " + selectedName);

            return;
        }

        QuestionEntry entry = this.questionMap.get(selectedName);

        //create new tabs for each language

        //iterate through all supported languages
        for (LangEntry langEntry : this.langLoader.listSupportedLanguages()) {
            Tab tab = new Tab(langEntry.getTitle());

            tabPane.getTabs().add(tab);
        }
    }

}
