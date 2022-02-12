package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;

/**
 * Playerについて、次のPlayerに移行させるクラス
 * 現在の手番の相手Playerを取得して、相手PlayerをPlayerにセットする。
 */
public class OthelloBoardPlayerStatusUpdater {

    OthelloBoardStatus othelloBoardStatus;

    public OthelloBoardPlayerStatusUpdater(
            OthelloBoardStatus othelloBoardStatus
    ) {
        this.othelloBoardStatus = othelloBoardStatus;
    }

    public void updateOthelloBoardPlayerStatus(){
        Player nextPlayer = othelloBoardStatus.getOpponentPlayer();
        othelloBoardStatus.setPlayer(nextPlayer);
    }
}
