package com.ekalips.instastudy.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ekalips on 11/7/17.
 */

public class PaginatedListResponse<T> {

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("items")
    @Expose
    private List<T> data;

    public int getCount() {
        return count;
    }

    public List<T> getData() {
        return data;
    }
}
