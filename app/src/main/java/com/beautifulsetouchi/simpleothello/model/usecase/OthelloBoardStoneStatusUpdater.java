package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStoneNum;

/**
 * オセロ盤面の黒石・白石の個数を数えるように依頼して、
 * 取得した石の個数をothelloBoardStatusにセットして、現在の状況を反映させるクラス
 */
public class OthelloBoardStoneStatusUpdater {

    OthelloBoardStoneCounter othelloBoardStoneCounter;
    OthelloBoardStatus othelloBoardStatus;

    public OthelloBoardStoneStatusUpdater(
            OthelloBoardStoneCounter othelloBoardStoneCounter,
            OthelloBoardStatus othelloBoardStatus
    ) {
        this.othelloBoardStoneCounter = othelloBoardStoneCounter;
        this.othelloBoardStatus = othelloBoardStatus;
    }

    public void updateOthelloBoardStoneStatus() {

        OthelloBoardStoneNum othelloBoardStoneNum = othelloBoardStoneCounter.countStoneNum();
        othelloBoardStatus.setOthelloBoardStoneNum(othelloBoardStoneNum);

    }
}
