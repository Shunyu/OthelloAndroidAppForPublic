package com.beautifulsetouchi.simpleothello.model.usecase;

import android.util.Log;

import com.beautifulsetouchi.simpleothello.model.constant.Winner;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloGameResult;


/**
 * ゲーム終了時に、最終的な黒石・白石の個数や、勝者を確認し、
 * othelloGameResultにそれらの情報をセットするクラス
 */
public class OthelloGameResultUpdater {

    OthelloBoardStatus othelloBoardStatus;
    OthelloGameResult othelloGameResult;

    public OthelloGameResultUpdater(
            OthelloBoardStatus othelloBoardStatus,
            OthelloGameResult othelloGameResult
    ) {
        this.othelloBoardStatus = othelloBoardStatus;
        this.othelloGameResult= othelloGameResult;
    }

    public void updateOthelloGameResult(){

        int blackStoneNum = othelloBoardStatus.getBlackStoneNum();
        int whiteStoneNum = othelloBoardStatus.getWhiteStoneNum();

        othelloGameResult.setBlackStoneNum(blackStoneNum);
        othelloGameResult.setWhiteStoneNum(whiteStoneNum);

        // 結果の生成
        // 最終結果としての石数もほしい
        if ((blackStoneNum - whiteStoneNum) > 0) {
            othelloGameResult.setWinner(Winner.BLACK);
        }
        else if ((blackStoneNum - whiteStoneNum) < 0) {
            othelloGameResult.setWinner(Winner.WHITE);
        }
        else {
            othelloGameResult.setWinner(Winner.DRAW);
        }

        Log.i("winner", String.valueOf(othelloGameResult.getWinner()));
        Log.i("blackStoneNum", String.valueOf(othelloGameResult.getBlackStoneNum()));
        Log.i("whiteStoneNum", String.valueOf(othelloGameResult.getWhiteStoneNum()));
    }
}
