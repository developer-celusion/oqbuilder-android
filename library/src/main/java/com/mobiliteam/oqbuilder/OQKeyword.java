package com.mobiliteam.oqbuilder;

/**
 * Created by swapnilnandgave on 17/03/18.
 */

public enum OQKeyword {

    select {
        @Override
        public String toString() {
            return "$select=";
        }
    },
    filter {
        @Override
        public String toString() {
            return "$filter=";
        }
    },
    expand {
        @Override
        public String toString() {
            return "$expand=";
        }
    },
    order {
        @Override
        public String toString() {
            return "$orderby=";
        }
    },
    skip {
        @Override
        public String toString() {
            return "$skip=";
        }
    },
    top {
        @Override
        public String toString() {
            return "$top=";
        }
    },
    count {
        @Override
        public String toString() {
            return "/$count";
        }
    }

}
