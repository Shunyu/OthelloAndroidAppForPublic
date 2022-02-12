package com.beautifulsetouchi.simpleothello.model.service;

import com.beautifulsetouchi.simpleothello.di.DaggerApiComponent;
import com.beautifulsetouchi.simpleothello.model.entity.AiOthelloApiBestMove;
import com.beautifulsetouchi.simpleothello.model.entity.AiOthelloApiRequestBody;
import com.beautifulsetouchi.simpleothello.model.net.AiOthelloApi;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

/**
 * aiOthelloApiを利用して、AIオセロから最適の手を取得するクラス
 * 未ログイン時に使用
 */
public class AiOthelloApiService {

    @Inject
    AiOthelloApi api;

    public AiOthelloApiService(){
        DaggerApiComponent.create().inject(this);
    }

    public Single<AiOthelloApiBestMove> getBestMove(AiOthelloApiRequestBody aiOthelloApiRequestBody){
        return api.getBestMove(aiOthelloApiRequestBody);
    }

}
