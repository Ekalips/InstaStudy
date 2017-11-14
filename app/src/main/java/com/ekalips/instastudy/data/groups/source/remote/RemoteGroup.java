package com.ekalips.instastudy.data.groups.source.remote;

import com.ekalips.instastudy.data.groups.Group;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekalips on 11/5/17.
 */

public class RemoteGroup implements Group {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("picture")
    @Expose
    private String picture;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPicture() {
        return picture;
    }

}
