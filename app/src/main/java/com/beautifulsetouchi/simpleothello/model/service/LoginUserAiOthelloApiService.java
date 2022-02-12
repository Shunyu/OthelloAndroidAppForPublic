package com.beautifulsetouchi.simpleothello.model.service;

import android.util.Log;

import com.beautifulsetouchi.simpleothello.di.DaggerLoginUserApiComponent;
import com.beautifulsetouchi.simpleothello.model.entity.AiOthelloApiRequestBody;
import com.beautifulsetouchi.simpleothello.model.entity.LoginUserAiOthelloApiBestMove;
import com.beautifulsetouchi.simpleothello.model.net.LoginUserAiOthelloApi;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

/**
 * loginAiOthelloApiを利用して、AIオセロから最適の手を取得するクラス
 * ログイン時に使用
 * AIオセロから最適の手を取得するメソッドを呼ぶ前には、まずaccessTokenをセットしてからメソッドを呼ぶ点に注意。
 */
public class LoginUserAiOthelloApiService {

    private String accessToken;

    @Inject
    LoginUserAiOthelloApi api;

    public LoginUserAiOthelloApiService(){
        DaggerLoginUserApiComponent.create().inject(this);
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public Single<LoginUserAiOthelloApiBestMove> getBestMove(AiOthelloApiRequestBody aiOthelloApiRequestBody){
        String authHeader = "Bearer " + accessToken;
        Log.i("model", authHeader);
        return api.getBestMove(authHeader, aiOthelloApiRequestBody);
    }

}
