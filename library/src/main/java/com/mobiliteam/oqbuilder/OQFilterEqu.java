package com.mobiliteam.oqbuilder;

/**
 * Created by swapnilnandgave on 17/03/18.
 */

public enum OQFilterEqu {

    eq {
        @Override
        public String toString() {
            return " eq ";
        }
    },
    lt {
        @Override
        public String toString() {
            return " lt ";
        }
    },
    gt {
        @Override
        public String toString() {
            return " gt ";
        }
    },
    le {
        @Override
        public String toString() {
            return " le ";
        }
    },
    ge {
        @Override
        public String toString() {
            return " ge ";
        }
    },
    contains {
        @Override
        public String toString() {
            return "contains";
        }
    },
    endswith {
        @Override
        public String toString() {
            return "endswith";
        }
    },
    startswith {
        @Override
        public String toString() {
            return "startswith";
        }
    }

}
