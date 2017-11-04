package com.ekalips.instastudy.network.stuff;

import android.support.annotation.NonNull;

import com.ekalips.instastudy.network.InstaApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ekalips on 10/13/17.
 */

public class BearerHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        String authHeader = request.header(InstaApi.AUTH_HEADER);
        if (authHeader != null) {
            request = request.newBuilder()
                    .header(InstaApi.AUTH_HEADER, "Bearer " + authHeader)
                    .build();
        }
        return chain.proceed(request);
    }
}
