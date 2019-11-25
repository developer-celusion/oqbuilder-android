package com.mobiliteam.oqbuilder;

/**
 * Created by Chetan on 11/4/18.
 */

public enum OQFunction {

    length("length"),
    tolower("tolower"),
    toupper("toupper"),
    trim("trim"),
    indexof("indexof", true),
    substring("substring", true),
    year("year"),
    month("month"),
    day("day"),
    hour("hour"),
    minute("minute"),
    second("second"),
    fractionalseconds("fractionalseconds"),
    totaloffsetminutes("totaloffsetminutes");


    private String string;
    private boolean acceptValue;

    OQFunction(String string, boolean acceptValue) {
        this.string = string;
        this.acceptValue = acceptValue;
    }

    OQFunction(String string) {
        this.string = string;
        this.acceptValue = false;
    }

    @Override
    public String toString() {
        return string;
    }

    public boolean acceptValue() {
        return acceptValue;
    }
}
