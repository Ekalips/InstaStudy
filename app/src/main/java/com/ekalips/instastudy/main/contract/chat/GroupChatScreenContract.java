package com.ekalips.instastudy.main.contract.chat;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.ekalips.instastudy.data.messages.Message;
import com.ekalips.instastudy.stuff.ObservableString;
import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

import java.util.List;

/**
 * Created by Ekalips on 11/6/17.
 */

public interface GroupChatScreenContract {

    interface View extends BaseView {
        void clearInput();

        void scrollToBottom();

        void showAttachmentDialog(String chatId);
    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }

        public abstract void init(String groupId);

        public abstract ObservableInt getTotalCount();

        public abstract ObservableField<List<Message>> getMessages();

        public abstract void requestNextPage();

        public abstract void sendMessage(CharSequence message);

        public abstract ObservableString getMyUserId();

        public abstract void onNewMessage(Message message);

        public abstract void showAttachmentDialog();
    }

}
