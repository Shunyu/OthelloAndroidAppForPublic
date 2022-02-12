package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.List;

/**
 * 現在の盤面の状況や、手番の状況を踏まえて、
 * 現在の手番のPlayerにとって、石を置ける場所が存在するかどうかを確認するクラス
 */
public class OthelloBoardLegalMoveChecker {

    OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGenerator;
    OthelloBoardSpecificIndexLegalMoveChecker othelloBoardSpecificIndexLegalMoveChecker;

    public OthelloBoardLegalMoveChecker(
            OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGenerator,
            OthelloBoardSpecificIndexLegalMoveChecker othelloBoardSpecificIndexLegalMoveChecker
    ) {
        this.othelloAllUpdatableBoardIndexListGenerator = othelloAllUpdatableBoardIndexListGenerator;
        this.othelloBoardSpecificIndexLegalMoveChecker = othelloBoardSpecificIndexLegalMoveChecker;
    }

    public boolean legalMoveExists() {
        boolean legalMoveExistsFlag = false;

        List<UpdatableBoardIndex> allUpdatableBoardIndexList = othelloAllUpdatableBoardIndexListGenerator.getAllUpdatableBoardIndexList();

        for (UpdatableBoardIndex boardIndex : allUpdatableBoardIndexList) {
            if (othelloBoardSpecificIndexLegalMoveChecker.isLegalMove(boardIndex)) {
                legalMoveExistsFlag = true;
                break;
            }
        }
        return legalMoveExistsFlag;
    }

}
