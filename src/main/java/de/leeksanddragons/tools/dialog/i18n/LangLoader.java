package de.leeksanddragons.tools.dialog.i18n;

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
    }

    public List<LangEntry> listSupportedLanguages () {
        return this.list;
    }

}
