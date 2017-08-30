package de.leeksanddragons.tools.dialog.javafx.window;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Justin on 30.08.2017.
 */
public class SaveFileDialog {

    protected Stage stage = null;
    protected FileChooser fileChooser = new FileChooser();

    public SaveFileDialog () {
        //create an new stage
        this.stage = new Stage();

        this.stage.setTitle("Save to...");

        this.stage.hide();
    }

    public SaveFileDialog (Stage stage) {
        //create an new stage
        this.stage = stage;
    }

    public File open () {
        //this.stage.setTitle("Save to...");

        this.stage.show();

        fileChooser.setTitle("Save to...");
        return fileChooser.showSaveDialog(stage);
    }

}
