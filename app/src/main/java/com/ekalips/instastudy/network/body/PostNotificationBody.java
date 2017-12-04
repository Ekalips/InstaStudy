package com.ekalips.instastudy.network.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ekalips on 12/4/17.
 */

public class PostNotificationBody {

    @SerializedName("title")
    @Expose
    private final String title;
    @SerializedName("body")
    @Expose
    private final String body;

    public PostNotificationBody(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
