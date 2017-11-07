package com.ekalips.instastudy.main.contract;

import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

/**
 * Created by Ekalips on 11/7/17.
 */

public interface ScheduleScreenContract {

    interface View extends BaseView {

    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }
    }

}
