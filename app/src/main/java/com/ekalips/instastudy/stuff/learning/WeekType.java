package com.ekalips.instastudy.stuff.learning;

/**
 * Created by Ekalips on 11/8/17.
 */

public enum WeekType {
    ANY(0), ODD(2), EVEN(1);

    private final int type;

    WeekType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static WeekType fromType(int type) {
        for (WeekType wt :
                WeekType.values()) {
            if (wt.getType() == type) {
                return wt;
            }
        }
        return ANY;
    }
}
