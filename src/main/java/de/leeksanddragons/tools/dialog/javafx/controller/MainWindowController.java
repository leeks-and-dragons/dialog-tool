package de.leeksanddragons.tools.dialog.javafx.controller;

import de.leeksanddragons.tools.dialog.Main;
import de.leeksanddragons.tools.dialog.exception.IncompatibleVersionException;
import de.leeksanddragons.tools.dialog.i18n.LangLoader;
import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.SaveableTab;
import de.leeksanddragons.tools.dialog.javafx.window.OpenFileDialog;
import de.leeksanddragons.tools.dialog.javafx.window.SaveFileDialog;
import de.leeksanddragons.tools.dialog.model.LangEntry;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import de.leeksanddragons.tools.dialog.model.QuestionLangEntry;
import de.leeksanddragons.tools.dialog.utils.FileUtils;
import de.leeksanddragons.tools.dialog.utils.JavaFXUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public static final String TAB_FXML_PATH = "./data/ui/tab.fxml";
    public static final String TRANSITION_TAB_FXML_PATH = "./data/ui/transition_tab.fxml";

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
    protected static Map<String,QuestionEntry> questionMap = new HashMap<>();

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

    /**
    * list with all opened tabs
    */
    protected List<SaveableTab> openTabs = new ArrayList<>();

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

        //set button image
        Image imageDecline = new Image("file:data/icons/cancel_16.png");
        this.deleteButton.setGraphic(new ImageView(imageDecline));
        this.deleteButton.setText("");

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
                JavaFXUtils.showInfoDialog("About", "This Dialog Tool was created for game Leeks & Dragons.\nVersion: " + Main.VERSION_NUMBER + ".\nAuthor: http://jukusoft.com\n\nhttp://leeks-and-dragons.de\nCopyright (c) 2017 leeks-and-dragons.de, All Rights Reserved.");
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

        this.deleteButton.setVisible(false);
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
        try {
            this.load(this.lastSavePath);
        } catch (IncompatibleVersionException e) {
            //version isnt supported by this tool
            JavaFXUtils.showErrorDialog("Error", "Cannot open this Dialog, because dialog was created with an newer version of this tool (requested: " + e.getRequestedVersion() + ", tool version: " + Main.VERSION_NUMBER + ").");

            //hide all widgets
            hideAllWidgets();

            return;
        }

        //refresh list
        this.refreshListView();

        //refresh tab pane
        this.refreshTabPane();

        //show all widgets
        this.showAllWidgets();
    }

    protected void saveOpenTabs () {
        for (SaveableTab tab : this.openTabs) {
            tab.saveTab();
        }
    }

    protected void save (String filePath) {
        //save all opened tab to entries first
        this.saveOpenTabs();

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

    protected void load (String filePath) throws IncompatibleVersionException {
        //clear map
        this.questionMap.clear();

        JSONObject json = null;

        try {
            String content = FileUtils.readFile(filePath, StandardCharsets.UTF_8);
            json = new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            JavaFXUtils.showExceptionDialog("Exception", "Exception while loading file", "An exception occurred while loading file! Please report developers!", e);

            return;
        }

        //check tool version
        int requestedToolVersion = json.getInt("tool_version");
        System.out.println("requested tool version: " + requestedToolVersion);

        if (requestedToolVersion > Main.VERSION_NUMBER) {
            throw new IncompatibleVersionException("requested version: " + requestedToolVersion + ", tool version: " + Main.VERSION_NUMBER + ".", requestedToolVersion);
        }

        //get all supported languages
        JSONArray jsonArray = json.getJSONArray("supported_languages");

        List<String> supportedLangTokens = this.langLoader.listSupportedLanguageTokens();

        for (int i = 0; i < jsonArray.length(); i++) {
            String langToken = jsonArray.getString(i);

            if (!supportedLangTokens.contains(langToken)) {
                //language isnt supported by this tool
                JavaFXUtils.showInfoDialog("Information", "Language token '" + langToken + "' isnt originally supported by this tool, so this lang token was appended, so you can work with it.");

                this.langLoader.addLang(langToken, "Unknown: " + langToken);
            }
        }

        //load questions
        JSONArray jsonArray1 = json.getJSONArray("questions");

        //iterate through all questions
        for (int i = 0; i < jsonArray1.length(); i++) {
            JSONObject jsonObject = jsonArray1.getJSONObject(i);

            String name = jsonObject.getString("name");
            this.questionMap.put(name, QuestionEntry.createFromJSON(jsonObject));
        }
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

        //refresh listview
        this.refreshListView();

        //select another item
        if (this.questionList.getItems().size() > 0) {
            this.questionList.getSelectionModel().select(questionList.getItems().size() - 1);
        }

        //refresh tabpane
        this.refreshTabPane();
    }

    public static List<String> listQuestions () {
        List<String> list = new ArrayList<>();

        for (Map.Entry<String,QuestionEntry> entry : questionMap.entrySet()) {
            list.add(entry.getKey());
        }

        return list;
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

        if (questions.size() <= 0) {
            //hide delete button
            this.deleteButton.setVisible(false);
        } else {
            //show delete button
            this.deleteButton.setVisible(true);
        }

        this.questionList.setItems(questions);
    }

    protected void refreshTabPane () {
        //save all opened tab to entries first
        this.saveOpenTabs();

        //check, if list contains entries
        if (this.questionMap.size() <= 0) {
            //remove all tabs
            tabPane.getTabs().clear();

            return;
        }

        //clear all tabs
        tabPane.getTabs().clear();

        //clear open tabs list
        this.openTabs.clear();

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
            QuestionLangEntry entry1 = entry.getLang(langEntry.getTokenName());

            if (entry1 == null) {
                //create new entry
                entry1 = new QuestionLangEntry(langEntry.getTokenName());
            }

            createTab(langEntry.getTokenName(), langEntry.getTitle(), entry1);
        }

        //create transition tab
        createTransitionTab(entry);
    }

    protected void createTab (String langToken, String langTitle, QuestionLangEntry entry) {
        Tab tab = new Tab(langTitle);

        // load fxml
        try {
            FXMLLoader loader = new FXMLLoader(new File(TAB_FXML_PATH).toURI().toURL());

            //set controller
            TabController tabController = new TabController(tab, entry);
            this.openTabs.add(tabController);
            loader.setController(tabController);

            Pane rootPane = loader.load();//FXMLLoader.load(new File(fxmlPath).toURI().toURL());

            //initialize tab controller
            tabController.init(stage, null, rootPane);

            tab.setContent(rootPane);
            tab.setClosable(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        tabPane.getTabs().add(tab);
    }

    protected void createTransitionTab (QuestionEntry entry) {
        Tab tab = new Tab("Transition");

        // load fxml
        try {
            FXMLLoader loader = new FXMLLoader(new File(TRANSITION_TAB_FXML_PATH).toURI().toURL());

            //set controller
            TransitionTabController tabController = new TransitionTabController(entry);
            this.openTabs.add(tabController);
            loader.setController(tabController);

            Pane rootPane = loader.load();//FXMLLoader.load(new File(fxmlPath).toURI().toURL());

            //initialize tab controller
            tabController.init(stage, null, rootPane);

            tab.setContent(rootPane);
            tab.setClosable(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        tabPane.getTabs().add(tab);
    }

}
