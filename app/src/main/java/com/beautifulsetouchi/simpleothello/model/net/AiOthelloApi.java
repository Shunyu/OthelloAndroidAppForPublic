package com.beautifulsetouchi.simpleothello.model.net;

import com.beautifulsetouchi.simpleothello.model.entity.AiOthelloApiBestMove;
import com.beautifulsetouchi.simpleothello.model.entity.AiOthelloApiRequestBody;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 未ログイン時に、AIオセロからインターネット経由で最良の手を取得するに当たって、定義したインターフェース
 */
public interface AiOthelloApi {
    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })
    @POST("best-move")
    Single<AiOthelloApiBestMove> getBestMove(@Body AiOthelloApiRequestBody aiOthelloApiRequestBody);

}
