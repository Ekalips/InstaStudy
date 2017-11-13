package com.ekalips.instastudy.data.messages.sources.remote;

import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.source.network.model.RemoteUserData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekalips on 11/6/17.
 */

public class RemoteMessage implements Message {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("text")
    @Expose
    private String message;
    @SerializedName("timestamp")
    @Expose
    private long date;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("from")
    @Expose
    private RemoteUserData sender;
    @SerializedName("isMe")
    @Expose
    private boolean mine;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public long getDate() {
        return date;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public User getAuthor() {
        return sender;
    }

    @Override
    public boolean isMine() {
        return mine;
    }
}
