package de.leeksanddragons.tools.dialog.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Justin on 29.08.2017.
 */
public class QuestionEntry {

    /**
    * unique name of question
    */
    protected String name = "";

    /**
     * list with all languages
     */
    protected List<String> langList = new ArrayList<>();

    /**
    * map with all language entries
    */
    protected Map<String,QuestionLangEntry> entries = new HashMap<>();

    public List<String> getLanguages () {
        return this.langList;
    }

}
