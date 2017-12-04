package com.ekalips.instastudy.main.contract;

import android.databinding.ObservableBoolean;

import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

/**
 * Created by ekalips on 12/4/17.
 */

public interface NotificationsScreenContract {

    interface View extends BaseView {

    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }

        public abstract ObservableBoolean getCanPublish();
    }

}
