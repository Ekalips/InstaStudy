package com.ekalips.instastudy.data.files.source.remote;

import com.ekalips.instastudy.data.files.models.FileOrDirectory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by djqrj on 12/3/2017.
 */

public class RemoteFileOrDirectoryEntity implements FileOrDirectory {

    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("path")
    @Expose
    private String path;


    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isFile() {
        return type == 0;
    }

    @Override
    public boolean isDirectory() {
        return type == 1;
    }
}
