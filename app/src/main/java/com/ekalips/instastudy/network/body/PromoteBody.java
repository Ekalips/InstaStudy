package com.ekalips.instastudy.network.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ekalips on 12/5/17.
 */

public class PromoteBody {

    @SerializedName("code")
    @Expose
    private final String code;

    public PromoteBody(String code) {
        this.code = code;
    }
}
