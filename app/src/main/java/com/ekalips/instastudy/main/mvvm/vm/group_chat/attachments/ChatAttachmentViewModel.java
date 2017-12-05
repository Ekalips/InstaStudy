package com.ekalips.instastudy.main.mvvm.vm.group_chat.attachments;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;

import com.ekalips.instastudy.R;
import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.data.messages.MessageDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.main.contract.chat.GroupChatScreenContract;
import com.ekalips.instastudy.main.contract.chat.attachments.ChatAttachmentContract;
import com.ekalips.instastudy.providers.ToastProvider;
import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.rx.RxUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by ekalips on 11/29/17.
 */


public class ChatAttachmentViewModel extends ChatAttachmentContract.ViewModel {

    private static final String TAG = ChatAttachmentViewModel.class.getSimpleName();

    private final ObservableField<ChatAttachmentContract.Pages> currentPage = new ObservableField<>();
    private final ObservableBoolean loading = new ObservableBoolean(false);
    private final ObservableInt filesLeft = new ObservableInt(0);
    private final MessageDataProvider messageDataProvider;
    private final ToastProvider toastProvider;
    private final GroupChatScreenContract.ViewModel parentVM;

    private String groupId;

    @Inject
    public ChatAttachmentViewModel(RxRequests rxRequests, @DataProvider MessageDataProvider messageDataProvider, ToastProvider toastProvider,
                                   GroupChatScreenContract.ViewModel parentVM) {
        super(rxRequests);
        this.messageDataProvider = messageDataProvider;
        this.toastProvider = toastProvider;
        this.parentVM = parentVM;
        changePage(ChatAttachmentContract.Pages.TAKE_PHOTO);
    }

    @Override
    public void init(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public ObservableField<ChatAttachmentContract.Pages> getCurrentPage() {
        return currentPage;
    }

    public void changePage(ChatAttachmentContract.Pages page) {
        if (page != currentPage.get()) {
            currentPage.set(page);
            navigateTo(page, null);
        }
    }

    @Override
    public void sendFiles(File... files) {
        List<Observable<? extends Message>> filesToSend = new ArrayList<>();
        for (File f :
                files) {
            filesToSend.add(messageDataProvider.sendChatAttachment(groupId, f));
        }

        filesLeft.set(filesToSend.size());
        loading.set(true);
        request(RxUtils.wrapAsIO(Observable.concat(filesToSend)), message -> {
            onFileSent(message);
            filesLeft.set(filesLeft.get() - 1);

            if (filesLeft.get() <= 0) {
                finish();
            }
        }, this::onFileSendError);
    }

    private void onFileSent(Message message) {
        parentVM.onNewMessage(message);
    }

    private void onFileSendError(Throwable throwable) {
        Log.e(TAG, "onFileSendError: ", throwable);
        toastProvider.showToast(R.string.error_file_upload);
    }

    private void finish() {
        loading.set(false);
        goBack();
    }

    @Override
    public ObservableBoolean getLoading(){
        return loading;
    }

    @Override
    public ObservableInt getFilesLeft(){
        return filesLeft;
    }
}
