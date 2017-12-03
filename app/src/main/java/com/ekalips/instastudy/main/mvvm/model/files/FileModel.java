package com.ekalips.instastudy.main.mvvm.model.files;

import com.ekalips.instastudy.data.files.models.File;

/**
 * Created by djqrj on 12/3/2017.
 */

public class FileModel implements File {

    private int type;
    private String url;
    private String title;

    public FileModel(File file) {
        this.type = file.getType();
        this.url = file.getUrl();
        this.title = file.getTitle();
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
