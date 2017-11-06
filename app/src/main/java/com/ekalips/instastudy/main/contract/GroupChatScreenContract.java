package com.ekalips.instastudy.main.contract;

import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

/**
 * Created by Ekalips on 11/6/17.
 */

public interface GroupChatScreenContract {

    interface View extends BaseView {
        void clearInput();
    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }

        public abstract void init(String groupId);

        public abstract void sendMessage(CharSequence message);

    }

}
