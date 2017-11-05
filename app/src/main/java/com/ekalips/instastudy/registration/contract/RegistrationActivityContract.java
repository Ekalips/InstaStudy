package com.ekalips.instastudy.registration.contract;

import com.ekalips.instastudy.registration.mvvm.model.FillDataObservable;
import com.ekalips.instastudy.registration.rules.CredentialsValidator;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

import java.io.File;

/**
 * Created by Ekalips on 11/5/17.
 */

public interface RegistrationActivityContract {

    interface View extends BaseView {

    }

    abstract class ViewModel extends BaseViewModel<View> {

        public abstract CredentialsValidator getValidator();

        public abstract FillDataObservable getRegistrationData();

        public abstract void register();

        public abstract void onImageSet(File file);

        public abstract void onImageSkip();
    }

}
