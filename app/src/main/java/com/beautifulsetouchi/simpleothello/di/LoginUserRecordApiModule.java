package com.beautifulsetouchi.simpleothello.di;

import com.beautifulsetouchi.simpleothello.model.net.LoginUserRecordApi;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 依存性の注入に利用するために、LoginUserRecordApiの準備を行うクラス
 */
@Module
public class LoginUserRecordApiModule {

    private static final String BASE_URL = "https://beautiful-setouchi.com/user/v2/";

    @Provides
    public LoginUserRecordApi provideLoginUserRecordApi() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(httpClient.build())
                .build()
                .create(LoginUserRecordApi.class);
    }
}
