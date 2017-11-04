package com.ekalips.instastudy.stuff;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Ekalips on 11/2/17.
 */

public class NetworkUtils {

    public static RequestBody prepareStringRequestBody(String string) {
        // add another part within the multipart request
        return RequestBody.create(
                MultipartBody.FORM, string);
    }

    @Nullable
    public static MultipartBody.Part prepareFilePart(@NonNull Context context, String fieldName, @Nullable File file) {
        if (file == null) {
            return null;
        }

        String fileType = context.getContentResolver().getType(
                FileProvider.getUriForFile(context, Const.CONTENT_AUTHORITY, file)
        );
        if (fileType == null) {
            fileType = "";
        }

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(fileType),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(fieldName, file.getName(), requestFile);
    }

}
