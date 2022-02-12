package com.beautifulsetouchi.simpleothello.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * ログイン時に、インターネット経由で、対戦成績を受領する際のResponseBodyクラス
 * 現在は使用していないので、関連箇所を整理する予定。
 */
public class LoginUserRecord {

    @SerializedName("winnum")
    private long winnum;

    @SerializedName("drawnum")
    private long drawnum;

    @SerializedName("losenum")
    private long losenum;

    public LoginUserRecord(long winnum, long drawnum, long losenum) {
        this.winnum = winnum;
        this.drawnum = drawnum;
        this.losenum = losenum;
    }

    public long getWinnum() {
        return winnum;
    }

    public long getDrawnum() {
        return drawnum;
    }

    public long getLosenum() {
        return losenum;
    }
}
