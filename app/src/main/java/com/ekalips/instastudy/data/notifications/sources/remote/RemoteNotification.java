package com.ekalips.instastudy.data.notifications.sources.remote;

import com.ekalips.instastudy.data.notifications.models.Notification;
import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.source.network.model.RemoteUserData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ekalips on 12/4/17.
 */

public class RemoteNotification implements Notification {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("from")
    @Expose
    private RemoteUserData author;

    @SerializedName("sentAt")
    @Expose
    private long date;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    @Override
    public long getDate() {
        return date;
    }
}
