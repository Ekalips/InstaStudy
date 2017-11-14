package com.ekalips.instastudy.firebase.firebase_handler;

/**
 * Created by Ekalips on 11/14/17.
 */

public enum FirebaseEventType {

    NONE(0), NEW_MESSAGE(1);

    private final int type;

    FirebaseEventType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static FirebaseEventType fromType(int type) {
        for (FirebaseEventType fe :
                FirebaseEventType.values()) {
            if (fe.getType() == type) {
                return fe;
            }
        }
        return NONE;
    }
}
