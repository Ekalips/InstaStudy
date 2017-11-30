package com.ekalips.instastudy.main.contract.chat.attachments;

import android.databinding.ObservableField;

import com.ekalips.instastudy.main.mvvm.model.SelectableFile;
import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

import java.util.List;

/**
 * Created by ekalips on 11/30/17.
 */

public interface AttachmentGalleryContract {

    interface View extends BaseView {
        void requestPermissions();
    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }

        public abstract ObservableField<List<SelectableFile>> getImages();

        public abstract ObservableField<List<SelectableFile>> getSelectedFiles();

        public abstract void selectFile(SelectableFile selectableFile);

        public abstract void fetchImages();

        public abstract void requestPermissions();

        public abstract void unselectAll();

        public abstract void sendSelected();
    }

}
