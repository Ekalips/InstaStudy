package com.ekalips.instastudy.main.mvvm.vm.group_chat;

import android.databinding.ObservableField;
import android.util.Log;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.groups.Group;
import com.ekalips.instastudy.data.groups.GroupDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.main.contract.GroupChatScreenContract;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.providers.MessagingProvider;
import com.wonderslab.base.rx.RxRequests;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/6/17.
 */

public class GroupChatViewModule extends GroupChatScreenContract.ViewModel {

    private static final String TAG = GroupChatViewModule.class.getSimpleName();

    private final MainActivityContract.FlexibleMainToolbar flexibleMainToolbar;
    private final GroupDataProvider groupDataProvider;
    private final MessagingProvider messagingProvider;

    private final ObservableField<Group> group = new ObservableField<>(null);

    @Inject
    public GroupChatViewModule(MainActivityContract.FlexibleMainToolbar flexibleMainToolbar, @DataProvider GroupDataProvider groupDataProvider,
                               RxRequests rxRequests, MessagingProvider messagingProvider) {
        super(rxRequests);
        this.flexibleMainToolbar = flexibleMainToolbar;
        this.groupDataProvider = groupDataProvider;
        this.messagingProvider = messagingProvider;
    }

    @Override
    public void init(String groupId) {
        request(groupDataProvider.getGroup(groupId, true), data -> setUpGroup(data.getData()), this::onGetGroupError);
    }

    private void setUpGroup(Group group) {
        this.group.set(group);
        flexibleMainToolbar.onToolbarTitleChange(group.getTitle());
    }

    private void onGetGroupError(Throwable throwable) {
        Log.e(TAG, "onGetGroupError: ", throwable);
        messagingProvider.showToast(R.string.error_get_group);
    }

    @Override
    public void sendMessage(CharSequence message) {

    }
}
