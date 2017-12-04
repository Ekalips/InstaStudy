package com.ekalips.instastudy.main.contract;

import android.databinding.ObservableField;
import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;

import com.ekalips.instastudy.data.user.User;
import com.ekalips.instastudy.navigation.NavigateToEnum;
import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

/**
 * Created by Ekalips on 11/6/17.
 */

public interface MainActivityContract {

    interface View extends BaseView {

        void shoUserPage(String userId);
    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }

        public abstract ObservableField<User> getUser();

        public abstract ObservableField<Screens> getCurrentScreen();

        public abstract void navigateTo(Screens screens);

        public abstract void onMenuItemSelected(@IdRes int itemId);

        public abstract void openMyUserPage();
    }

    interface FlexibleMainToolbar {

        void onChange(String toolbarTitle, @MenuRes int menu);

    }

    enum Screens {
        GROUP_CHAT(NavigateToEnum.GROUP_CHAT), SCHEDULE(NavigateToEnum.SCHEDULE), TEACHERS(NavigateToEnum.TEACHERS),
        FILES(NavigateToEnum.FILES), NOTIFICATIONS(NavigateToEnum.NOTIFICATIONS), NONE(null);

        private final NavigateToEnum place;

        Screens(NavigateToEnum place) {
            this.place = place;
        }

        public NavigateToEnum getPlace() {
            return place;
        }
    }

}
