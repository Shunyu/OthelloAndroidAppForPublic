package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 特定のマスにおいて、特定の方向を考えた際に、
 * 反転させることができる相手の石の有無を確認するクラス
 */
public class OthelloSpecificDirectionTurnOverStoneExistenceChecker {

    OthelloTurnOverStoneCounter othelloTurnOverStoneCounter;

    public OthelloSpecificDirectionTurnOverStoneExistenceChecker(
            OthelloTurnOverStoneCounter othelloTurnOverStoneCounter
    ) {
        this.othelloTurnOverStoneCounter = othelloTurnOverStoneCounter;
    }

    public boolean turnOverStoneExistsInSpecificDirection(UpdatableBoardIndex boardIndex, BoardDirection boardDirection) {

        boolean turnOverStoneExistsFlag = false;

        int turnOverStoneNum = othelloTurnOverStoneCounter.countTurnOverStones(boardIndex, boardDirection);

        if (turnOverStoneNum > 0){
            turnOverStoneExistsFlag = true;
        }

        return turnOverStoneExistsFlag;
    }

}
