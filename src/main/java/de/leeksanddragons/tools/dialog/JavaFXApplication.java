package de.leeksanddragons.tools.dialog;

import de.leeksanddragons.tools.dialog.javafx.window.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Justin on 29.08.2017.
 */
public class JavaFXApplication extends Application {

    public JavaFXApplication () {
        //
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainWindow window = new MainWindow();
    }

}
