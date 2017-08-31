package de.leeksanddragons.tools.dialog.model.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.controller.TransitionPaneController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import org.json.JSONObject;

/**
 * Transition which closes the dialog
 *
 * Created by Justin on 30.08.2017.
 */
public class QuitDialogTransition extends Transition {

    @Override
    public FXMLController createFXMLController(TransitionPaneController paneController, QuestionEntry entry, int index) {
        return null;
    }

    @Override
    public String getFXMLPath() {
        return null;
    }

    @Override
    public String getType() {
        return "quit_dialog";
    }

    @Override
    public String getDescription() {
        return "quit dialog";
    }

    @Override
    public String getIconPath() {
        return "data/icons/cancel_48.png";
    }

    @Override
    protected void addJSONParams(JSONObject json) {
        //
    }

}
