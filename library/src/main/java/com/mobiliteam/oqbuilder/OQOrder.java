package com.mobiliteam.oqbuilder;

/**
 * Created by swapnilnandgave on 17/03/18.
 */

public class OQOrder {

    private String property;
    private OQOrderType orderType;

    public OQOrder(String property) {
        this(property, OQOrderType.ASC);
    }

    public OQOrder(String property, OQOrderType orderType) {
        this.property = property;
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(property);
        stringBuilder.append(orderType.toString());
        return stringBuilder.toString();
    }
}
