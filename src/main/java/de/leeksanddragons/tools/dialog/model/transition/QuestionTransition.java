package de.leeksanddragons.tools.dialog.model.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.controller.TransitionPaneController;
import de.leeksanddragons.tools.dialog.javafx.controller.transition.QuestionTransitionController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import org.json.JSONObject;

/**
 * Created by Justin on 30.08.2017.
 */
public class QuestionTransition extends Transition {

    protected String nextQuestion = "";

    public QuestionTransition (String nextQuestion) {
        this.nextQuestion = nextQuestion;
    }

    public QuestionTransition () {
        //
    }

    @Override
    public FXMLController createFXMLController(TransitionPaneController paneController, QuestionEntry entry, int index) {
        return new QuestionTransitionController(paneController, entry, index);
    }

    @Override
    public String getFXMLPath() {
        return "data/ui/transitions/next_question.fxml";
    }

    @Override
    public String getType() {
        return "next_question";
    }

    @Override
    public String getDescription() {
        return "Go to next question: " + this.nextQuestion;
    }

    @Override
    public String getIconPath() {
        return "data/icons/arrow_right_48.png";
    }

    @Override
    protected void addJSONParams(JSONObject json) {
        json.put("next_question", this.nextQuestion);
    }

    @Override
    public void loadParamsFromJSON(JSONObject json) {
        this.nextQuestion = json.getString("next_question");
    }

}
