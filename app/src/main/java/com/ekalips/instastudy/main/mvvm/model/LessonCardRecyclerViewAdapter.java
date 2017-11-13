package com.ekalips.instastudy.main.mvvm.model;

import android.support.v7.widget.DividerItemDecoration;
import android.view.ViewGroup;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.databinding.RvItemLessonCardBinding;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.wonderslab.base.recyclerview.BindingRecyclerViewAdapter;
import com.wonderslab.base.recyclerview.BindingViewHolder;

/**
 * Created by Ekalips on 11/8/17.
 */

public class LessonCardRecyclerViewAdapter extends BindingRecyclerViewAdapter<RvItemLessonCardBinding, LessonDay> {
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
}
