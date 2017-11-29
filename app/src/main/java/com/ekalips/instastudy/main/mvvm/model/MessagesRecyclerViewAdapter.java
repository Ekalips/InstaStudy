package com.ekalips.instastudy.main.mvvm.model;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.databinding.RvItemMessageBinding;
import com.wonderslab.base.recyclerview.BindingViewHolder;
import com.wonderslab.base.recyclerview.PaginatedRecyclerViewAdapter;

import java.util.List;

/**
 * Created by Ekalips on 11/13/17.
 */

public class MessagesRecyclerViewAdapter extends PaginatedRecyclerViewAdapter<RvItemMessageBinding, Message> {

    private String myUserId = "";

    public MessagesRecyclerViewAdapter(@Nullable PaginatedListCallbacks callbacks) {
        super(callbacks);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.rv_item_message;
    }

    @Override
    public void onBindRegularHolder(BindingViewHolder<RvItemMessageBinding> holder, int position) {
        Message currentMessage = getData().get(holder.getAdapterPosition());

        holder.getBinding().setMessage(getData().get(holder.getAdapterPosition()));
        holder.getBinding().setIsMe(TextUtils.equals(myUserId, currentMessage.getAuthor() != null ? currentMessage.getAuthor().getUserId() : null));


        Message prevMessage = safeGet(holder.getAdapterPosition() - 1);

        if (prevMessage != null && currentMessage.getAuthor() != null && prevMessage.getAuthor() != null
                && TextUtils.equals(currentMessage.getAuthor().getUserId(), prevMessage.getAuthor().getUserId())) {
            holder.getBinding().setShowAvatar(false);
        } else {
            holder.getBinding().setShowAvatar(true);
        }

        holder.getBinding().executePendingBindings();
    }

    @Nullable
    private Message safeGet(int position) {
        return position < getData().size() && position >= 0 ? getData().get(position) : null;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
        setData(getData());
    }

    @Override
    public void setData(List<Message> data) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MessagesDiffUtil(this.getData(), data));
        getData().clear();
        if (data != null) {
            getData().addAll(data);
        }
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public void setTotalCount(int count) {
        super.setTotalCount(count);
    }
}
