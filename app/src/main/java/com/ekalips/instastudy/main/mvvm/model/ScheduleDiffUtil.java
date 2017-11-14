package com.ekalips.instastudy.main.mvvm.model;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by Ekalips on 11/14/17.
 */

public class ScheduleDiffUtil extends DiffUtil.Callback {

    private final List<LessonDay> oldList;
    private final List<LessonDay> newList;

    public ScheduleDiffUtil(List<LessonDay> oldList, List<LessonDay> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return TextUtils.equals(oldList.get(oldItemPosition).getDayName(), newList.get(newItemPosition).getDayName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
