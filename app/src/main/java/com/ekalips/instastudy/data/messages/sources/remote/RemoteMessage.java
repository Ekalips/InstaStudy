package com.ekalips.instastudy.data.messages.sources.remote;

import com.ekalips.instastudy.data.files.models.File;
import com.ekalips.instastudy.data.files.source.remote.RemoteFileOrDirectoryEntity;
import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.data.user.source.network.model.RemoteUserData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

/**
 * Created by Ekalips on 11/6/17.
 */

public class RemoteMessage implements Message {

    @SerializedName(value = "_id", alternate = "messageId")
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
    @SerializedName("file")
    @Expose
    private RemoteFileOrDirectoryEntity file;

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

    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setSender(RemoteUserData sender) {
        this.sender = sender;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    @Nullable
    @Override
    public File getFile() {
        return file;
    }
}
