package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.constant.Winner;

/**
 * オセロゲームが終了した際に、
 * 勝者
 * ゲームオーバーの結果、再スタートになったのか
 * 再スタートボタンが押された結果、再スタートになったのか
 * の情報を伝達するためのクラス
 */
public class OthelloResultStatus {

    private Winner winner;
    private boolean gameOverFlag;
    private boolean restartFlag;

    public OthelloResultStatus(Winner winner, boolean gameOverFlag, boolean restartFlag){
        this.winner = winner;
        this.gameOverFlag = gameOverFlag;
        this.restartFlag = restartFlag;
    }

    public Winner getWinner() {
        return winner;
    }

    public boolean isGameOverFlag() {
        return gameOverFlag;
    }

    public boolean isRestartFlag() {
        return restartFlag;
    }
}
