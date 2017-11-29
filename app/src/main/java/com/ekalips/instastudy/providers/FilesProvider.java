package com.ekalips.instastudy.providers;

import android.content.Context;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by ekalips on 11/30/17.
 */

@Singleton
public class FilesProvider {

    private final Context context;

    @Inject
    public FilesProvider(Context context) {
        this.context = context;
    }

    public File createImageFile() {
        File dir = context.getFilesDir();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, "Image" + System.currentTimeMillis());
    }
}
