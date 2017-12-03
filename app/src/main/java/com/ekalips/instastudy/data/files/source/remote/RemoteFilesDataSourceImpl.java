package com.ekalips.instastudy.data.files.source.remote;

import com.ekalips.instastudy.data.files.models.FileOrDirectory;
import com.ekalips.instastudy.error_handling.ErrorThrower;
import com.ekalips.instastudy.network.InstaApi;
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

    @Inject
    public RemoteFilesDataSourceImpl(InstaApi instaApi, ErrorThrower errorThrower) {
        api = instaApi;
        this.errorThrower = errorThrower;
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
}
