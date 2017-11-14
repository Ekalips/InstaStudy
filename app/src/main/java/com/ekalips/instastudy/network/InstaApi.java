package com.ekalips.instastudy.network;

import com.ekalips.instastudy.data.groups.source.remote.RemoteGroup;
import com.ekalips.instastudy.data.lessons.sources.remote.models.RemoteLesson;
import com.ekalips.instastudy.data.messages.sources.remote.RemoteMessage;
import com.ekalips.instastudy.data.user.source.network.model.RemoteUserData;
import com.ekalips.instastudy.data.user.source.network.model.RemoteUserDataWrap;
import com.ekalips.instastudy.network.body.LoginBody;
import com.ekalips.instastudy.network.body.SendMessageBody;
import com.ekalips.instastudy.network.body.UpdateFirebaseTokenBody;
import com.ekalips.instastudy.network.body.UpdateUserNameBody;
import com.ekalips.instastudy.network.response.PaginatedListResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
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

    @GET("group/{group_id}")
    Call<RemoteGroup> getGroup(@Header(AUTH_HEADER) String token, @Path("group_id") String groupId);

    @Multipart
    @POST("user/me/avatar")
    Call<RemoteUserData> updateAvatar(@Header(AUTH_HEADER) String token, @Part MultipartBody.Part filePart);

    @GET("group/{group_id}/chat/messages")
    Call<PaginatedListResponse<RemoteMessage>> getMessages(@Header(AUTH_HEADER) String token, @Path("group_id") String groupId, @Query("offset") int offset, @Query("limit") int limit);

    @GET("group/{group_id}/schedule")
    Call<List<RemoteLesson>> getLessons(@Header(AUTH_HEADER) String token, @Path("group_id") String groupId);

    @POST("group/{group_id}/chat/messages")
    Call<RemoteMessage> sendMessage(@Header(AUTH_HEADER) String token, @Path("group_id") String groupId, @Body SendMessageBody messageBody);

    Call<Void> updateDeviceToken(@Header(AUTH_HEADER) String accessToken, @Body UpdateFirebaseTokenBody body);
}
