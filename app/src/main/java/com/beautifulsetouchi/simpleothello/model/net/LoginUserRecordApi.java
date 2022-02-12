package com.beautifulsetouchi.simpleothello.model.net;

import com.beautifulsetouchi.simpleothello.model.entity.GameResultRequestResource;

import java.util.Map;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * ログイン時に、accessTokenをヘッダーに含めつつ、
 * インターネット経由で、対戦成績の取得や更新を行うために定義したインターフェース
 */
public interface LoginUserRecordApi {

    @GET("ai-othello/playresult")
    Single<Map<String, Object>> getLoginUserRecord(@Header("Authorization") String authHeader);

    @POST("ai-othello/playresult/update")
    Single<Map<String, Object>> updateLoginUserRecord(@Header("Authorization") String authHeader, @Body GameResultRequestResource gameResultRequestResource);
}
