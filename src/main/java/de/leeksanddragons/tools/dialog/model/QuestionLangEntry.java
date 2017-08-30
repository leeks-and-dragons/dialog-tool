package de.leeksanddragons.tools.dialog.model;

import de.leeksanddragons.tools.dialog.json.JSONLoadable;
import de.leeksanddragons.tools.dialog.json.JSONSerializable;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 29.08.2017.
 */
public class QuestionLangEntry implements JSONSerializable, JSONLoadable {

    protected String text = "";
    protected String langToken = "";

    protected String choice1 = "";
    protected String choice2 = "";
    protected String choice3 = "";

    public QuestionLangEntry (String langToken) {
        this.langToken = langToken;
    }

    protected QuestionLangEntry () {
        //
    }

    public String getText () {
        return this.text;
    }

    public void setText (String text) {
        this.text = text;
    }

    public String getChoise1Text () {
        return this.choice1;
    }

    public void setChoice1Text (String text) {
        this.choice1 = text;
    }

    public String getChoise2Text () {
        return this.choice2;
    }

    public void setChoice2Text (String text) {
        this.choice2 = text;
    }

    public String getChoise3Text () {
        return this.choice3;
    }

    public void setChoice3Text (String text) {
        this.choice3 = text;
    }

    public String getLangToken () {
        return this.langToken;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        //save lang token and question text
        json.put("langToken", this.langToken);
        json.put("text", this.text);

        //save choises
        json.put("choice1", this.choice1);
        json.put("choice2", this.choice2);
        json.put("choice3", this.choice3);

        return json;
    }

    @Override
    public void loadFromJSON(JSONObject json) {
        //load lang token and question text
        this.langToken = json.getString("langToken");
        this.text = json.getString("text");

        //load choices
        this.choice1 = json.getString("choice1");
        this.choice2 = json.getString("choice2");
        this.choice3 = json.getString("choice3");
    }

    public static QuestionLangEntry createFromJSON (JSONObject json) {
        QuestionLangEntry entry = new QuestionLangEntry();

        //load entry from json
        entry.loadFromJSON(json);

        return entry;
    }

}
