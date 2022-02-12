package com.beautifulsetouchi.simpleothello.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * ログイン時に、インターネット経由で、AIオセロから最良の手を受領する際のResponseBodyクラス
 * 最良の手、およびAIオセロ側で付与された乱数データを格納する。
 * 対戦終了後、乱数データも一緒に、対戦成績更新APIに対して送信することで、
 * サーバー側で、実際にAIオセロを利用してオセロゲームを行ったかを確認している。
 */
public class LoginUserAiOthelloApiBestMove {

    @SerializedName("bestmove")
    public String bestMove;

    @SerializedName("bestmoveid")
    public String bestMoveId;

    public LoginUserAiOthelloApiBestMove(
            String bestMove,
            String bestMoveId
    ){
        this.bestMove = bestMove;
        this.bestMoveId = bestMoveId;
    }

    public String getBestMove(){
        return bestMove;
    }
     public String getBestMoveId(){
        return bestMoveId;
     }

}
