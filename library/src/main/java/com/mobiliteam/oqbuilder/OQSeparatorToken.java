package com.mobiliteam.oqbuilder;

/**
 * Created by Chetan on 11/4/18.
 */

public enum OQSeparatorToken {

    openParentheses("("),

    closeParentheses(")"),

    comma(",");

    private String name;

    OQSeparatorToken(String string) {
        this.name = string;
    }

    @Override
    public String toString() {
        return name;
    }
}
