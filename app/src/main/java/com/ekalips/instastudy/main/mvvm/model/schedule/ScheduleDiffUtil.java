package com.ekalips.instastudy.main.mvvm.model.schedule;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.ekalips.instastudy.data.lessons.models.Lesson;

import java.util.List;

/**
 * Created by Ekalips on 11/14/17.
 */

public class ScheduleDiffUtil extends DiffUtil.Callback {

    private static final String TAG = ScheduleDiffUtil.class.getSimpleName();

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
        LessonDay oldDay = oldList.get(oldItemPosition);
        LessonDay newDay = newList.get(newItemPosition);
        if (oldDay.getLessons().size() != newDay.getLessons().size()) {
            return false;
        }
        for (int i = 0; i < oldDay.getLessons().size(); i++) {
            Lesson oldLesson = (Lesson) oldDay.getLessons().get(i);
            Lesson newLesson = (Lesson) newDay.getLessons().get(i);
            if (!TextUtils.equals(oldLesson.getId(), newLesson.getId())) {
                return false;
            }
        }
        return true;
    }
}
