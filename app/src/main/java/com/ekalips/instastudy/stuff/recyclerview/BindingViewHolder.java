package com.ekalips.instastudy.stuff.recyclerview;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by Ekalips on 9/27/17.
 */

public class BindingViewHolder<BindingView extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private BindingView binding;

    private BindingViewHolder(BindingView bindingView) {
        super(bindingView.getRoot());
        this.binding = bindingView;
    }

    public BindingViewHolder(int layoutRes, ViewGroup parent) {
        this((BindingView) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutRes, parent, false));
    }

    public BindingView getBinding() {
        return binding;
    }
}
