package com.ekalips.instastudy.data.files.models;

/**
 * Created by djqrj on 12/3/2017.
 */

public interface FileOrDirectory extends File, Directory {

    boolean isFile();

    boolean isDirectory();

}
