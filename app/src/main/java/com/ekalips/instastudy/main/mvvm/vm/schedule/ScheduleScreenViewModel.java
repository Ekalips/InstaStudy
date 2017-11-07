package com.ekalips.instastudy.main.mvvm.vm.schedule;

import com.ekalips.instastudy.main.contract.ScheduleScreenContract;
import com.wonderslab.base.rx.RxRequests;

import javax.inject.Inject;

/**
 * Created by Ekalips on 11/7/17.
 */

public class ScheduleScreenViewModel extends ScheduleScreenContract.ViewModel {

    @Inject
    public ScheduleScreenViewModel(RxRequests rxRequests) {
        super(rxRequests);
    }
}
