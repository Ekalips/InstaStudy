package com.ekalips.instastudy.data.lessons.sources.remote.models;

import com.ekalips.instastudy.data.lessons.models.Subject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ekalips on 11/7/17.
 */

class RemoteSubject implements Subject{

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
