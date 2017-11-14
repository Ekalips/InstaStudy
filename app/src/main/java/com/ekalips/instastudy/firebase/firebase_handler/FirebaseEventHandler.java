package com.ekalips.instastudy.firebase.firebase_handler;

import android.util.Log;
import android.util.SparseArray;

import com.ekalips.instastudy.firebase.firebase_handler.strategies.DefaultFirebaseEventStrategy;
import com.ekalips.instastudy.firebase.firebase_handler.strategies.FirebaseEventStrategy;
import com.ekalips.instastudy.firebase.firebase_handler.strategies.NewMessageEventHandler;
import com.google.firebase.messaging.RemoteMessage;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Ekalips on 11/14/17.
 */

@Singleton
public class FirebaseEventHandler {

    private static final String TAG = FirebaseEventHandler.class.getSimpleName();

    private final SparseArray<FirebaseEventStrategy> handlers = new SparseArray<>();
    private final FirebaseEventStrategy defaultStrategy;

    @Inject
    public FirebaseEventHandler(DefaultFirebaseEventStrategy defaultStrategy,
                                NewMessageEventHandler newMessageStrategy) {
        this.defaultStrategy = defaultStrategy;
        handlers.put(defaultStrategy.getEventType(), defaultStrategy);
        handlers.put(newMessageStrategy.getEventType(), newMessageStrategy);
    }

    public void handleFirebaseEvent(RemoteMessage remoteMessage) {
        try {
            if (remoteMessage != null && remoteMessage.getData() != null) {
                int messageType = Integer.parseInt(remoteMessage.getData().get("notification_type"));
                handlers.get(messageType, defaultStrategy).handleEvent(remoteMessage.getData());
            }
        } catch (NumberFormatException ex) {
            Log.e(TAG, "handleFirebaseEvent: Can't extract 'notification_type");
        }

    }
}
