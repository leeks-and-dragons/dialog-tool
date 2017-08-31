package de.leeksanddragons.tools.dialog.model.transition;

import de.leeksanddragons.tools.dialog.javafx.FXMLController;
import de.leeksanddragons.tools.dialog.javafx.controller.TransitionPaneController;
import de.leeksanddragons.tools.dialog.javafx.controller.transition.AddItemTransitionController;
import de.leeksanddragons.tools.dialog.model.QuestionEntry;
import org.json.JSONObject;

/**
 * Adds or substracts some items from player
 *
 * Created by Justin on 30.08.2017.
 */
public class AddItemTransition extends Transition {

    protected String uniqueItemName = "";
    protected int numberOfItems = 0;

    public AddItemTransition (String name, int itemCount) {
        this.uniqueItemName = name;
        this.numberOfItems = itemCount;
    }

    public AddItemTransition () {
        //
    }

    @Override
    public FXMLController createFXMLController(TransitionPaneController paneController, QuestionEntry entry, int index) {
        return new AddItemTransitionController(paneController, entry, index);
    }

    @Override
    public String getFXMLPath() {
        return "./data/ui/transitions/add_item.fxml";
    }

    @Override
    public String getType() {
        return "add_item";
    }

    @Override
    public String getDescription() {
        return "add " + numberOfItems + " items of type '" + uniqueItemName + "'";
    }

    @Override
    public String getIconPath() {
        return "data/icons/box_48.png";
    }

    @Override
    protected void addJSONParams(JSONObject json) {
        json.put("item_name", this.uniqueItemName);
        json.put("item_count", this.numberOfItems);
    }
    
}
