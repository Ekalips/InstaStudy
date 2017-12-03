package com.ekalips.instastudy.main.mvvm.model.files;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ekalips.instastudy.BR;
import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.files.models.Directory;
import com.ekalips.instastudy.data.files.models.File;
import com.ekalips.instastudy.stuff.ClickAdapter;
import com.wonderslab.base.recyclerview.BindingViewHolder;
import com.wonderslab.base.recyclerview.DataSetInterface;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by djqrj on 12/3/2017.
 */

public class FilesRecyclerViewAdapter extends RecyclerView.Adapter<BindingViewHolder<ViewDataBinding>> implements DataSetInterface<Object> {

    private static final int TYPE_FILE = 0;
    private static final int TYPE_DIR = 1;

    private final WeakReference<AdapterCallbacks> adapterCallbacksWeakReference;

    public FilesRecyclerViewAdapter(AdapterCallbacks adapterCallbacks) {
        adapterCallbacksWeakReference = new WeakReference<>(adapterCallbacks);
    }


    private final List<Object> data = new ArrayList<>();

    @Override
    public BindingViewHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_FILE: {
                return new BindingViewHolder<>(R.layout.rv_item_file, parent);
            }
            case TYPE_DIR: {
                return new BindingViewHolder<>(R.layout.rv_item_directory, parent);
            }
        }
        throw new IllegalArgumentException("Unknown view type: " + viewType);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ViewDataBinding> holder, int position) {
        holder.getBinding().setVariable(BR.file, data.get(holder.getAdapterPosition()));
        holder.getBinding().setVariable(BR.directory, data.get(holder.getAdapterPosition()));
        holder.getBinding().setVariable(BR.downloadClick, new ClickAdapter() {
            @Override
            public void onClick(View view) {
                if (adapterCallbacksWeakReference.get() != null) {
                    adapterCallbacksWeakReference.get().onDownloadFileClicked((File) data.get(holder.getAdapterPosition()));
                }
            }
        });
        holder.getBinding().setVariable(BR.directoryClick, new ClickAdapter() {
            @Override
            public void onClick(View view) {
                if (adapterCallbacksWeakReference.get() != null) {
                    adapterCallbacksWeakReference.get().onDirectoryClicked((Directory) data.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void setData(List<Object> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof File) {
            return TYPE_FILE;
        } else if (data.get(position) instanceof Directory) {
            return TYPE_DIR;
        }
        return -1;
    }

    public interface AdapterCallbacks {

        void onDownloadFileClicked(File file);

        void onDirectoryClicked(Directory directory);

    }
}
