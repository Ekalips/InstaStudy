package com.ekalips.instastudy.main.mvvm.model.notifications;

import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.notifications.models.Notification;
import com.ekalips.instastudy.stuff.ClickAdapter;
import com.wonderslab.base.BR;
import com.wonderslab.base.recyclerview.BindingRecyclerViewAdapter;
import com.wonderslab.base.recyclerview.BindingViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekalips on 12/4/17.
 */

public class NotificationsRecyclerViewAdapter extends BindingRecyclerViewAdapter<ViewDataBinding, Object> {

    private static final int TYPE_NOTIFICATION = 0;
    private static final int TYPE_TIME = 1;

    private final List<ObservableBoolean> expandedStates = new ArrayList<>();

    @Override
    public BindingViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NOTIFICATION: {
                return new BindingViewHolder<>(R.layout.rv_item_notification, parent);
            }
            case TYPE_TIME: {
                return new BindingViewHolder<>(R.layout.rv_item_text, parent);
            }
        }
        throw new IllegalArgumentException("Unknown viewType: " + viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (getData().get(position) instanceof Notification) {
            return TYPE_NOTIFICATION;
        } else if (getData().get(position) instanceof String) {
            return TYPE_TIME;
        } else {
            return -1;
        }
    }

    @Override
    public int getLayoutRes() {
        return -1;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ViewDataBinding> holder, int position) {
        holder.getBinding().setVariable(BR.expanded, expandedStates.get(holder.getAdapterPosition()));
        holder.getBinding().setVariable(BR.notification, getData().get(holder.getAdapterPosition()));
        holder.getBinding().setVariable(BR.text, getData().get(holder.getAdapterPosition()));
        holder.getBinding().setVariable(BR.onExpandClick, new ClickAdapter() {
            @Override
            public void onClick(View v) {
                ObservableBoolean state = expandedStates.get(holder.getAdapterPosition());
                state.set(!state.get());
            }
        });
    }

    @Override
    public void setData(List<Object> data) {
        expandedStates.clear();
        for (int i = 0; i < data.size(); i++) {
            expandedStates.add(new ObservableBoolean());
        }
        super.setData(data);
    }
}
