package de.leeksanddragons.tools.dialog.i18n;

import de.leeksanddragons.tools.dialog.Main;
import de.leeksanddragons.tools.dialog.utils.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Justin on 29.08.2017.
 */
public class LangInitializer {

    public static void init () {
        //generate languages file, if neccessary
        if (!(new File("./data/i18n/languages.json").exists())) {
            System.out.println("create new language file.");

            //create json object
            JSONObject json = new JSONObject();

            //store version number of dialog tool
            json.put("tool_version", Main.VERSION_NUMBER);

            JSONArray jsonArray = new JSONArray();

            //add languages
            jsonArray.put(createLang("de", "German"));
            jsonArray.put(createLang("en", "English"));

            //save array
            json.put("langs", jsonArray);

            try {
                FileUtils.writeFile("./data/i18n/languages.json", json.toString(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    protected static JSONObject createLang (String tokenName, String title) {
        JSONObject json = new JSONObject();

        json.put("tokenName", tokenName);
        json.put("title", title);

        return json;
    }

}
