package com.ekalips.instastudy.main.mvvm.model;

import android.view.ViewGroup;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.lessons.models.Lesson;
import com.ekalips.instastudy.databinding.RvItemLessonBinding;
import com.ekalips.instastudy.stuff.recyclerview.BindingRecyclerViewAdapter;
import com.ekalips.instastudy.stuff.recyclerview.BindingViewHolder;
import com.google.android.flexbox.FlexboxLayoutManager;

/**
 * Created by Ekalips on 11/8/17.
 */

public class InnerLessonRecyclerViewAdapter extends BindingRecyclerViewAdapter<RvItemLessonBinding, Lesson> {
    @Override
    public int getLayoutRes() {
        return R.layout.rv_item_lesson;
    }

    private static final String TAG = InnerLessonRecyclerViewAdapter.class.getSimpleName();

    @Override
    public BindingViewHolder<RvItemLessonBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        BindingViewHolder<RvItemLessonBinding> holder = super.onCreateViewHolder(parent, viewType);

        if (holder.itemView.getLayoutParams() instanceof FlexboxLayoutManager.LayoutParams) {
            ((FlexboxLayoutManager.LayoutParams) holder.itemView.getLayoutParams()).setFlexGrow(1);
            holder.itemView.requestLayout();
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<RvItemLessonBinding> holder, int position) {
        holder.getBinding().setLesson(getData().get(holder.getAdapterPosition()));
        holder.getBinding().executePendingBindings();
    }
}
