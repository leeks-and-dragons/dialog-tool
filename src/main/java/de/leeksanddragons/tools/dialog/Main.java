package de.leeksanddragons.tools.dialog;

import de.leeksanddragons.tools.dialog.utils.FileUtils;
import de.leeksanddragons.tools.dialog.utils.LangInitializer;
import javafx.application.Application;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Justin on 29.08.2017.
 */
public class Main {

    public static final int VERSION_NUMBER = 1;

    public static void main (String[] args) {
        //initialize language files
        LangInitializer.init();

        //create JavaFX window
        Application.launch(JavaFXApplication.class, args);
    }

}
