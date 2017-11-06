package com.ekalips.instastudy.main.contract;

import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

/**
 * Created by Ekalips on 11/6/17.
 */

public interface MainActivityContract {

    interface View extends BaseView {

    }

    abstract class ViewModel extends BaseViewModel<View> {

    }

}
