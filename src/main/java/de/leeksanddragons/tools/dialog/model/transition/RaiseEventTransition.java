package de.leeksanddragons.tools.dialog.model.transition;

/**
 * Created by Justin on 30.08.2017.
 */
public class RaiseEventTransition extends Transition {

    protected String eventName = "";

    @Override
    public void createNewInstance() {
        //
    }

    @Override
    public String getType() {
        return "raise_event";
    }

    @Override
    public String getIconPath() {
        return "data/icons/optimization_48.png";
    }
}
