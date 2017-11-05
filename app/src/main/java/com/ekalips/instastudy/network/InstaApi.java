package com.ekalips.instastudy.network;

import com.ekalips.instastudy.data.user.source.network.model.RemoteUserDataWrap;
import com.ekalips.instastudy.network.body.LoginBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Ekalips on 11/4/17.
 */

public interface InstaApi {
    String AUTH_HEADER = "Authorization";

    @POST("auth/login")
    Call<RemoteUserDataWrap> login(@Body LoginBody loginBody);

}
