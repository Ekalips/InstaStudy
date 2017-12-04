package com.ekalips.instastudy.main.mvvm.model.schedule;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.RvItemLessonCardBinding;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.wonderslab.base.recyclerview.BindingRecyclerViewAdapter;
import com.wonderslab.base.recyclerview.BindingViewHolder;

import java.util.List;

/**
 * Created by Ekalips on 11/8/17.
 */

public class LessonCardRecyclerViewAdapter extends BindingRecyclerViewAdapter<RvItemLessonCardBinding, LessonDay> {

    private RecyclerView recyclerView;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rv_item_lesson_card;
    }

    @Override
    public BindingViewHolder<RvItemLessonCardBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        BindingViewHolder<RvItemLessonCardBinding> holder = super.onCreateViewHolder(parent, viewType);
        holder.getBinding().recyclerView.setLayoutManager(new FlexboxLayoutManager(parent.getContext(), FlexDirection.COLUMN, FlexWrap.WRAP));
        holder.getBinding().recyclerView.setAdapter(new InnerLessonRecyclerViewAdapter());
        holder.getBinding().recyclerView.addItemDecoration(new DividerItemDecoration(parent.getContext(), DividerItemDecoration.VERTICAL));
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<RvItemLessonCardBinding> holder, int position) {
        holder.getBinding().setDay(getData().get(holder.getAdapterPosition()));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public void setData(List<LessonDay> data) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ScheduleDiffUtil(this.getData(), data));
        getData().clear();
        if (data != null) {
            getData().addAll(data);
        }
        diffResult.dispatchUpdatesTo(this);
    }
}
