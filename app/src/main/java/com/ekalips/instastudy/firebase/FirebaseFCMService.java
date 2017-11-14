package com.ekalips.instastudy.firebase;

import android.util.Log;

import com.ekalips.instastudy.BuildConfig;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Ekalips on 11/14/17.
 */

public class FirebaseFCMService extends FirebaseMessagingService {

    private static final String TAG = FirebaseFCMService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        printMessageIfDebug(remoteMessage);
    }

    private void printMessageIfDebug(RemoteMessage message) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        Log.d(TAG, "onMessageReceived: ");
        if (message.getNotification() != null) {
            Log.d(TAG, "onMessageReceived: " + message.getNotification().getBody());
        }
        if (message.getData() != null) {
            Log.d(TAG, "onMessageReceived: " + message.getData());
        }

    }
}
