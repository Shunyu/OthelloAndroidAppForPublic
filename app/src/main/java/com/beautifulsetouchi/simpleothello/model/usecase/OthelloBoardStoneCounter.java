package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStoneNum;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.List;

import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.BLACK;
import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.WHITE;

/**
 * オセロ盤面を確認して、現在の黒石の個数、白石の個数を計算するクラス
 */
public class OthelloBoardStoneCounter {

    OthelloBoard othelloBoard;
    OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGenerator;

    public OthelloBoardStoneCounter(
            OthelloBoard othelloBoard,
            OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGenerator
    ) {
        this.othelloBoard = othelloBoard;
        this.othelloAllUpdatableBoardIndexListGenerator = othelloAllUpdatableBoardIndexListGenerator;
    }

    public OthelloBoardStoneNum countStoneNum(){

        OthelloBoardStoneNum othelloBoardStoneNum = new OthelloBoardStoneNum();

        int blackStoneNum = 0;
        int whiteStoneNum = 0;

        List<UpdatableBoardIndex> allUpdatableBoardIndexList = othelloAllUpdatableBoardIndexListGenerator.getAllUpdatableBoardIndexList();

        for (UpdatableBoardIndex boardIndex : allUpdatableBoardIndexList){
            switch(othelloBoard.getBoardStone(boardIndex)){
                case BLACK:
                    blackStoneNum++;
                    break;
                case WHITE:
                    whiteStoneNum++;
                    break;
            }
        }

        othelloBoardStoneNum.setBlackStoneNum(blackStoneNum);
        othelloBoardStoneNum.setWhiteStoneNum(whiteStoneNum);

        return othelloBoardStoneNum;
    }
}
