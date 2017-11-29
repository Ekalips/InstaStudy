package com.ekalips.instastudy.main.contract.chat;

import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

/**
 * Created by ekalips on 11/29/17.
 */

public interface ChatAttachmentContract {

    interface View extends BaseView {
    }

    abstract class ViewModel extends BaseViewModel<View> {
        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }
    }

}
