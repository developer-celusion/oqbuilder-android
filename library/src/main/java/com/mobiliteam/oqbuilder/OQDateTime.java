package com.mobiliteam.oqbuilder;

/**
 * Created by Chetan on 16/4/18.
 */

public enum OQDateTime {
    now("now()"),
    mindatetime("mindatetime()");

    private String string;

    OQDateTime(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
