package com.mobiliteam.oqbuilder;

/**
 * Created by swapnilnandgave on 17/03/18.
 */

public enum OQFilterType {
    None {
        @Override
        public String toString() {
            return "";
        }
    },
    And {
        @Override
        public String toString() {
            return " and ";
        }
    },
    Or {
        @Override
        public String toString() {
            return " or ";
        }
    }
}
