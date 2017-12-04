package com.ekalips.instastudy.main.contract;

import android.databinding.ObservableField;

import com.ekalips.instastudy.main.mvvm.model.schedule.LessonDay;
import com.ekalips.instastudy.stuff.learning.WeekType;
import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

import java.util.List;

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

        public abstract ObservableField<List<LessonDay>> getLessons();

        public abstract ObservableField<WeekType> getWeekType();

        public abstract void switchWeek();
    }

    enum ViewType {
        NONE, ODD, EVEN
    }

}
