package com.ekalips.instastudy.main.mvvm.model.messages;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.io.File;

/**
 * Created by ekalips on 11/30/17.
 */

public class SelectableFile extends BaseObservable {

    private final File file;
    @Bindable
    private boolean selected;

    public SelectableFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
    }

    @Override
    public boolean equals(Object obj) {
        return file.equals(obj) || super.equals(obj);
    }

    @Override
    public int hashCode() {
        return file.hashCode();
    }
}
