package com.wonderslab.base.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.wonderslab.base.recyclerview.DataSetInterface;
import com.wonderslab.base.recyclerview.PaginatedDataSetInterface;

import java.util.List;
import java.util.Objects;

/**
 * Created by Ekalips on 11/13/17.
 */

public class Bindings {


    @BindingAdapter({"src"})
    @SuppressWarnings("all")
    public static void setDataToRecyclerView(RecyclerView recyclerView, List data) {
        if (data != null && recyclerView.getAdapter() instanceof DataSetInterface) {
            ((DataSetInterface) recyclerView.getAdapter()).setData(data);
        }
    }

    @BindingAdapter({"src", "total"})
    @SuppressWarnings("all")
    public static void setDatWithTotalToRecyclerView(RecyclerView recyclerView, List data, int totalCount) {
        if (recyclerView.getAdapter() != null && recyclerView.getAdapter() instanceof PaginatedDataSetInterface) {
            if (data != null) {
                ((PaginatedDataSetInterface) recyclerView.getAdapter()).setData(data);
            }
            ((PaginatedDataSetInterface) recyclerView.getAdapter()).setTotalCount(totalCount);
        }
    }

    @BindingAdapter({"src", "adapter"})
    public static void setDataWithAdapterToRecyclerView(RecyclerView recyclerView, List data, RecyclerView.Adapter adapter) {
        if (!Objects.equals(recyclerView.getAdapter(), adapter)) {
            recyclerView.setAdapter(adapter);
        }
        setDataToRecyclerView(recyclerView, data);
    }

    @BindingAdapter({"src", "total", "adapter"})
    public static void setDataWithPaginatedAdapter(RecyclerView recyclerView, List data, int totalCount, RecyclerView.Adapter adapter) {
        if (!Objects.equals(recyclerView.getAdapter(), adapter)) {
            recyclerView.setAdapter(adapter);
        }
        setDatWithTotalToRecyclerView(recyclerView, data, totalCount);
    }

}
