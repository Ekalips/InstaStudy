package com.ekalips.instastudy.providers;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import com.ekalips.instastudy.stuff.FileUtils;
import com.wonderslab.base.rx.RxUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Callable;

import javax.annotation.Nullable;
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

    public File createTemporaryFile(@Nullable String originalFileName) throws IOException {
        String fileName;
        String ext = FileUtils.getFileExt(originalFileName);
        if (originalFileName == null) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            fileName = "File" + timeStamp + "";
        } else {
            fileName = originalFileName;
            if (fileName.endsWith(ext)) {
                fileName = fileName.substring(0, fileName.length() - 1 - ext.length());
            }
        }
        File storageDir = new File(context.getFilesDir(), "Files");
        if (!storageDir.exists())
            storageDir.mkdirs();
        if (!ext.startsWith(".")) {
            ext = "." + ext;
        }

        File result = File.createTempFile(
                fileName,  /* prefix */
                ext,         /* suffix */
                storageDir      /* directory */
        );
        result.deleteOnExit();
        return result;
    }


    public Observable<File> getFileFromUri(Uri uri) {
        return Observable.fromCallable(() -> {
            File resultingFile = createTemporaryFile(getFileName(uri));
            try (InputStream inputStream = context.getContentResolver().openInputStream(uri)) {
                if (inputStream != null) {
                    int maxBufferSize = 1024 * 1024;
                    int bytesAvailable = inputStream.available();
                    int bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    final byte[] buffers = new byte[bufferSize];
                    FileOutputStream outputStream = new FileOutputStream(resultingFile);
                    int read;
                    while ((read = inputStream.read(buffers)) != -1) {
                        outputStream.write(buffers, 0, read);
                    }
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultingFile;
        });
    }

    public String getFileName(Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor returnCursor =
                contentResolver.query(uri, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }
}
