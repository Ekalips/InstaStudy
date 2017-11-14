package com.ekalips.instastudy.network.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekalips on 11/14/17.
 */

public class UpdateFirebaseTokenBody {

    @SerializedName("deviceToken")
    @Expose
    private final String oldToken;
    @SerializedName("newDeviceToken")
    @Expose
    private final String newToken;

    public UpdateFirebaseTokenBody(String oldToken, String newToken) {
        this.oldToken = oldToken;
        this.newToken = newToken;
    }
}
