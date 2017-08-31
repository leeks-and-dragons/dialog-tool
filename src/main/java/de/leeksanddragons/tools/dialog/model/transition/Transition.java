package de.leeksanddragons.tools.dialog.model.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.controller.TransitionPaneController;
import de.leeksanddragons.tools.dialog.json.JSONSerializable;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Justin on 30.08.2017.
 */
public abstract class Transition implements JSONSerializable {

    /**
    * map with all transition types
    */
    protected static final Map<String,Transition> transitionTitlesMap = new HashMap<>();
    protected static final Map<String,Transition> transitionTypesMap = new HashMap<>();
    protected static final Map<String,String> typeToTitleMap = new HashMap<>();

    static {
        transitionTitlesMap.put("Give Item", new AddItemTransition());
        transitionTitlesMap.put("Finish Quest", new FinishQuestTransition());
        transitionTitlesMap.put("Give Quest", new GiveQuestTransition());
        transitionTitlesMap.put("Next Question", new QuestionTransition());
        transitionTitlesMap.put("Quit Dialog", new QuitDialogTransition());
        transitionTitlesMap.put("Raise Event", new RaiseEventTransition());

        //iterate through all transition types
        for (Map.Entry<String,Transition> entry : transitionTitlesMap.entrySet()) {
            transitionTypesMap.put(entry.getValue().getType(), entry.getValue());

            typeToTitleMap.put(entry.getValue().getType(), entry.getKey());
        }
    }

    protected Transition () {
        //
    }

    public abstract FXMLController createFXMLController (TransitionPaneController paneController, QuestionEntry entry, int index);

    public abstract String getFXMLPath ();

    public abstract String getType ();

    public abstract String getDescription ();

    public abstract String getIconPath ();

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        //save type
        json.put("type", this.getType());

        addJSONParams(json);

        return json;
    }

    protected abstract void addJSONParams (JSONObject json);

    public static Transition createFromJSON (JSONObject json) {
        //TODO: add code here

        return null;
    }

    public static List<String> listTransitionTypeNames () {
        List<String> list = new ArrayList<>();

        for (Map.Entry<String,Transition> entry : transitionTitlesMap.entrySet()) {
            list.add(entry.getKey());
        }

        return list;
    }

    public static Transition getTransitionByType (String title) {
        if (!transitionTypesMap.containsKey(title)) {
            return null;
        }

        return transitionTypesMap.get(title);
    }

    public static Transition getTransitionByTitle (String title) {
        if (!transitionTitlesMap.containsKey(title)) {
            return null;
        }

        return transitionTitlesMap.get(title);
    }

    public static String getTransitionTitleByType (String type) {
        if (!typeToTitleMap.containsKey(type)) {
            return "Unknown Title";
        }

        return typeToTitleMap.get(type);
    }

}
