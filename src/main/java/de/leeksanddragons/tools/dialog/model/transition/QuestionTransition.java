package de.leeksanddragons.tools.dialog.model.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import org.json.JSONObject;

/**
 * Created by Justin on 30.08.2017.
 */
public class QuestionTransition extends Transition {

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
        return "next_question";
    }

    @Override
    public String getIconPath() {
        return "data/icons/arrow_right_48.png";
    }

    @Override
    protected void addJSONParams(JSONObject json) {
        //
    }

}
