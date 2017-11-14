package com.ekalips.instastudy.firebase.firebase_handler.strategies;

import android.util.Log;

import com.ekalips.instastudy.firebase.firebase_handler.FirebaseEventType;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ekalips on 11/14/17.
 */

@Singleton
public class DefaultFirebaseEventStrategy implements FirebaseEventStrategy {

    private static final String TAG = DefaultFirebaseEventStrategy.class.getSimpleName();

    @Inject
    public DefaultFirebaseEventStrategy() {
    }

    @Override
    public void handleEvent(Map<String, String> data) {
        Log.d(TAG, "Default event handler called");
    }

    @Override
    public int getEventType() {
        return FirebaseEventType.NONE.getType();
    }
}
