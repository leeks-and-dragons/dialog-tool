package de.leeksanddragons.tools.dialog;

import de.leeksanddragons.tools.dialog.i18n.LangLoader;
import de.leeksanddragons.tools.dialog.javafx.window.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Justin on 29.08.2017.
 */
public class JavaFXApplication extends Application {

    public JavaFXApplication () {
        //
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LangLoader langLoader = new LangLoader();

        try {
            langLoader.load("./data/i18n/languages.json");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        //create new window
        MainWindow window = new MainWindow(langLoader);
    }

}
