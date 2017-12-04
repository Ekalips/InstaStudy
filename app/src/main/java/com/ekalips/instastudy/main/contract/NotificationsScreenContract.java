package com.ekalips.instastudy.main.contract;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

import java.util.List;

/**
 * Created by ekalips on 12/4/17.
 */

public interface NotificationsScreenContract {

    interface View extends BaseView {

        void showSendNotificationDialog();
    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }

        public abstract void init(String groupId);

        public abstract void fetchNotifications();

        public abstract ObservableBoolean getCanPublish();

        public abstract ObservableField<List<Object>> getData();

        public abstract ObservableBoolean getLoading();

        public abstract void showSendNotificationDialog();

        public abstract void sendNotification(CharSequence title, CharSequence body);
    }

}
