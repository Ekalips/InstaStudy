package com.ekalips.instastudy.data.messages.sources.remote;

import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.data.other_user.OtherUser;
import com.ekalips.instastudy.data.other_user.RemoteOtherUser;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekalips on 11/6/17.
 */

public class RemoteMessage implements Message {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("createdAt")
    @Expose
    private long date;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("sender")
    @Expose
    private RemoteOtherUser sender;
    @SerializedName("isMine")
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
    public OtherUser getAuthor() {
        return sender;
    }

    @Override
    public boolean isMine() {
        return mine;
    }
}
