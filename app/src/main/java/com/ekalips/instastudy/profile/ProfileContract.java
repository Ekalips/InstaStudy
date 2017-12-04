package com.ekalips.instastudy.profile;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.ekalips.instastudy.data.user.User;
import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

/**
 * Created by ekalips on 12/4/17.
 */

public interface ProfileContract {

    interface View extends BaseView {

        void showEnterCodeDialog();

    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }

        public abstract void init(String userId);

        public abstract ObservableField<User> getUser();

        public abstract ObservableBoolean getIsMe();

        public abstract ObservableBoolean getLoading();

        public abstract void showEnterCodeDialog();

        public abstract void sendRiseCode(String code);
    }

}
