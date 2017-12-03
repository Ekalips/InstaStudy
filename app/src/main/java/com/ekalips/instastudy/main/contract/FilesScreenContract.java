package com.ekalips.instastudy.main.contract;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.net.Uri;

import com.ekalips.instastudy.data.files.models.Directory;
import com.ekalips.instastudy.data.files.models.File;
import com.wonderslab.base.rx.RxRequests;
import com.wonderslab.base.veiwmodel.BaseViewModel;
import com.wonderslab.base.view.BaseView;

import java.util.List;

/**
 * Created by djqrj on 12/3/2017.
 */

public interface FilesScreenContract {

    interface View extends BaseView {

        void showFileChooser();

        void openUrl(String url);
    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }

        public abstract void init(String groupId, String directory);

        public abstract ObservableField<List<Object>> getContent();

        public abstract void onDownloadFile(File file);

        public abstract void onOpenDirectory(Directory directory);

        public abstract void showUploadDialog();

        public abstract void onFileSelected(Uri uri);

        public abstract ObservableBoolean getLoading();
    }

}
