package de.leeksanddragons.tools.dialog.model.transition;

/**
 * Created by Justin on 30.08.2017.
 */
public class QuestionTransition extends Transition {
    @Override
    public void createNewInstance() {

    }

    @Override
    public String getType() {
        return "next_question";
    }

    @Override
    public String getIconPath() {
        return "data/icons/arrow_right_48.png";
    }
}
