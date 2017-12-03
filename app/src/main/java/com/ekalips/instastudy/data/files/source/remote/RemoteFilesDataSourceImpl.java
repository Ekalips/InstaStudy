package com.ekalips.instastudy.data.files.source.remote;

import android.content.Context;

import com.ekalips.instastudy.data.files.models.File;
import com.ekalips.instastudy.data.files.models.FileOrDirectory;
import com.ekalips.instastudy.error_handling.ErrorThrower;
import com.ekalips.instastudy.network.InstaApi;
import com.ekalips.instastudy.providers.FilesProvider;
import com.ekalips.instastudy.stuff.NetworkUtils;
import com.wonderslab.base.rx.RxUtils;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by djqrj on 12/3/2017.
 */

public class RemoteFilesDataSourceImpl implements RemoteFilesDataSource {

    private final InstaApi api;
    private final ErrorThrower errorThrower;
    private final FilesProvider filesProvider;
    private final Context context;

    @Inject
    public RemoteFilesDataSourceImpl(InstaApi instaApi, ErrorThrower errorThrower, FilesProvider filesProvider, Context context) {
        api = instaApi;
        this.context = context;
        this.errorThrower = errorThrower;
        this.filesProvider = filesProvider;
    }

    @Override
    public Observable<List<? extends FileOrDirectory>> getDirectoryContent(String token, String groupId, String path) {
        return RxUtils.wrapAsIO(Observable.fromCallable((Callable<List<? extends FileOrDirectory>>) () -> {
            Response<List<RemoteFileOrDirectoryEntity>> response = api.getDirectoryContent(token, groupId, path).execute();
            if (response.isSuccessful()) {
                return response.body();
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }

    @Override
    public Observable<? extends File> uploadFile(String token, String groupId, String path, java.io.File file) {
        return RxUtils.wrapAsIO(Observable.fromCallable((Callable<File>) () -> {
            Response<RemoteFileOrDirectoryEntity> response = api.uploadFile(token, groupId, path, NetworkUtils.prepareFilePart(context, "file", file)).execute();
            if (response.isSuccessful()) {
                return response.body();
            }
            errorThrower.throwFromResponse(response);
            return null;
        }));
    }
}
