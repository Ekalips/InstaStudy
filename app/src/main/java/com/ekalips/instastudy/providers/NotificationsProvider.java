package com.ekalips.instastudy.providers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;

import com.ekalips.instastudy.R;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/1/17.
 */

public class NotificationsProvider {

    public enum Channel {
        General("general");

        private String channelId;

        Channel(String channelId) {
            this.channelId = channelId;
        }

        public String getChannelId() {
            return channelId;
        }
    }

    private final Context context;
    private final NotificationManagerCompat notificationManagerCompat;
    private final NotificationManager notificationManager;

    @Inject
    public NotificationsProvider(Context context) {
        this.context = context;
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.notificationManagerCompat = NotificationManagerCompat.from(context);
        checkAndInitChannels();
    }

    private void checkAndInitChannels() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }
        for (Channel ch :
                Channel.values()) {
            NotificationChannel channel = notificationManager.getNotificationChannel(ch.getChannelId());
            if (channel == null) {
                channel = new NotificationChannel(ch.getChannelId(), ch.name(), NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
            }
        }

    }

    public void showSimpleNotification(int id, @StringRes int title, @StringRes int message) {
        showSimpleNotification(id, context.getString(title), context.getString(message), Channel.General);
    }

    public void showSimpleNotification(int id, String title, String message) {
        showSimpleNotification(id, title, message, Channel.General);
    }

    public void showSimpleNotification(int id, String title, String message, Channel channel) {
        showNotification(id, title, message, channel);
    }

    public void cancelNotification(int id) {
        cancelNotification(null, id);
    }

    public void cancelNotification(@Nullable String tag, int id) {
        cancelNotificationInternal(tag, id);
    }

    private void showNotification(int id, String title, String message, @Nullable Channel channel) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(false)
                .setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setStyle(new Notification.BigTextStyle()
                        .bigText(message));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && channel != null) {
            builder.setChannelId(channel.getChannelId());
        }

        notificationManagerCompat.notify(id, builder.build());
    }

    private void cancelNotificationInternal(@Nullable String tag, int id) {
        notificationManagerCompat.cancel(tag, id);
    }
}
