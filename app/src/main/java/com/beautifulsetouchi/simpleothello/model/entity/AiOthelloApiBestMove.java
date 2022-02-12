package com.beautifulsetouchi.simpleothello.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 未ログイン時に、インターネット経由で、AIオセロから最良の手を受領する際のResponseBodyクラス
 * 最良の手の情報を格納する
 */
public class AiOthelloApiBestMove {

    @SerializedName("bestmove")
    public String bestMove;

    public AiOthelloApiBestMove(String bestMove){
        this.bestMove = bestMove;
    }

}
