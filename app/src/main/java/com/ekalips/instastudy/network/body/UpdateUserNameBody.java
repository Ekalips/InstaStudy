package com.ekalips.instastudy.network.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekalips on 11/5/17.
 */

public class UpdateUserNameBody {

    @SerializedName("name")
    @Expose
    private String name;

    public UpdateUserNameBody(String name) {
        this.name = name;
    }
}
