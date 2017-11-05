package com.ekalips.instastudy.registration.contract;

import android.databinding.ObservableField;

import com.esafirm.imagepicker.model.Image;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

import java.io.File;

/**
 * Created by Ekalips on 11/5/17.
 */

public interface SetImageScreenContract {

    interface View extends BaseView {

        void requestImageFromCamera();

        void requestImageFromGallery();
    }

    abstract class ViewModel extends BaseViewModel<View> {

        public abstract void skip();

        public abstract void setUp();

        public abstract void setFromCamera();

        public abstract void setFromGallery();

        public abstract ObservableField<File> getSelectedImage();

        public abstract void onImageSelected(Image image);
    }

}
