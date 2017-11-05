package com.ekalips.instastudy.registration.contract;

import com.ekalips.instastudy.registration.mvvm.model.FillDataObservable;
import com.ekalips.instastudy.registration.rules.CredentialsValidator;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

/**
 * Created by Ekalips on 11/5/17.
 */

public interface RegistrationActivityContract {

    interface View extends BaseView {

    }

    abstract class ViewModel extends BaseViewModel<View> {

        public abstract CredentialsValidator getValidator();

        public abstract FillDataObservable getRegistrationData();
    }

    interface Callbacks {

        void onDataFilled();

        void onImageSet();

        void onImageSetSkip();

    }

}
