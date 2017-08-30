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

    public boolean equals (LangEntry entry1) {
        return this.tokenName.equals(entry1.getTokenName()) && this.title.equals(entry1.getTitle());
    }

    @Override
    public boolean equals (Object obj) {
        if (!(obj instanceof LangEntry)) {
            throw new IllegalStateException("equals() only can compare LangEntrys. class of compared class " + obj.getClass() + " isnt supported.");
        }

        return this.equals((LangEntry) obj);
    }

}
