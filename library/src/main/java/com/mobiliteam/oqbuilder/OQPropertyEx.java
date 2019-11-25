package com.mobiliteam.oqbuilder;

/**
 * Created by Chetan on 11/4/18.
 */

public class OQPropertyEx {

    private OQFunction oqPropertyEx;
    private String entityName;
    private String propertyValue;

    public OQPropertyEx(OQFunction stringFun, String entityName, Object propertyValue) {
        this.oqPropertyEx = stringFun;
        this.entityName = entityName;
        if (propertyValue instanceof String) {
            this.propertyValue = "'" + propertyValue + "'";
        } else {
            this.propertyValue = "" + propertyValue;
        }
    }

//    public OQPropertyEx(OQFunction stringFun, String entityName, String propertyValue) {
//        this.oqPropertyEx = stringFun;
//        this.entityName = entityName;
//        this.propertyValue = propertyValue;
//    }

    public OQPropertyEx(OQFunction stringFun, String entityName) {
        this(stringFun, entityName, null);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(oqPropertyEx.toString());
        stringBuilder.append(OQSeparatorToken.openParentheses.toString());
        stringBuilder.append(entityName);
        if (oqPropertyEx.acceptValue()) {
            stringBuilder.append(OQSeparatorToken.comma.toString());
            stringBuilder.append(propertyValue);
        }
        stringBuilder.append(OQSeparatorToken.closeParentheses.toString());

        return stringBuilder.toString();
    }
}
