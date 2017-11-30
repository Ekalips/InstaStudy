package com.ekalips.instastudy.providers;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.wonderslab.base.rx.RxUtils;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by ekalips on 11/30/17.
 */

@Singleton
public class FilesProvider {

    private final Context context;

    @Inject
    public FilesProvider(Context context) {
        this.context = context;
    }

    public File createImageFile() {
        File dir = context.getFilesDir();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, "Image" + System.currentTimeMillis());
    }

    public Observable<File> getImages() {
        return RxUtils.wrapAsIO(Observable.create((ObservableOnSubscribe<File>) e -> {
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
            final String orderBy = MediaStore.Images.Media._ID;
            //Stores all the images from the gallery in Cursor
            try (Cursor cursor = context.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                    null, orderBy)) {
                if (cursor != null) {
                    int count = cursor.getCount();
                    String[] arrPath = new String[count];

                    for (int i = 0; i < count; i++) {
                        cursor.moveToPosition(i);
                        int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                        //Store the path of the image
                        arrPath[i] = cursor.getString(dataColumnIndex);
                        e.onNext(new File(arrPath[i]));
                    }
                }
            } catch (Throwable t) {
                e.onError(t);
            } finally {
                e.onComplete();
            }
        }));
    }
}
