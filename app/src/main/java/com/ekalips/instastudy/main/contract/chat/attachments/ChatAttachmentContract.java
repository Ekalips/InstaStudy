package com.ekalips.instastudy.main.contract.chat.attachments;

import android.databinding.ObservableField;

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

        public abstract ObservableField<Pages> getCurrentPage();

        public abstract void changePage(Pages page);
    }

    enum Pages {
        TAKE_PHOTO, SELECT_IMAGE, PHONE_GALLERY, PHONE_CAMERA
    }

}
