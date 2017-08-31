package de.leeksanddragons.tools.dialog.model.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.controller.TransitionPaneController;
import de.leeksanddragons.tools.dialog.javafx.controller.transition.RaiseEventTransitionController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import org.json.JSONObject;

/**
 * Created by Justin on 30.08.2017.
 */
public class RaiseEventTransition extends Transition {

    protected String eventName = "";

    public RaiseEventTransition (String eventName) {
        this.eventName = eventName;
    }

    public RaiseEventTransition () {
        //
    }

    @Override
    public FXMLController createFXMLController(TransitionPaneController paneController, QuestionEntry entry, int index) {
        return new RaiseEventTransitionController(paneController, entry, index);
    }

    @Override
    public String getFXMLPath() {
        return "data/ui/transitions/raise_event.fxml";
    }

    @Override
    public String getType() {
        return "raise_event";
    }

    @Override
    public String getDescription() {
        return "raise event '" + this.eventName + "'";
    }

    @Override
    public String getIconPath() {
        return "data/icons/optimization_48.png";
    }

    @Override
    protected void addJSONParams(JSONObject json) {
        json.put("event_name", this.eventName);
    }

}
