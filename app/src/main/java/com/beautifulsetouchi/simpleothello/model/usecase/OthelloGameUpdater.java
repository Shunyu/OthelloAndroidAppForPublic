package com.beautifulsetouchi.simpleothello.model.usecase;

import android.util.Log;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 石を置いて、石の反転を行い、盤面を更新し、
 * 次の手番のPlayerについて、石を置ける場所が存在する場合には処理を終え、
 * 存在しない場合には、Playerを変更して、石を置ける場所が存在するか確認する。
 * 存在する場合には、処理を終え、
 * 存在しない場合には、オセロゲームを終了させる処理を開始する。
 * そのようなオセロゲームの流れをつかさどるクラス
 */
public class OthelloGameUpdater {

    OthelloRule othelloRule;
    OthelloBoardStatusObserver othelloBoardStatusObserver;

    public OthelloGameUpdater(
            OthelloRule othelloRule,
            OthelloBoardStatusObserver othelloBoardStatusObserver
    ) {

        Log.i("md OthelloGameUpdater", "construct");
        this.othelloRule = othelloRule;
        this.othelloBoardStatusObserver = othelloBoardStatusObserver;
    }

    void notifyOthelloBoardForOthelloBoardStatusObserver(){
        othelloBoardStatusObserver.updateOthelloBoardStatus();
    }

    void notifyPlayerSwappingForOthelloBoardStatusObserver(){
        othelloBoardStatusObserver.updatePlayerStatus();
    }

    void notifyGameOverForOthelloBoardStatusObserver(){
        othelloBoardStatusObserver.updateOthelloBoardStatusToGameOver();
    }

    // スコープ
    public void updateGame(UpdatableBoardIndex boardIndex){

        // ロジック
        othelloRule.turnOverStones(boardIndex);
        notifyOthelloBoardForOthelloBoardStatusObserver();

        boolean legalMoveExistsFlagAtFirstTime = othelloRule.legalMoveExists();
        if (legalMoveExistsFlagAtFirstTime) {
            return;
        } else {
            notifyPlayerSwappingForOthelloBoardStatusObserver();
            boolean legalMoveExistsFlagAtSecondTime = othelloRule.legalMoveExists();
            if (legalMoveExistsFlagAtSecondTime) {
                return;
            } else {
                notifyGameOverForOthelloBoardStatusObserver();
                return;
            }

        }
    }

}
