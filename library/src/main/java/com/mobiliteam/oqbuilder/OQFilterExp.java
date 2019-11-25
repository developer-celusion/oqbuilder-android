package com.mobiliteam.oqbuilder;

/**
 * Created by swapnilnandgave on 17/03/18.
 */

public class OQFilterExp {

    private String property;
    private OQFilterEqu filterType;
    private String value;
    private OQPropertyEx oqProperty = null;

    public OQFilterExp(String property, OQFilterEqu filterType, String value) {
//        this.property = property;
//        this.filterType = filterType;
//        this.value = "'"+value+"'";
        this(property, filterType, value, false);
    }

    public OQFilterExp(String property, OQFilterEqu filterType, String value, boolean date) {
        this.property = property;
        this.filterType = filterType;
        if (date) {
            this.value = "" + value;
        } else {
            this.value = "'" + value + "'";
        }
    }

    public OQFilterExp(String property, OQFilterEqu filterType, Object value) {
        this.property = property;
        this.filterType = filterType;
        this.value = value.toString();
    }

    public OQFilterExp(OQPropertyEx property, OQFilterEqu filterType, Object value) {
        this.oqProperty = property;
        this.filterType = filterType;
        if (value instanceof String) {
            this.value = "'" + value + "'";
        } else {
            this.value = "" + value;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        switch (filterType) {
            case eq:
            case lt:
            case gt:
            case le:
            case ge:
                if (oqProperty != null) {
                    this.property = oqProperty.toString();
                }
                stringBuilder.append(property);
                stringBuilder.append(filterType.toString());
                stringBuilder.append(value);
                break;
            case contains:
            case endswith:
            case startswith:
                stringBuilder.append(filterType.toString());
                stringBuilder.append(OQSeparatorToken.openParentheses.toString());
                stringBuilder.append(property);
                stringBuilder.append(OQSeparatorToken.comma.toString());
                stringBuilder.append(value);
                stringBuilder.append(OQSeparatorToken.closeParentheses.toString());
                break;
        }

        return stringBuilder.toString();
    }
}
