package com.ekalips.instastudy.main.contract;

import android.databinding.ObservableField;

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

    }

    abstract class ViewModel extends BaseViewModel<View> {

        public ViewModel(RxRequests rxRequests) {
            super(rxRequests);
        }

        public abstract void init(String groupId, String directory);

        public abstract ObservableField<List<Object>> getContent();

        public abstract void onDownloadFile(File file);

        public abstract void onOpenDirectory(Directory directory);
    }

}
