package com.ekalips.instastudy.firebase.firebase_handler.strategies;

import java.util.Map;

/**
 * Created by Ekalips on 11/14/17.
 */

public interface FirebaseEventStrategy {

    void handleEvent(Map<String, String> data);

    int getEventType();

}
