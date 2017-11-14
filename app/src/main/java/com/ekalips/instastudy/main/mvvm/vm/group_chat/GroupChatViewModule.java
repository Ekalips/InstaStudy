package com.ekalips.instastudy.main.mvvm.vm.group_chat;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.groups.GroupDataProvider;
import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.data.messages.MessageDataProvider;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.firebase.firebase_handler.events.NewMessageEvent;
import com.ekalips.instastudy.main.contract.GroupChatScreenContract;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.network.response.PaginatedListResponse;
import com.ekalips.instastudy.providers.ToastProvider;
import com.ekalips.instastudy.stuff.Const;
import com.ekalips.instastudy.stuff.ObservableString;
import com.ekalips.instastudy.stuff.StringUtils;
import com.ekalips.instastudy.stuff.lists.EqualableArrayList;
import com.ekalips.instastudy.stuff.lists.MessageEqualator;
import com.wonderslab.base.rx.RxRequests;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Ekalips on 11/6/17.
 */

public class GroupChatViewModule extends GroupChatScreenContract.ViewModel {

    private static final String TAG = GroupChatViewModule.class.getSimpleName();

    private final MainActivityContract.FlexibleMainToolbar flexibleMainToolbar;
    private final GroupDataProvider groupDataProvider;
    private final MessageDataProvider messageDataProvider;
    private final ToastProvider toastProvider;
    private final UserDataProvider userDataProvider;

    private final ObservableField<Group> group = new ObservableField<>(null);
    private final ObservableInt totalMessagesCount = new ObservableInt(0);
    private final ObservableField<List<Message>> messages = new ObservableField<>(new EqualableArrayList<>(new MessageEqualator()));
    private final ObservableString myId = new ObservableString("");

    private final EventBus firebaseEventBus;

    private String groupId;

    @Inject
    public GroupChatViewModule(MainActivityContract.FlexibleMainToolbar flexibleMainToolbar, @DataProvider GroupDataProvider groupDataProvider,
                               @DataProvider MessageDataProvider messageDataProvider, RxRequests rxRequests, ToastProvider toastProvider,
                               @Named(Const.NAME_FIREBASE_EVENT_BUS) EventBus firebaseEventBus, @DataProvider UserDataProvider userDataProvider) {
        super(rxRequests);
        this.messageDataProvider = messageDataProvider;
        this.flexibleMainToolbar = flexibleMainToolbar;
        this.groupDataProvider = groupDataProvider;
        this.toastProvider = toastProvider;
        this.firebaseEventBus = firebaseEventBus;
        this.userDataProvider = userDataProvider;

        request(userDataProvider.getUser(false), data -> myId.set(data.getData().getUserId()), throwable -> {
        });
    }

    @Override
    public void onAttach(GroupChatScreenContract.View view) {
        super.onAttach(view);
        firebaseEventBus.register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        firebaseEventBus.unregister(this);
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

        synchronized (this.messages) {
            totalMessagesCount.set(messages.getCount());
            this.messages.get().removeAll(messages.getData());
            this.messages.get().addAll(messages.getData());
            this.messages.notifyChange();
        }
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

    @Override
    public ObservableString getMyUserId() {
        return myId;
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

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewMessageEvent(NewMessageEvent event) {
        onNewMessage(event.getMessage());
    }
}
