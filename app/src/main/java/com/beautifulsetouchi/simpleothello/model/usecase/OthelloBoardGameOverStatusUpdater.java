package com.beautifulsetouchi.simpleothello.model.usecase;

import android.util.Log;

import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;

/**
 * othelloBoardStatusについて、ステータスをゲームオーバーに変更するクラス
 */
public class OthelloBoardGameOverStatusUpdater {

    OthelloBoardStatus othelloBoardStatus;

    public OthelloBoardGameOverStatusUpdater(
            OthelloBoardStatus othelloBoardStatus
    ) {
        this.othelloBoardStatus = othelloBoardStatus;
    }

    public void updateOthelloBoardStatusToGameOver() {

        // 状態のアップデート
        othelloBoardStatus.setGameOverFlag(true);
        Log.i("setGameOverFlag", "true");

    }
}
