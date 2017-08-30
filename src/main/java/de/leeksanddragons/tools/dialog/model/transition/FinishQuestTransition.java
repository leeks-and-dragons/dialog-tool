package de.leeksanddragons.tools.dialog.model.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import org.json.JSONObject;

/**
 * Created by Justin on 30.08.2017.
 */
public class FinishQuestTransition extends Transition {

    protected String uniqueQuestName = "";

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
        return "finish_quest";
    }

    @Override
    public String getIconPath() {
        return "data/icons/flag_48.png";
    }

    @Override
    protected void addJSONParams(JSONObject json) {
        //
    }

}
