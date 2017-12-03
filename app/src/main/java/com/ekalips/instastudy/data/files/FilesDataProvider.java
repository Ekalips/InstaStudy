package com.ekalips.instastudy.data.files;

import com.ekalips.instastudy.data.files.models.File;
import com.ekalips.instastudy.data.files.models.FileOrDirectory;
import com.ekalips.instastudy.data.files.source.remote.RemoteFilesDataSource;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by djqrj on 12/3/2017.
 */

public interface FilesDataProvider extends RemoteFilesDataSource {

    Observable<List<? extends FileOrDirectory>> getDirectoryContent(String groupId, String path);

    Observable<List<? extends FileOrDirectory>> getMainGroupDirectoryContent(String path);

    Observable<? extends File> uploadFile(String groupId, java.io.File file);

    Observable<? extends File> uploadFileToMyGroup(java.io.File file);
}
