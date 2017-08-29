package de.leeksanddragons.tools.dialog.model;

import de.leeksanddragons.tools.dialog.json.JSONSerializable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Justin on 29.08.2017.
 */
public class QuestionEntry implements JSONSerializable {

    /**
    * unique name of question
    */
    protected String name = "";

    /**
     * list with all languages
     */
    protected List<String> langList = new ArrayList<>();

    /**
    * map with all language entries
    */
    protected Map<String,QuestionLangEntry> entries = new HashMap<>();

    public QuestionEntry (String questionName) {
        this.name = questionName;
    }

    public List<String> getLanguages () {
        return this.langList;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        //save name
        json.put("name", this.name);

        //save supported languages
        JSONArray jsonArray = new JSONArray();

        //iterate through all supported languages
        for (Map.Entry<String,QuestionLangEntry> entry : this.entries.entrySet()) {
            jsonArray.put(entry.getValue().toJSON());
        }

        //put json array
        json.put("langs", jsonArray);

        return json;
    }
}
