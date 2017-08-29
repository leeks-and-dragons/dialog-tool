package de.leeksanddragons.tools.dialog;

import de.leeksanddragons.tools.dialog.i18n.LangInitializer;
import de.leeksanddragons.tools.dialog.i18n.LangLoader;
import javafx.application.Application;

import java.io.IOException;

/**
 * Created by Justin on 29.08.2017.
 */
public class Main {

    public static final int VERSION_NUMBER = 1;

    public static void main (String[] args) {
        //initialize language files
        LangInitializer.init("./data/i18n/languages.json");

        //create JavaFX window
        Application.launch(JavaFXApplication.class, args);
    }

}
