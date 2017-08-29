package de.leeksanddragons.tools.dialog.i18n;

import de.leeksanddragons.tools.dialog.Main;
import de.leeksanddragons.tools.dialog.model.LangEntry;
import de.leeksanddragons.tools.dialog.utils.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 29.08.2017.
 */
public class LangLoader {

    /**
    * list with all supported languages
    */
    protected List<LangEntry> list = new ArrayList<>();

    protected int retrys = 0;

    public LangLoader () {
        //
    }

    public void load (String fileName) throws IOException {
        //check, if file exists or throw an exception
        if (!(new File(fileName).exists())) {
            throw new FileNotFoundException("Could not found language file: " + fileName);
        }

        //read content from file
        String content = FileUtils.readFile(fileName, StandardCharsets.UTF_8);

        //create json object from json string
        JSONObject json = new JSONObject(content);

        //get version of dialog tool
        int toolVersion = json.getInt("tool_version");

        //check, if file is supported
        if (toolVersion > Main.VERSION_NUMBER) {
            System.err.println("Could not load language.json, because file belongs to an newer version of this tool.");

            //rename old file
            FileUtils.moveFile(fileName, fileName.substring(0, fileName.length() - 5) + "_backup_" + System.currentTimeMillis() + ".json");

            //create an new file
            LangInitializer.init(fileName);

            retrys++;

            if (retrys > 3) {
                throw new RuntimeException("Infite loop found in class LangLoader! Maybe LangInitializer writes an wrong tool version?");
            }

            //load file again
            this.load(fileName);
        }


    }

    public List<LangEntry> listSupportedLanguages () {
        return this.list;
    }

}
