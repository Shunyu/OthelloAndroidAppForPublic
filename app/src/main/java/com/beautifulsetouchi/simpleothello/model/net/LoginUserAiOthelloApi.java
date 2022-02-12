package com.beautifulsetouchi.simpleothello.model.net;

import com.beautifulsetouchi.simpleothello.model.entity.AiOthelloApiRequestBody;
import com.beautifulsetouchi.simpleothello.model.entity.LoginUserAiOthelloApiBestMove;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * ログイン時に、accessTokenをヘッダーに含めつつ、
 * AIオセロからインターネット経由で最良の手を取得するに当たって、定義したインターフェース
 */
public interface LoginUserAiOthelloApi {

    @POST("best-move")
    Single<LoginUserAiOthelloApiBestMove> getBestMove(
            @Header("Authorization") String authHeader,
            @Body AiOthelloApiRequestBody aiOthelloApiRequestBody
    );

}
