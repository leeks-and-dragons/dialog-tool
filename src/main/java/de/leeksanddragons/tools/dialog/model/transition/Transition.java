package de.leeksanddragons.tools.dialog.model.transition;

import de.leeksanddragons.tools.dialog.json.JSONLoadable;
import de.leeksanddragons.tools.dialog.json.JSONSerializable;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Justin on 30.08.2017.
 */
public abstract class Transition implements JSONSerializable, JSONLoadable {

    /**
    * map with all transition types
    */
    protected static final Map<String,Transition> transitionTypeMap = new HashMap<>();

    static {
        transitionTypeMap.put("Give Item", new AddItemTransition());
        transitionTypeMap.put("Finish Quest", new FinishQuestTransition());
        transitionTypeMap.put("Give Quest", new GiveQuestTransition());
        transitionTypeMap.put("Next Question", new QuestionTransition());
        transitionTypeMap.put("Quit Dialog", new QuitDialogTransition());
        transitionTypeMap.put("Raise Event", new RaiseEventTransition());
    }

    protected Transition () {
        //
    }

    public abstract void createNewInstance ();

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

        return null;
    }

    public static List<String> listTransitionTypeNames () {
        List<String> list = new ArrayList<>();

        for (Map.Entry<String,Transition> entry : transitionTypeMap.entrySet()) {
            list.add(entry.getKey());
        }

        return list;
    }

}
