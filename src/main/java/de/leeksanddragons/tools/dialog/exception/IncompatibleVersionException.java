package de.leeksanddragons.tools.dialog.exception;

/**
 * Exception which is thrown, if an opened dialog requires an newer version of this tool and so this tool cannot parse the file format.
 *
 * Created by Justin on 30.08.2017.
 */
public class IncompatibleVersionException extends Exception {

    protected int requestedVersion = 0;

    public IncompatibleVersionException (String message, int requestedVersion) {
        super(message);

        this.requestedVersion = requestedVersion;
    }

    public int getRequestedVersion () {
        return this.requestedVersion;
    }

}
