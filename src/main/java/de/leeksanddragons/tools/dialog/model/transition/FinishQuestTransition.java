package de.leeksanddragons.tools.dialog.model.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.controller.TransitionPaneController;
import de.leeksanddragons.tools.dialog.javafx.controller.transition.FinishQuestTransitionController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import org.json.JSONObject;

/**
 * Created by Justin on 30.08.2017.
 */
public class FinishQuestTransition extends Transition {

    protected String uniqueQuestName = "";

    public FinishQuestTransition (String questName) {
        this.uniqueQuestName = questName;
    }

    public FinishQuestTransition () {
        //
    }

    @Override
    public FXMLController createFXMLController(TransitionPaneController paneController, QuestionEntry entry, int index) {
        return new FinishQuestTransitionController(paneController, entry, index);
    }

    @Override
    public String getFXMLPath() {
        return "./data/ui/transitions/finish_quest.fxml";
    }

    @Override
    public String getType() {
        return "finish_quest";
    }

    @Override
    public String getDescription() {
        return "finish quest '" + this.uniqueQuestName + "'";
    }

    @Override
    public String getIconPath() {
        return "data/icons/flag_48.png";
    }

    @Override
    protected void addJSONParams(JSONObject json) {
        json.put("quest_name", this.uniqueQuestName);
    }

}
