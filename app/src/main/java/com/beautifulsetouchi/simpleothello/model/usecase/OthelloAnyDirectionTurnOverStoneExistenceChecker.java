package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.List;

/**
 * 指定されたマスの周囲8方向を確認して、
 * 反転させることのできる相手石が、どの方向に存在しているか確認し
 * 反転させることのできる相手石が、1方向でも存在すればtrueを返すクラス
 */
public class OthelloAnyDirectionTurnOverStoneExistenceChecker {

    OthelloAllBoardDirectionListGenerator othelloAllBoardDirectionListGenerator;
    OthelloSpecificDirectionTurnOverStoneExistenceChecker othelloSpecificDirectionTurnOverStoneExistenceChecker;


    public OthelloAnyDirectionTurnOverStoneExistenceChecker(
            OthelloAllBoardDirectionListGenerator othelloAllBoardDirectionListGenerator,
            OthelloSpecificDirectionTurnOverStoneExistenceChecker othelloSpecificDirectionTurnOverStoneExistenceChecker
    ) {
        this.othelloAllBoardDirectionListGenerator = othelloAllBoardDirectionListGenerator;
        this.othelloSpecificDirectionTurnOverStoneExistenceChecker = othelloSpecificDirectionTurnOverStoneExistenceChecker;
    }
    
    public boolean turnOverStoneExistsInAnyDirections(UpdatableBoardIndex boardIndex){

        boolean turnOverStoneExistsFlag = false;

        List<BoardDirection> allBoardDirectionList = othelloAllBoardDirectionListGenerator.getAllBoardDirectionList();
        for (BoardDirection boardDirection : allBoardDirectionList) {
            boolean turnOverStoneExistsInSpecificDirectionFlag = othelloSpecificDirectionTurnOverStoneExistenceChecker.turnOverStoneExistsInSpecificDirection(boardIndex, boardDirection);
            if (turnOverStoneExistsInSpecificDirectionFlag) {
                turnOverStoneExistsFlag = true;
                break;
            }
        }

        return turnOverStoneExistsFlag;
    }


}
