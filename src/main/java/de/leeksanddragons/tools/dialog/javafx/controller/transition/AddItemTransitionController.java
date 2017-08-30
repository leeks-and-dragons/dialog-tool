package de.leeksanddragons.tools.dialog.javafx.controller.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import de.leeksanddragons.tools.dialog.model.transition.AddItemTransition;
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
public class AddItemTransitionController implements FXMLController, Initializable {

    @FXML
    protected TextField itemTextField;

    @FXML
    protected TextField itemCountTextField;

    @FXML
    protected Button addButton;

    protected QuestionEntry entry = null;
    protected int index = 0;

    public AddItemTransitionController (QuestionEntry entry, int index) {
        this.entry = entry;
        this.index = index;
    }

    @Override
    public void init(Stage stage, Scene scene, Pane pane) {
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //add transition

                String itemName = itemTextField.getText();

                if (itemName.isEmpty()) {
                    return;
                }

                String itemCountText = itemCountTextField.getText();

                if (itemCountText.isEmpty()) {
                    return;
                }

                int itemCount = 0;

                try {
                    itemCount = Integer.parseInt(itemCountText);
                } catch (Exception e) {
                    return;
                }

                AddItemTransition transition = new AddItemTransition(itemName, itemCount);
                entry.getTranstionList(index).add(transition);

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
