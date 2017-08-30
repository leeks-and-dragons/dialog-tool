package de.leeksanddragons.tools.dialog.exception;

/**
 * Exception which is thrown, if an opened dialog requires an newer version of this tool and so this tool cannot parse the file format.
 *
 * Created by Justin on 30.08.2017.
 */
public class IncompatibleVersionException extends Exception {

    public IncompatibleVersionException (String message) {
        super(message);
    }

}
