package com.mobiliteam.oqbuilder;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by swapnilnandgave on 17/03/18.
 * <p>
 * OQBuilder is written in order to generate OData queries efficiently.
 * It is written according to the reference document provided by OData for ver 4.0 on their official website.
 * <p>
 * Reference link : http://docs.oasis-open.org/odata/odata/v4.0/odata-v4.0-part1-protocol.html
 * <p>
 * All the functions written in this library are as per the document.
 */

public class OQBuilder {

    private static int INVALID = -990;
    private String url;
    private String entityName;
    private ArrayList<String> selects;
    private ArrayList<OQFilterInfo> filters;
    private ArrayList<OQExpand> expands;
    private ArrayList<OQOrder> orders;
    private int skip = INVALID;
    private int top = INVALID;
    private String id = "";
    private OQCount count;
    private int skipMultiplier = -1;
    private int interval = INVALID;

    public OQBuilder(String url) {
        this.url = url;
        this.entityName = null;
        this.selects = new ArrayList<>();
        this.filters = new ArrayList<>();
        this.expands = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.skip = INVALID;
        this.top = INVALID;
        this.id = "";
        this.count = null;
    }

    public OQBuilder setEntity(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public OQBuilder select(String... properties) {
        if (properties.length > 0) {
            this.selects = new ArrayList<>(Arrays.asList(properties));
            //Collections.addAll(this.selects,properties);
        }
        return this;
    }

    public OQBuilder order(OQOrder... orders) {
        if (orders.length > 0) {
            this.orders = new ArrayList<>(Arrays.asList(orders));
            //Collections.addAll(this.orders,orders);
        }
        return this;
    }

    public OQBuilder filter(OQFilterInfo... filters) {
        if (filters.length > 0) {
            this.filters = new ArrayList<>(Arrays.asList(filters));
            //Collections.addAll(this.orders,orders);
        }
        return this;
    }

    public OQBuilder expand(OQExpand... expands) {
        for (OQExpand expand : expands) {
            this.expands.add(expand);
        }
        return this;
    }

    public OQBuilder setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    public OQBuilder setTop(int top) {
        this.top = top;
        return this;
    }

    public OQBuilder getCount(OQCount count) {
        this.count = count;
        return this;
    }

    public OQBuilder setId(Object id) {
        if (id instanceof String) {
            this.id = "('" + id + "')";
        } else {
            this.id = "(" + id.toString() + ")";
        }

        return this;
    }

    public String build() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getQueryUrlPart());

        stringBuilder.append(getCountPart());
        stringBuilder.append(getIDPart());

        if (isExp()) {
            stringBuilder.append(getExpPart());
            stringBuilder.append(getUserQueryPart());
        }
        return stringBuilder.toString();
    }

    public String toString() {
        return build();
    }

    public String encode() {
        String temp = build();
        return temp.replace(" ", "%20").replaceAll("\\+", "%2B");
    }

    public OQBuilder paginate(final int interval) {
        skipMultiplier = -1;
        this.interval = interval;
        return this;
    }

    public void nextPage() {
        skipMultiplier += 1;
        setTop(this.interval);
        setSkip(skipMultiplier * this.interval);
        //return this;
    }

    private String getQueryUrlPart() {
        if (this.url == null || this.entityName == null) {
            throw new RuntimeException("Url and entity should be provided to generate oData URL");
        }
        String temp = this.url;
        temp = temp.concat((temp.substring(temp.length() - 1)).equals("/") ? "" : "/");
        temp = temp.concat(this.entityName);
        return temp;
    }

    private String getIDPart() {
        return id;
    }

    private String getCountPart() {
        return count != null ? count.toString() : "";
    }

    private String getExpPart() {
        if (isExp()) {
            return "?";
        } else {
            return "";
        }
    }

    private String getUserQueryPart() {
        StringBuilder userPartBuilder = new StringBuilder();
        if (selects.size() > 0) {
            userPartBuilder.append(OQKeyword.select.toString());
            userPartBuilder.append(TextUtils.join(",", selects));
        }
        if (filters.size() > 0) {
            requestPart(userPartBuilder);
            userPartBuilder.append(OQKeyword.filter.toString());
            for (OQFilterInfo filterInfo : filters) {
                userPartBuilder.append(filterInfo.toString());
            }
        }
        if (expands.size() > 0) {
            requestPart(userPartBuilder);
            userPartBuilder.append(OQKeyword.expand.toString());
            ArrayList<String> expandItems = new ArrayList<>();
            for (OQExpand expand : expands) {
                //userPartBuilder.append(expand.toString());
                expandItems.add(expand.toString());
            }
            userPartBuilder.append(TextUtils.join(",", expandItems));
        }
        if (orders.size() > 0) {
            requestPart(userPartBuilder);
            userPartBuilder.append(OQKeyword.order.toString());
            ArrayList<String> tempList = new ArrayList<>();
            for (OQOrder order : orders) {
                tempList.add(order.toString());
            }
            userPartBuilder.append(TextUtils.join(",", tempList));
        }
        if (skip != INVALID) {
            requestPart(userPartBuilder);
            userPartBuilder.append(OQKeyword.skip.toString());
            userPartBuilder.append("" + skip);
        }
        if (top != INVALID) {
            requestPart(userPartBuilder);
            userPartBuilder.append(OQKeyword.top.toString());
            userPartBuilder.append("" + top);
        }
        return userPartBuilder.toString();
    }

    private void requestPart(StringBuilder stringBuilder) {
        if (stringBuilder.toString().length() > 0) {
            stringBuilder.append("&");
        }
    }

    private boolean isExp() {
        if (selects.size() > 0 || filters.size() > 0 || expands.size() > 0 || orders.size() > 0 || skip != INVALID || top != INVALID) {
            return true;
        } else {
            return false;
        }
    }

}
