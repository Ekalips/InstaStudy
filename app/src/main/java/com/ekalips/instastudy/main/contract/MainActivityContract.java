package com.ekalips.instastudy.main.contract;

import android.databinding.ObservableField;
import android.support.annotation.MenuRes;

import com.ekalips.instastudy.data.user.User;
import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

/**
 * Created by Ekalips on 11/6/17.
 */

public interface MainActivityContract {

    interface View extends BaseView {

    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }

        public abstract ObservableField<User> getUser();
    }

    interface FlexibleMainToolbar {

        void onToolbarTitleChange(String title);

        void onMenuChange(@MenuRes int menu);

    }

}
