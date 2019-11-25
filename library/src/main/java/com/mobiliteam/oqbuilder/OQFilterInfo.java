package com.mobiliteam.oqbuilder;

/**
 * Created by swapnilnandgave on 17/03/18.
 */

public class OQFilterInfo {

    private OQFilterExp expression;
    private OQFilterType filterType;
    private OQFilterInfo[] filterInfos;

    public OQFilterInfo(OQFilterExp expression) {
        this(OQFilterType.None, expression);
    }

    public OQFilterInfo(OQFilterType filterType, OQFilterExp expression) {
        this.filterType = filterType;
        this.expression = expression;
    }

    public OQFilterInfo(OQFilterType filterType, OQFilterInfo... filterInfo) {
        this.filterType = filterType;
        this.filterInfos = filterInfo;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(filterType.toString());
        if (filterInfos != null) {
            stringBuilder.append(OQSeparatorToken.openParentheses.toString());
            for (OQFilterInfo item : this.filterInfos) {
                stringBuilder.append(item.toString());
            }
            stringBuilder.append(OQSeparatorToken.closeParentheses.toString());
        } else {
            stringBuilder.append(expression.toString());
        }
        return stringBuilder.toString();
    }
}
