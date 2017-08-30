package de.leeksanddragons.tools.dialog.model;

import de.leeksanddragons.tools.dialog.json.JSONLoadable;
import de.leeksanddragons.tools.dialog.json.JSONSerializable;
import org.json.JSONObject;

/**
 * Created by Justin on 30.08.2017.
 */
public class Transition implements JSONSerializable, JSONLoadable {

    protected Transition () {
        //
    }

    @Override
    public void loadFromJSON(JSONObject json) {
        //TODO: add code here
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        //TODO: add code here

        return json;
    }

    public static Transition createFromJSON (JSONObject json) {
        //TODO: add code here

        return new Transition();
    }

}
