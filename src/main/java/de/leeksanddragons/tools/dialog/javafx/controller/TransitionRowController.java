package de.leeksanddragons.tools.dialog.javafx.controller;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import de.leeksanddragons.tools.dialog.model.transition.Transition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Justin on 31.08.2017.
 */
public class TransitionRowController implements FXMLController, Initializable {

    @FXML
    protected ImageView imageView;

    @FXML
    protected Label titleLabel;

    @FXML
    protected Label descriptionLabel;

    @FXML
    protected Button editButton;

    @FXML
    protected Button deleteButton;

    protected TransitionPaneController paneController = null;
    protected QuestionEntry entry = null;
    protected int index = 0;
    protected Transition transition = null;

    public TransitionRowController (TransitionPaneController paneController, QuestionEntry entry, int index, Transition transition) {
        this.paneController = paneController;
        this.entry = entry;
        this.index = index;
        this.transition = transition;
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        imageView.setImage(new Image("file:" + transition.getIconPath()));

        //deactivate edit button, because he isnt suppoted yet
        this.editButton.setVisible(false);

        this.deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //delete transition
                delete();
            }
        });

        //set button image
        Image imageDecline = new Image("file:data/icons/trash_32.png");
        this.deleteButton.setGraphic(new ImageView(imageDecline));
        this.deleteButton.setText("");

        //set title and description
        this.titleLabel.setText(Transition.getTransitionTitleByType(this.transition.getType()));
        this.descriptionLabel.setText(this.transition.getDescription());
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    protected void delete () {
        //remove transition from list
        this.entry.getTranstionList(this.index).remove(this.transition);

        //refresh list, so entry is removed from list
        this.paneController.refreshListView();
    }

}
