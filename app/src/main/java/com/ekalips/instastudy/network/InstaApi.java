package com.ekalips.instastudy.network;

import com.ekalips.instastudy.data.groups.source.remote.RemoteGroup;
import com.ekalips.instastudy.data.user.source.network.model.RemoteUserData;
import com.ekalips.instastudy.data.user.source.network.model.RemoteUserDataWrap;
import com.ekalips.instastudy.network.body.LoginBody;
import com.ekalips.instastudy.network.body.UpdateUserNameBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Ekalips on 11/4/17.
 */

public interface InstaApi {
    String AUTH_HEADER = "Authorization";

    @POST("auth/login")
    Call<RemoteUserDataWrap> login(@Body LoginBody loginBody);

    @POST("user/me")
    Call<RemoteUserData> updateName(@Header(AUTH_HEADER) String token, @Body UpdateUserNameBody name);

    @POST("group/join")
    Call<RemoteGroup> joinToGroup(@Header(AUTH_HEADER) String token, @Query("title") String name);
}
