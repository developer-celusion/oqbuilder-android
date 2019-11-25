package com.mobiliteam.oqbuilder;

/**
 * Created by swapnilnandgave on 17/03/18.
 */

public enum OQOrderType {

    ASC {
        @Override
        public String toString() {
            return " asc";
        }
    },
    DESC {
        @Override
        public String toString() {
            return " desc";
        }
    }

}
