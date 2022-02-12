package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.List;

/**
 * 指定のマスにおいて、指定の方向に関して、相手石の反転を実施するクラス
 */
public class OthelloBoardSpecificDirectionTurnOverExecutor {

    OthelloTurnOverStoneCounter othelloTurnOverStoneCounter;
    OthelloBoardIndexRangeGenerator othelloBoardIndexRangeGenerator;
    OthelloBoardSpecificIndexListTurnOverExecutor othelloBoardSpecificIndexListTurnOverExecutor;

    public OthelloBoardSpecificDirectionTurnOverExecutor(
            OthelloTurnOverStoneCounter othelloTurnOverStoneCounter,
            OthelloBoardIndexRangeGenerator othelloBoardIndexRangeGenerator,
            OthelloBoardSpecificIndexListTurnOverExecutor othelloBoardSpecificIndexListTurnOverExecutor
    ) {
        this.othelloTurnOverStoneCounter = othelloTurnOverStoneCounter;
        this.othelloBoardIndexRangeGenerator = othelloBoardIndexRangeGenerator;
        this.othelloBoardSpecificIndexListTurnOverExecutor = othelloBoardSpecificIndexListTurnOverExecutor;

    }

    public void executeTurnOverStones(
            UpdatableBoardIndex boardIndex,
            BoardDirection boardDirection
    ) {
        int count = othelloTurnOverStoneCounter.countTurnOverStones(boardIndex, boardDirection);

        if (1 <= count & count <= 6){
            List<UpdatableBoardIndex> targetBoardIndexRangeList = othelloBoardIndexRangeGenerator.generateBoardIndexRangeList(
                    boardIndex, boardDirection, 1, count
            );
            othelloBoardSpecificIndexListTurnOverExecutor.executeTurnOverStones(targetBoardIndexRangeList);
        } else if (count == 0) {
            // なにも処理を行わない。
            ;
        } else {
            throw new RuntimeException("特定の方向について見たとき、反転させる石の数は0以上6以下です。");
        }

    }
}
