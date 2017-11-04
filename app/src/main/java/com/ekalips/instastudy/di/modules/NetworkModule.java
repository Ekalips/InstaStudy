package com.ekalips.instastudy.di.modules;

import com.ekalips.instastudy.BuildConfig;
import com.ekalips.instastudy.network.InstaApi;
import com.ekalips.instastudy.network.stuff.BearerHeaderInterceptor;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ekalips on 10/2/17.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    GsonConverterFactory providerConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Provides
    @Singleton
    StethoInterceptor provideStetho() {
        return new StethoInterceptor();
    }

    @Provides
    @Singleton
    BearerHeaderInterceptor provideHeaderInterceptor() {
        return new BearerHeaderInterceptor();
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(HttpLoggingInterceptor loggingInterceptor, StethoInterceptor stethoInterceptor, BearerHeaderInterceptor bearerHeaderInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(stethoInterceptor)
                .addInterceptor(bearerHeaderInterceptor)
                .build();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory, OkHttpClient client, RxJava2CallAdapterFactory callAdapterFactory) {
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(BuildConfig.SERVER_URL)
                .build();
    }

    @Provides
    @Singleton
    InstaApi provideApiService(Retrofit retrofit) {
        return retrofit.create(InstaApi.class);
    }

}
