package com.ekalips.instastudy.main.contract.chat.attachments;

import android.databinding.ObservableField;

import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

import java.io.File;

/**
 * Created by ekalips on 11/29/17.
 */

public interface AttachmentTakePhotoContract {

    interface View extends BaseView {

        void requestPermissions();

        void takePicture(File imageFile);
    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }

        public abstract void requestPermissions();

        public abstract void takePicture();

        public abstract void onPictureTaken(File imageFile);

        public abstract ObservableField<File> getTakenFile();

        public abstract void undo();

        public abstract void send();
    }
}
