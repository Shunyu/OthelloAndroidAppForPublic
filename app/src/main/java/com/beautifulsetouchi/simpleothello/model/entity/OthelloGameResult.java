package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.constant.Winner;

/**
 * オセロゲームが終了した際に結果を格納するクラス
 */
public class OthelloGameResult {

    private Winner winner;
    private int blackStoneNum;
    private int whiteStoneNum;

    public OthelloGameResult(){
        this(Winner.DRAW, 2, 2);
    }

    private OthelloGameResult(
            Winner winner,
            int blackStoneNum,
            int whiteStoneNum
    ) {

        this.winner = winner;

        if (blackStoneNum <= -1 || 65 <= blackStoneNum){
            throw new IllegalArgumentException("blackStoneNumは0以上64以下でなければなりません。");
        }
        this.blackStoneNum = blackStoneNum;

        if (whiteStoneNum <= -1 || 65 <= whiteStoneNum) {
            throw new IllegalArgumentException("whiteStoneNumは0以上64以下でなければなりません。");
        }
        this.whiteStoneNum = whiteStoneNum;

    }

    public void setWinner(Winner winner) {

        this.winner = winner;
    }

    public void setBlackStoneNum(int blackStoneNum) {
        if (blackStoneNum <= -1 || 65 <= blackStoneNum){
            throw new IllegalArgumentException("blackStoneNumは0以上64以下でなければなりません。");
        }
        this.blackStoneNum = blackStoneNum;
    }

    public void setWhiteStoneNum(int whiteStoneNum) {
        if (whiteStoneNum <= -1 || 65 <= whiteStoneNum) {
            throw new IllegalArgumentException("whiteStoneNumは0以上64以下でなければなりません。");
        }
        this.whiteStoneNum = whiteStoneNum;
    }

    public Winner getWinner() {
        return winner;
    }

    public int getBlackStoneNum() {
        return blackStoneNum;
    }

    public int getWhiteStoneNum() {
        return whiteStoneNum;
    }
}
