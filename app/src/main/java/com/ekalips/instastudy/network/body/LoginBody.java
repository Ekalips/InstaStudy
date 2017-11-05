package com.ekalips.instastudy.network.body;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekalips on 11/5/17.
 */

public class LoginBody {

    @SerializedName("token")
    @Expose
    private String firebaseAuthToken;

    @SerializedName("device_token")
    @Expose
    private String firebaseDeviceToken;

    public LoginBody(String firebaseAuthToken, @Nullable String firebaseDeviceToken) {
        this.firebaseAuthToken = firebaseAuthToken;
        this.firebaseDeviceToken = firebaseDeviceToken;
    }
}
