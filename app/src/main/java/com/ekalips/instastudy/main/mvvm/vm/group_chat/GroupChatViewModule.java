package com.ekalips.instastudy.main.mvvm.vm.group_chat;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.groups.GroupDataProvider;
import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.data.messages.MessageDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.main.contract.GroupChatScreenContract;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.network.response.PaginatedListResponse;
import com.ekalips.instastudy.providers.ToastProvider;
import com.ekalips.instastudy.stuff.StringUtils;
import com.wonderslab.base.rx.RxRequests;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/6/17.
 */

public class GroupChatViewModule extends GroupChatScreenContract.ViewModel {

    private static final String TAG = GroupChatViewModule.class.getSimpleName();

    private final MainActivityContract.FlexibleMainToolbar flexibleMainToolbar;
    private final GroupDataProvider groupDataProvider;
    private final MessageDataProvider messageDataProvider;
    private final ToastProvider toastProvider;

    private final ObservableField<Group> group = new ObservableField<>(null);
    private final ObservableInt totalMessagesCount = new ObservableInt(0);
    private final ObservableField<List<Message>> messages = new ObservableField<>(new ArrayList<>());

    private String groupId;

    @Inject
    public GroupChatViewModule(MainActivityContract.FlexibleMainToolbar flexibleMainToolbar, @DataProvider GroupDataProvider groupDataProvider,
                               @DataProvider MessageDataProvider messageDataProvider, RxRequests rxRequests, ToastProvider toastProvider) {
        super(rxRequests);
        this.messageDataProvider = messageDataProvider;
        this.flexibleMainToolbar = flexibleMainToolbar;
        this.groupDataProvider = groupDataProvider;
        this.toastProvider = toastProvider;
    }

    @Override
    public void init(String groupId) {
        this.groupId = groupId;
        if (StringUtils.isEmpty(groupId)) {
            request(groupDataProvider.getMainGroup(true), data -> setUpGroup(data.getData()), this::onGetGroupError);
        } else {
            request(groupDataProvider.getGroup(groupId, true), data -> setUpGroup(data.getData()), this::onGetGroupError);
        }
        requestMessages(0);
    }

    @Override
    public ObservableInt getTotalCount() {
        return totalMessagesCount;
    }

    @Override
    public ObservableField<List<Message>> getMessages() {
        return messages;
    }

    private void requestMessages(int offset) {
        if (StringUtils.isEmpty(groupId)) {
            request(messageDataProvider.getMainGroupMessages(offset), data -> onGetMessagesSuccess(data.getData()), this::onGetMessagesError);
        } else {
            request(messageDataProvider.getGroupMessages(groupId, offset), data -> onGetMessagesSuccess(data.getData()), this::onGetMessagesError);
        }
    }

    private void onGetMessagesSuccess(PaginatedListResponse<? extends Message> messages) {
        totalMessagesCount.set(messages.getCount());
        this.messages.get().removeAll(messages.getData());
        this.messages.get().addAll(messages.getData());
        this.messages.notifyChange();
    }

    private void onGetMessagesError(Throwable throwable) {
        Log.e(TAG, "onGetMessagesError: ", throwable);
    }

    @Override
    public void requestNextPage() {
        requestMessages(messages.get().size());
    }

    private void setUpGroup(Group group) {
        this.group.set(group);
        flexibleMainToolbar.onToolbarTitleChange(group.getTitle());
    }

    private void onGetGroupError(Throwable throwable) {
        Log.e(TAG, "onGetGroupError: ", throwable);
        toastProvider.showToast(R.string.error_get_group);
    }

    @Override
    public void sendMessage(CharSequence message) {
        if (!StringUtils.isEmpty(message)) {
            request(messageDataProvider.sendMessage(group.get().getId(), message.toString().trim()), data -> onNewMessage(data.getData()), this::onMessageSendError);
            if (view != null) {
                view.clearInput();
            }
        }
    }

    private void onNewMessage(Message message) {
        totalMessagesCount.set(totalMessagesCount.get() + 1);
        this.messages.get().remove(message);
        this.messages.get().add(0, message);
        this.messages.notifyChange();

        if (message.isMine() && view != null) {
            view.scrollToBottom();
        }
    }

    private void onMessageSendError(Throwable throwable) {
        Log.e(TAG, "onMessageSendError: ", throwable);
        toastProvider.showToast(R.string.error_send_message);
    }
}
