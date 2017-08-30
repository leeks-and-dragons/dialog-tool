package de.leeksanddragons.tools.dialog.model.transition;

/**
 * Transition which closes the dialog
 *
 * Created by Justin on 30.08.2017.
 */
public class QuitDialogTransition extends Transition {

    @Override
    public void createNewInstance() {

    }

    @Override
    public String getType() {
        return "quit_dialog";
    }

    @Override
    public String getIconPath() {
        return "data/icons/cancel_48.png";
    }

}
