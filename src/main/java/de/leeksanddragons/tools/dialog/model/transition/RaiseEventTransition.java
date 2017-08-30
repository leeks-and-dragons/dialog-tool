package de.leeksanddragons.tools.dialog.model.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import org.json.JSONObject;

/**
 * Created by Justin on 30.08.2017.
 */
public class RaiseEventTransition extends Transition {

    protected String eventName = "";

    @Override
    public FXMLController createFXMLController(QuestionEntry entry, int index) {
        return null;
    }

    @Override
    public String getFXMLPath() {
        return null;
    }

    @Override
    public String getType() {
        return "raise_event";
    }

    @Override
    public String getIconPath() {
        return "data/icons/optimization_48.png";
    }

    @Override
    protected void addJSONParams(JSONObject json) {
        //
    }

}
