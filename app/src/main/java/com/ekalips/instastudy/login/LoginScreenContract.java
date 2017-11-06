package com.ekalips.instastudy.login;

import android.databinding.ObservableBoolean;

import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

/**
 * Created by Ekalips on 11/5/17.
 */

public interface LoginScreenContract {

    interface View extends BaseView {

    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }

        public abstract void login();

        public abstract ObservableBoolean getInProgress();

    }

}
