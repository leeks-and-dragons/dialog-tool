package de.leeksanddragons.tools.dialog.model;

import de.leeksanddragons.tools.dialog.json.JSONLoadable;
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
public class QuestionEntry implements JSONSerializable, JSONLoadable {

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

    protected QuestionEntry () {
        //
    }

    public List<String> getLanguages () {
        return this.langList;
    }

    public void addLang (String tokenName) {
        QuestionLangEntry entry = new QuestionLangEntry(tokenName);

        this.entries.put(tokenName, entry);
        this.langList.add(tokenName);
    }

    public void removeLang (String tokenName) {
        this.entries.remove(tokenName);
        this.langList.remove(tokenName);
    }

    public QuestionLangEntry getLang (String tokenName) {
        if (!(this.entries.containsKey(tokenName))) {
            return null;
        }

        return this.entries.get(tokenName);
    }

    public String getQuestionName () {
        return this.name;
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

    @Override
    public void loadFromJSON(JSONObject json) {
        this.name = json.getString("name");

        JSONArray jsonArray = json.getJSONArray("langs");

        for (int i = 0; i < jsonArray.length(); i++) {
            QuestionLangEntry entry = QuestionLangEntry.createFromJSON(jsonArray.getJSONObject(i));

            this.entries.put(entry.getLangToken(), entry);
            this.langList.add(entry.getLangToken());
        }
    }

    public static QuestionEntry createFromJSON (JSONObject json) {
        QuestionEntry entry = new QuestionEntry();

        //load entry from JSON
        entry.loadFromJSON(json);

        return entry;
    }

}
