package com.wonderslab.base.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.wonderslab.base.R;
import com.wonderslab.base.databinding.RvItemLoaderBinding;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Ekalips on 11/9/17.
 */

public abstract class PaginatedRecyclerViewAdapter<BindingView extends ViewDataBinding, DataType> extends BindingRecyclerViewAdapter<ViewDataBinding, DataType>
        implements PaginatedDataSetInterface<DataType> {

    private final int TYPE_REGULAR = 0;
    private final int TYPE_LOADER = 1;

    private boolean dataSizeChanged = false;

    private int totalCount;

    @NonNull
    private WeakReference<PaginatedListCallbacks> paginatedListCallbacks;

    public PaginatedRecyclerViewAdapter() {
        this(null);
    }

    public PaginatedRecyclerViewAdapter(@Nullable PaginatedListCallbacks callbacks) {
        paginatedListCallbacks = new WeakReference<>(callbacks);
    }


    @Override
    public int getItemCount() {
        if (totalCount != 0 && getData().size() < totalCount && dataSizeChanged) {
            return super.getItemCount() + 1;
        }
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (getSafeFromList(getData(), position) != null) {
            return TYPE_REGULAR;
        } else {
            return TYPE_LOADER;
        }
    }

    @Override
    public BindingViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOADER) {
            return new BindingViewHolder<>(R.layout.rv_item_loader, parent);
        } else
            return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(BindingViewHolder<ViewDataBinding> holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ViewDataBinding> holder, int position) {
        if (holder.getBinding() instanceof RvItemLoaderBinding) {
            if (paginatedListCallbacks.get() != null) {
                paginatedListCallbacks.get().onRequestNextPage();
            }
        } else {
            onBindRegularHolder((BindingViewHolder<BindingView>) holder, position);
        }
    }

    @Nullable
    public PaginatedListCallbacks getCallbacks() {
        return paginatedListCallbacks.get();
    }

    public abstract void onBindRegularHolder(BindingViewHolder<BindingView> holder, int position);

    @Override
    public void setTotalCount(int count) {
        this.totalCount = count;
        dataSizeChanged = true;
//        notifyDataSetChanged();
    }

    @Override
    public void setData(List<DataType> data) {
        dataSizeChanged = data.size() != getData().size();
        super.setData(data);
    }

    private Object getSafeFromList(List list, int position) {
        if (position >= list.size() || position < 0) {
            return null;
        } else {
            return list.get(position);
        }
    }

    public interface PaginatedListCallbacks {

        void onRequestNextPage();

    }
}
