package com.ekalips.instastudy.data.files;

import com.ekalips.instastudy.data.files.models.File;
import com.ekalips.instastudy.data.files.models.FileOrDirectory;
import com.ekalips.instastudy.data.files.source.remote.RemoteFilesDataSource;
import com.ekalips.instastudy.data.user.UserDataProvider;
import com.ekalips.instastudy.di.source_qualifier.DataProvider;
import com.ekalips.instastudy.di.source_qualifier.Remote;
import com.wonderslab.base.rx.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by djqrj on 12/3/2017.
 */

public class FilesDataProviderImpl implements FilesDataProvider {

    private final UserDataProvider userDataProvider;
    private final RemoteFilesDataSource remoteFilesDataSource;

    @Inject
    public FilesDataProviderImpl(@DataProvider UserDataProvider userDataProvider, @Remote RemoteFilesDataSource remoteFilesDataSource) {
        this.remoteFilesDataSource = remoteFilesDataSource;
        this.userDataProvider = userDataProvider;
    }

    @Override
    public Observable<List<? extends FileOrDirectory>> getDirectoryContent(String groupId, String path) {
        return RxUtils.wrapAsIO(userDataProvider.getUserToken().switchMap(token -> getDirectoryContent(token, groupId, path)));
    }

    @Override
    public Observable<List<? extends FileOrDirectory>> getMainGroupDirectoryContent(String path) {
        return userDataProvider.getUser(false).switchMap(user -> getDirectoryContent(user.getData().getGroups().get(0).getId(), path));
    }

    @Override
    public Observable<List<? extends FileOrDirectory>> getDirectoryContent(String token, String groupId, String path) {
        return remoteFilesDataSource.getDirectoryContent(token, groupId, path);
    }

    @Override
    public Observable<? extends File> uploadFile(String token, String groupId, String path, java.io.File file) {
        return remoteFilesDataSource.uploadFile(token, groupId, path, file);
    }

    @Override
    public Observable<? extends File> uploadFile(String groupId, String path, java.io.File file) {
        return userDataProvider.getUserToken().switchMap(token -> uploadFile(token, groupId, path, file));
    }

    @Override
    public Observable<? extends File> uploadFileToMyGroup(String path, java.io.File file) {
        return userDataProvider.getUser(false).switchMap(user -> uploadFile(user.getData().getGroups().get(0).getId(), path, file));
    }
}
