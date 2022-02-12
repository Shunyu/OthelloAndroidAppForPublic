package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.List;

/**
 * 指定されたマスに石を置き、相手石の反転を実行するクラス
 */
public class OthelloBoardAllDirectionTurnOverExecutor {

    OthelloAllBoardDirectionListGenerator othelloAllBoardDirectionListGenerator;
    OthelloBoardSpecificDirectionTurnOverExecutor othelloBoardSpecificDirectionTurnOverExecutor;

    public OthelloBoardAllDirectionTurnOverExecutor(
            OthelloAllBoardDirectionListGenerator othelloAllBoardDirectionListGenerator,
            OthelloBoardSpecificDirectionTurnOverExecutor othelloBoardSpecificDirectionTurnOverExecutor
    ) {
        this.othelloAllBoardDirectionListGenerator = othelloAllBoardDirectionListGenerator;
        this.othelloBoardSpecificDirectionTurnOverExecutor = othelloBoardSpecificDirectionTurnOverExecutor;
    }

    public void executeTurnOverStones(
            UpdatableBoardIndex boardIndex
    ) {
        List<BoardDirection> allBoardDirectionList = othelloAllBoardDirectionListGenerator.getAllBoardDirectionList();
        for (BoardDirection boardDirection : allBoardDirectionList) {
            othelloBoardSpecificDirectionTurnOverExecutor.executeTurnOverStones(boardIndex, boardDirection);
        }
    }
}
