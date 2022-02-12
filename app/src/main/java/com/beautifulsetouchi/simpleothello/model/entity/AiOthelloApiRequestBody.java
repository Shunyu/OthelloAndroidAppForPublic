package com.beautifulsetouchi.simpleothello.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 未ログイン時に、インターネット経由で、AIオセロから最良の手を取得する際のRequestBodyクラス
 * リクエストを投げる際に必要な情報を格納する
 */
public class AiOthelloApiRequestBody {

    @SerializedName("nextplayer")
    private int nextPlayer;
    @SerializedName("boardarray")
    private String boardArrayString;

    public AiOthelloApiRequestBody(int nextPlayer, String boardArrayString){
        this.nextPlayer = nextPlayer;
        this.boardArrayString = boardArrayString;
    }

}
