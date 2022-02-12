package com.beautifulsetouchi.simpleothello.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * 対戦成績更新APIに対して、成績更新のリクエストを送る際に添付する、対戦時の盤面推移の情報を格納するクラス
 * オセロ更新前の盤面
 * オセロ更新時のPlayer
 * オセロ更新時の手（もしくはAIオセロから受領した乱数）
 * オセロ更新後の盤面
 * の情報を格納している。
 */
public class GameSituation {

    @SerializedName("beforeboard")
    private int[][] beforeBoard;
    @SerializedName("player")
    private int player;
    @SerializedName("action")
    private String action;
    @SerializedName("afterboard")
    private int[][] afterBoard;

    public void setBeforeBoard(int[][] beforeBoard){
        this.beforeBoard = beforeBoard;
    }

    public void setPlayer(int player){
        if (player != 1 && player != 2) {
            throw new IllegalArgumentException("playerが不正です。");
        }
        this.player = player;
    }

    public void setAction(String action){
        this.action = action;
    }

    public void setAfterBoard(int[][] afterBoard){
        this.afterBoard = afterBoard;
    }

    public int[][] getBeforeBoard() {
        return beforeBoard;
    }

    public int getPlayer() {
        return player;
    }

    public String getAction(){
        return action;
    }

    public int[][] getAfterBoard(){
        return afterBoard;
    }

    @Override
    public String toString() {
        return "GameSituation{" +
                "beforeBoard=" + Arrays.toString(beforeBoard) +
                ", player=" + player +
                ", action='" + action + '\'' +
                ", afterBoard=" + Arrays.toString(afterBoard) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSituation that = (GameSituation) o;
        return player == that.player &&
                Arrays.deepEquals(beforeBoard, that.beforeBoard) &&
                action.equals(that.action) &&
                Arrays.deepEquals(afterBoard, that.afterBoard);
    }
}
