package com.beautifulsetouchi.simpleothello.model.usecase;

import android.util.Log;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 指定のマスに、現在の手番のPlayerが石を置ける際には、石を置いてゲームを更新し、
 * ゲームを更新した旨の情報を伝えるクラス
 *
 * 将来的には、Mementoパターンを採用して、過去の盤面と過去のステータスを保持しておき、遡れるようにしたい。
 */
public class OthelloTaskManager {

    OthelloRule othelloRule;
    OthelloGameUpdater othelloGameUpdater;

    public OthelloTaskManager(
            OthelloRule othelloRule,
            OthelloGameUpdater othelloGameUpdater
    ) {

        Log.i("md OthelloTaskManager", "construct");
        this.othelloRule = othelloRule;
        this.othelloGameUpdater = othelloGameUpdater;
    }

    public boolean isGameUpdate(UpdatableBoardIndex boardIndex){

        boolean gameUpdateFlag = false;

        // 合法手であるかどうかの判定は、モデル内でして、モデルを更新する。
        // モデルの更新があった旨の情報を伝える。
        // モデルの更新があった場合には、事後的に、Viewを更新することとする。
        if(othelloRule.isLegalMove(boardIndex)){

            othelloGameUpdater.updateGame(boardIndex);

            gameUpdateFlag = true;
        }

        return gameUpdateFlag;
    }

}
