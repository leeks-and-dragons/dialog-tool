package de.leeksanddragons.tools.dialog.model.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.controller.TransitionPaneController;
import de.leeksanddragons.tools.dialog.javafx.controller.transition.GiveQuestTransitionController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import org.json.JSONObject;

/**
 * Created by Justin on 30.08.2017.
 */
public class GiveQuestTransition extends Transition {

    protected String uniqueQuestName = "";

    public GiveQuestTransition (String questName) {
        this.uniqueQuestName = questName;
    }

    public GiveQuestTransition () {
        //
    }

    @Override
    public FXMLController createFXMLController(TransitionPaneController paneController, QuestionEntry entry, int index) {
        return new GiveQuestTransitionController(paneController, entry, index);
    }

    @Override
    public String getFXMLPath() {
        return "data/ui/transitions/give_quest.fxml";
    }

    @Override
    public String getType() {
        return "give_quest";
    }

    @Override
    public String getDescription() {
        return "give new quest '" + this.uniqueQuestName + "'";
    }

    @Override
    public String getIconPath() {
        return "data/icons/question_48.png";
    }

    @Override
    protected void addJSONParams(JSONObject json) {
        json.put("quest_name", this.uniqueQuestName);
    }

}
