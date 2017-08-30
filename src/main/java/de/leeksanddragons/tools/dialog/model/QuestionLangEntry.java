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

    public String getText () {
        return this.text;
    }

    public void setText (String text) {
        this.text = text;
    }

    public String getChoise1Text () {
        return this.choice1;
    }

    public String getChoise2Text () {
        return this.choice2;
    }

    public String getChoise3Text () {
        return this.choice3;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        //save lang token and question text
        json.put("langToken", this.langToken);

        json.put("text", this.text);

        return json;
    }

    @Override
    public void loadFromJSON(JSONObject json) {
        this.langToken = json.getString("langToken");
        this.text = json.getString("text");
    }
}
