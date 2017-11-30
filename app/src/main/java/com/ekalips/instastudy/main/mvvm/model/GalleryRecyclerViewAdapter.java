package com.ekalips.instastudy.main.mvvm.model;

import android.view.View;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.RvItemGalleryImageBinding;
import com.ekalips.instastudy.stuff.ClickAdapter;
import com.wonderslab.base.recyclerview.BindingRecyclerViewAdapter;
import com.wonderslab.base.recyclerview.BindingViewHolder;

import java.lang.ref.WeakReference;

/**
 * Created by ekalips on 11/30/17.
 */

public class GalleryRecyclerViewAdapter extends BindingRecyclerViewAdapter<RvItemGalleryImageBinding, SelectableFile> {

    private final WeakReference<AdapterCallbacks> adapterCallbacsWeakReference;

    public GalleryRecyclerViewAdapter(AdapterCallbacks callbacks) {
        adapterCallbacsWeakReference = new WeakReference<>(callbacks);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rv_item_gallery_image;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<RvItemGalleryImageBinding> holder, int position) {
        holder.getBinding().setFile(getData().get(holder.getAdapterPosition()));
        holder.getBinding().setOnFileClick(new ClickAdapter() {
            @Override
            public void onClick(View v) {
                if (adapterCallbacsWeakReference.get() != null) {
                    adapterCallbacsWeakReference.get().onFileClicked(getData().get(holder.getAdapterPosition()));
                }
            }
        });
    }

    public interface AdapterCallbacks {
        void onFileClicked(SelectableFile file);
    }
}
