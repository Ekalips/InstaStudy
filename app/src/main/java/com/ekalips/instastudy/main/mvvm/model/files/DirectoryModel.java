package com.ekalips.instastudy.main.mvvm.model.files;

import com.ekalips.instastudy.data.files.models.Directory;

/**
 * Created by djqrj on 12/3/2017.
 */

public class DirectoryModel implements Directory {

    private int type;
    private String path;

    public DirectoryModel(String path) {
        this.path = path;
    }

    public DirectoryModel(Directory directory) {
        this.type = directory.getType();
        this.path = directory.getPath();
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
