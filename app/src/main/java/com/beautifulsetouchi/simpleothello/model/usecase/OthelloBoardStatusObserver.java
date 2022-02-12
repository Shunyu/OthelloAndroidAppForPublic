package com.beautifulsetouchi.simpleothello.model.usecase;


/**
 * メソッドを呼ばれたタイミングで、
 * オセロゲームの状態（盤面・プレーヤーなど）の情報のアップデートを行うObserverクラス
 */
public class OthelloBoardStatusObserver {

    OthelloBoardStoneStatusUpdater othelloBoardStoneStatusUpdater;
    OthelloBoardPlayerStatusUpdater othelloBoardPlayerStatusUpdater;
    OthelloBoardGameOverStatusUpdater othelloBoardGameOverStatusUpdater;
    OthelloGameResultUpdater othelloGameResultUpdater;

    public OthelloBoardStatusObserver(
            OthelloBoardStoneStatusUpdater othelloBoardStoneStatusUpdater,
            OthelloBoardPlayerStatusUpdater othelloBoardPlayerStatusUpdater,
            OthelloBoardGameOverStatusUpdater othelloBoardGameOverStatusUpdater,
            OthelloGameResultUpdater othelloGameResultUpdater
    ){
        this.othelloBoardStoneStatusUpdater = othelloBoardStoneStatusUpdater;
        this.othelloBoardPlayerStatusUpdater = othelloBoardPlayerStatusUpdater;
        this.othelloBoardGameOverStatusUpdater = othelloBoardGameOverStatusUpdater;
        this.othelloGameResultUpdater = othelloGameResultUpdater;
    }


    public void updatePlayerStatus(){

        // 状態のアップデート（プレーヤー変更）
        othelloBoardPlayerStatusUpdater.updateOthelloBoardPlayerStatus();

    }

    public void updateOthelloBoardStatus() {

        // 状態のアップデート（盤面の状態）
        othelloBoardStoneStatusUpdater.updateOthelloBoardStoneStatus();

        // 状態のアップデート（プレーヤー変更）
        othelloBoardPlayerStatusUpdater.updateOthelloBoardPlayerStatus();

    }

    public void updateOthelloBoardStatusToGameOver() {

        // 状態のアップデート（盤面の状態）
        othelloBoardStoneStatusUpdater.updateOthelloBoardStoneStatus();

        // 状態のアップデート（ゲーム終了）
        othelloBoardGameOverStatusUpdater.updateOthelloBoardStatusToGameOver();

        // 状態のアップデート（ゲームの結果の更新）
        othelloGameResultUpdater.updateOthelloGameResult();

    }

}
