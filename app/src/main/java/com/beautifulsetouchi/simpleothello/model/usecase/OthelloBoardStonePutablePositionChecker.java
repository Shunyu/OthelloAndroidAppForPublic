package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.PUTABLE;

/**
 * 特定のマスについて、石を置くことができるか否かを確認するクラス
 */
public class OthelloBoardStonePutablePositionChecker {

    OthelloBoard othelloBoard;

    public OthelloBoardStonePutablePositionChecker(
            OthelloBoard othelloBoard
    ) {
        this.othelloBoard = othelloBoard;
    }

    public boolean canPutBoardStone(UpdatableBoardIndex boardIndex) {

        boolean putableFlag;
        int boardStatus = othelloBoard.getBoardStone(boardIndex);

        if (boardStatus == PUTABLE){
            putableFlag = true;
        } else {
            putableFlag = false;
        }

        return putableFlag;
    }

}
