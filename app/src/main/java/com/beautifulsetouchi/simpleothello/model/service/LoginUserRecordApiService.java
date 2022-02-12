package com.beautifulsetouchi.simpleothello.model.service;

import android.util.Log;

import com.beautifulsetouchi.simpleothello.di.DaggerLoginUserRecordApiComponent;
import com.beautifulsetouchi.simpleothello.model.entity.GameResultRequestResource;
import com.beautifulsetouchi.simpleothello.model.net.LoginUserRecordApi;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

/**
 * loginUserRecordApiを利用して、対戦成績の取得や更新を行うクラス
 * ログイン時に使用
 * 対戦成績の取得や更新のメソッドを呼ぶ前には、まずaccessTokenをセットしてからメソッドを呼ぶ点に注意。
 */
public class LoginUserRecordApiService {

    @Inject
    LoginUserRecordApi api;

    public LoginUserRecordApiService() {
        DaggerLoginUserRecordApiComponent.create().inject(this);
    }

    private String accessToken;

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public Single<Map<String, Object>> getLoginUserRecord(){
        String authHeader = "Bearer " + accessToken;
        Log.i("model", authHeader);
        return api.getLoginUserRecord(authHeader);
    }

    public Single<Map<String, Object>> updateLoginUserRecord(GameResultRequestResource gameResultRequestResource) {
        String authHeader = "Bearer " + accessToken;
        Log.i("model", authHeader);
        return api.updateLoginUserRecord(authHeader, gameResultRequestResource);
    }

}
