package com.ekalips.instastudy.stuff.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ekalips on 9/27/17.
 */

public abstract class BindingRecyclerViewAdapter<BindingView extends ViewDataBinding, DataType> extends RecyclerView.Adapter<BindingViewHolder<BindingView>> implements DataSetInterface<DataType> {

    private List<DataType> data = new ArrayList<>();

    public abstract int getLayoutRes();

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public BindingViewHolder<BindingView> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BindingViewHolder<>(getLayoutRes(), parent);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<BindingView> holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void setData(List<DataType> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public List<DataType> getData() {
        return data;
    }

}