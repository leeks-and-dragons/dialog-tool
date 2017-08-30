package de.leeksanddragons.tools.dialog.model.transition;

/**
 * Created by Justin on 30.08.2017.
 */
public class GiveQuestTransition extends Transition {

    protected String uniqueQuestName = "";

    @Override
    public void createNewInstance() {

    }

    @Override
    public String getType() {
        return "give_quest";
    }

    @Override
    public String getIconPath() {
        return "data/icons/question_48.png";
    }
}
