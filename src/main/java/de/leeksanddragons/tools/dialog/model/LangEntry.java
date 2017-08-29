package de.leeksanddragons.tools.dialog.model;

/**
 * Created by Justin on 29.08.2017.
 */
public class LangEntry {

    protected String tokenName = "";
    protected String title = "";

    public LangEntry (String tokenName, String title) {
        this.tokenName = tokenName;
        this.title = title;
    }

    public String getTokenName () {
        return this.tokenName;
    }

    public String getTitle () {
        return this.title;
    }

}
