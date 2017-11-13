package com.ekalips.instastudy.main.mvvm.model;

import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.ekalips.instastudy.data.messages.Message;

import java.util.List;

/**
 * Created by Ekalips on 11/14/17.
 */

public class MessagesDiffUtil extends DiffUtil.Callback {

    private final List<Message> oldList;
    private final List<Message> newList;

    public MessagesDiffUtil(List<Message> oldList, List<Message> newList) {
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
        return TextUtils.equals(oldList.get(oldItemPosition).getId(), newList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return TextUtils.equals(oldList.get(oldItemPosition).getMessage(), newList.get(newItemPosition).getMessage());
    }
}
