package com.ekalips.instastudy.registration.mvvm.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.wonderslab.base.BR;

/**
 * Created by Ekalips on 11/5/17.
 */

public class FillDataObservable extends BaseObservable {

    @Bindable
    private String name;
    @Bindable
    private String group;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
        notifyPropertyChanged(BR.group);
    }
}
