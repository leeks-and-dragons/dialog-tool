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

    }

    @Override
    public JSONObject toJSON() {
        return null;
    }

    public static Transition createFromJSON (JSONObject json) {
        return new Transition();
    }

}
