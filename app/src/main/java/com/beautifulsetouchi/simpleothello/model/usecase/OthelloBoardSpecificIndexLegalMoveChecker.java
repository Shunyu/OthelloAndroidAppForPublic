package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 指定のマスに対して、現在の手番のPlayerは、石を置くことができるのかを判定するクラス
 * まず、石が既に置かれていないかを判定したのち、
 * 石が置かれていない場合には、反転させることができる石が周囲に存在しているかどうかを確認している。
 */
public class OthelloBoardSpecificIndexLegalMoveChecker {

    OthelloBoardStonePutablePositionChecker othelloBoardStonePutablePositionChecker;
    OthelloAnyDirectionTurnOverStoneExistenceChecker othelloAnyDirectionTurnOverStoneExistenceChecker;

    public OthelloBoardSpecificIndexLegalMoveChecker(
            OthelloBoardStonePutablePositionChecker othelloBoardStonePutablePositionChecker,
            OthelloAnyDirectionTurnOverStoneExistenceChecker othelloAnyDirectionTurnOverStoneExistenceChecker
    ) {
        this.othelloBoardStonePutablePositionChecker = othelloBoardStonePutablePositionChecker;
        this.othelloAnyDirectionTurnOverStoneExistenceChecker = othelloAnyDirectionTurnOverStoneExistenceChecker;
    }

    public boolean isLegalMove(UpdatableBoardIndex boardIndex){

        boolean isLegalMoveFlag;

        boolean putableFlag = othelloBoardStonePutablePositionChecker.canPutBoardStone(boardIndex);
        if (putableFlag) {
            boolean turnOverStoneExistsFlag = othelloAnyDirectionTurnOverStoneExistenceChecker.turnOverStoneExistsInAnyDirections(boardIndex);
            if (turnOverStoneExistsFlag) {
                isLegalMoveFlag = true;
            } else {
                isLegalMoveFlag = false;
            }
        } else {
            isLegalMoveFlag = false;
        }

        return isLegalMoveFlag;

    }

}
