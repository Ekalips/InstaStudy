package com.ekalips.instastudy.network.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekalips on 11/13/17.
 */

public class SendMessageBody {

    @SerializedName("text")
    @Expose
    private final String message;

    public SendMessageBody(String message) {
        this.message = message;
    }
}
