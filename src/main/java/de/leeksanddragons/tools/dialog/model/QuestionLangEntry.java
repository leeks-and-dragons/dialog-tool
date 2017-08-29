package de.leeksanddragons.tools.dialog.model;

import de.leeksanddragons.tools.dialog.json.JSONSerializable;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 29.08.2017.
 */
public class QuestionLangEntry implements JSONSerializable {

    protected String text = "";
    protected String langToken = "";

    protected String choice1 = "";
    protected String choice2 = "";
    protected String choice3 = "";

    public QuestionLangEntry (String langToken) {
        this.langToken = langToken;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        //save lang token and question text
        json.put("langToken", this.langToken);

        json.put("text", this.text);

        return json;
    }

}
