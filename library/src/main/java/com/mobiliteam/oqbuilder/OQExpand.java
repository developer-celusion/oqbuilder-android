package com.mobiliteam.oqbuilder;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by swapnilnandgave on 17/03/18.
 */

public class OQExpand {

    private ArrayList<String> properties;
    //private OQFilterInfo filter = null;
    private List<OQFilterInfo> filters = new ArrayList<>();
    private ArrayList<String> select;
    private List<OQExpand> expands = new ArrayList<>();

    public OQExpand(String... properties) {
        this(null, properties);
    }

    public OQExpand(String property, OQExpand nested) {
        this(nested, property);
    }

    public OQExpand(String[] properties, OQExpand nested) {
        this(nested, properties);
    }

    public OQExpand(OQExpand nested, String... properties) {
        if (properties.length > 0) {
            this.properties = new ArrayList<>(Arrays.asList(properties));
            //Collections.addAll(this.orders,orders);
            this.filters = new ArrayList<>();
            this.select = new ArrayList<>();
            addExpands(nested);
        } else {
            throw new RuntimeException("Expand properties should not be empty");
        }
    }

    public void addExpands(OQExpand... expands) {
        for(OQExpand expand:expands) {
            if(expand != null) {
                this.expands.add(expand);
            }
        }
        //this.expands.addAll(new ArrayList<>(Arrays.asList(expands)));
    }

//    public void setFilter(OQFilterInfo filter) {
//        this.filters.add(filter);
//        //this.filter = filter;
//    }

    public void setFilters(OQFilterInfo... filters) {
        for (OQFilterInfo filter : filters) {
            this.filters.add(filter);
        }
        //this.filter = filter;
    }

    public void setSelect(String... select) {
        if (select.length > 0) {
            this.select = new ArrayList<>(Arrays.asList(select));
            //Collections.addAll(this.orders,orders);
        }
    }

    public void setExpand(OQExpand expand) {
        this.expands = new ArrayList<>();
        this.expands.add(expand);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TextUtils.join(",", properties));
        StringBuilder bracketPartBuilder = new StringBuilder();
        if (select.size() > 0) {
            bracketPartBuilder.append(OQKeyword.select.toString());
            bracketPartBuilder.append(TextUtils.join(",", select));
        }
//        if(filter != null) {
//            requestPart(bracketPartBuilder);
//            bracketPartBuilder.append(OQKeyword.filter.toString());
//            bracketPartBuilder.append(filter.toString());
//        }
        if (filters.size() > 0) {
            requestPart(bracketPartBuilder);
            bracketPartBuilder.append(OQKeyword.filter.toString());
            for (OQFilterInfo filter : filters) {
                bracketPartBuilder.append(filter.toString());
            }
        }
        if (expands.size() > 0) {
            requestPart(bracketPartBuilder);
            bracketPartBuilder.append(OQKeyword.expand.toString());
            for (int i = 0; i < expands.size(); i++) {
                OQExpand item = expands.get(i);
                if(i > 0) {
                    bracketPartBuilder.append(",");
                }
                bracketPartBuilder.append(item.toString());
            }
        }
        if (bracketPartBuilder.toString().trim().length() > 0) {
            stringBuilder.append("(" + bracketPartBuilder.toString() + ")");
        }
        return stringBuilder.toString();
    }

    private void requestPart(StringBuilder stringBuilder) {
        if (stringBuilder.toString().length() > 0) {
            stringBuilder.append(";");
        }
    }

}
