package com.ekalips.instastudy.main.mvvm.vm.files;

import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

import com.ekalips.instastudy.data.files.FilesDataProvider;
import com.ekalips.instastudy.data.files.models.Directory;
import com.ekalips.instastudy.data.files.models.File;
import com.ekalips.instastudy.data.files.models.FileOrDirectory;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.main.contract.FilesScreenContract;
import com.ekalips.instastudy.main.contract.MainActivityContract;
import com.ekalips.instastudy.main.mvvm.model.files.DirectoryModel;
import com.ekalips.instastudy.main.mvvm.model.files.FileModel;
import com.ekalips.instastudy.navigation.NavigateToEnum;
import com.ekalips.instastudy.stuff.Const;
import com.ekalips.instastudy.stuff.StringUtils;
import com.wonderslab.base.rx.Response;
import com.wonderslab.base.rx.RxRequests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by djqrj on 12/3/2017.
 */

public class FilesScreenViewModel extends FilesScreenContract.ViewModel {

    private static final String TAG = FilesScreenViewModel.class.getSimpleName();

    private final ObservableField<List<Object>> content = new ObservableField<>(new ArrayList<>());
    private final FilesDataProvider filesDataProvider;
    private final MainActivityContract.ViewModel parentVM;

    private String groupId;
    private String directory;

    @Inject
    public FilesScreenViewModel(RxRequests rxRequests, @DataProvider FilesDataProvider filesDataProvider, MainActivityContract.ViewModel parentVM) {
        super(rxRequests);
        this.filesDataProvider = filesDataProvider;
        this.parentVM = parentVM;
    }

    @Override
    public void init(String groupId, String directory) {
        this.groupId = groupId;
        this.directory = directory;

        fixDirectory();
        requestContent();
    }

    private void fixDirectory() {
        if (directory == null) {
            directory = Const.INITAL_DIR;
        }
        if (!directory.endsWith(Const.DIR_DIVIDER)) {
            directory = directory + Const.DIR_DIVIDER;
        }
    }

    private void requestContent() {
        if (StringUtils.isEmpty(groupId)) {
            request(filesDataProvider.getMainGroupDirectoryContent(directory), this::onNewFiles, this::onGetFilesError);
        } else {
            request(filesDataProvider.getDirectoryContent(groupId, directory), this::onNewFiles, this::onGetFilesError);
        }
    }

    private void onNewFiles(List<? extends FileOrDirectory> fileOrDirectory) {
        content.get().clear();
        for (FileOrDirectory c :
                fileOrDirectory) {
            if (c.isDirectory()) {
                DirectoryModel directoryModel = new DirectoryModel(c);
                directoryModel.setPath(fixDirectoryPath(directoryModel.getPath()));
                content.get().add(directoryModel);
            } else if (c.isFile()) {
                content.get().add(new FileModel(c));
            }
        }

        Collections.sort(content.get(), (first, second) -> {
            if (first instanceof Directory) {
                if (second instanceof Directory) {
                    return ((Directory) first).getPath().compareTo(((Directory) second).getPath());
                } else {
                    return -1;
                }
            } else if (second instanceof Directory) {
                return -1;
            }
            return 1;
        });
        content.notifyChange();
    }

    private void onGetFilesError(Throwable throwable) {
        Log.e(TAG, "onGetFilesError: ", throwable);
    }

    @Override
    public ObservableField<List<Object>> getContent() {
        return content;
    }

    private String fixDirectoryPath(String directoryPath) {
        String res = directoryPath;
        if (res != null) {
            if (res.startsWith(directory)) {
                res = res.replaceFirst(directory, "");
            }
            if (res.endsWith(Const.DIR_DIVIDER) && res.length() > 0) {
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }

    @Override
    public void onDownloadFile(File file) {

    }

    @Override
    public void onOpenDirectory(Directory directory) {
        if (directory != null) {
            String path = directory.getPath();
            parentVM.navigateTo(NavigateToEnum.FILES, path);
        }
    }
}
