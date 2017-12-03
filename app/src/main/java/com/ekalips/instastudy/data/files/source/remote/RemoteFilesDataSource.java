package com.ekalips.instastudy.data.files.source.remote;

import com.ekalips.instastudy.data.files.models.FileOrDirectory;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by djqrj on 12/3/2017.
 */

public interface RemoteFilesDataSource {

    Observable<List<? extends FileOrDirectory>> getDirectoryContent(String token, String groupId, String path);

}
