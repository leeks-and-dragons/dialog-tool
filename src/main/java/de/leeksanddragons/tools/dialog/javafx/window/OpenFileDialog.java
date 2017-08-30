package de.leeksanddragons.tools.dialog.javafx.window;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Justin on 30.08.2017.
 */
public class OpenFileDialog {

    protected Stage stage = null;
    protected FileChooser fileChooser = new FileChooser();

    public OpenFileDialog() {
        //create an new stage
        this.stage = new Stage();

        this.stage.setTitle("Open...");

        this.stage.hide();
    }

    public OpenFileDialog(Stage stage) {
        //create an new stage
        this.stage = stage;
    }

    public File open () {
        //this.stage.setTitle("Save to...");

        this.stage.show();

        fileChooser.setTitle("Open...");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Leeks & Dragons Dialog File", "*.ldf"),
                new FileChooser.ExtensionFilter("Dialog File", "*.dlf")
        );

        return fileChooser.showOpenDialog(stage);
    }

}
