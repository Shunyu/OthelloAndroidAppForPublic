package com.beautifulsetouchi.simpleothello.di;

import com.beautifulsetouchi.simpleothello.model.net.LoginUserAiOthelloApi;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 依存性の注入に利用するために、LoginUserOthelloApiの準備を行うクラス
 */
@Module
public class LoginUserApiModule {

    private static final String BASE_URL = "https://beautiful-setouchi.com/user/v2/ai-othello/";

    @Provides
    public LoginUserAiOthelloApi provideLoginUserAiOthelloApi(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(httpClient.build())
                .build()
                .create(LoginUserAiOthelloApi.class);
    }

}
