package de.leeksanddragons.tools.dialog.model.transition;

/**
 * Adds or substracts some items from player
 *
 * Created by Justin on 30.08.2017.
 */
public class AddItemTransition extends Transition {

    protected String uniqueItemName = "";
    protected int numberOfItems = 0;

    @Override
    public void createNewInstance() {

    }

    @Override
    public String getType() {
        return "add_item";
    }

    @Override
    public String getIconPath() {
        return "data/icons/box_48.png";
    }
}
