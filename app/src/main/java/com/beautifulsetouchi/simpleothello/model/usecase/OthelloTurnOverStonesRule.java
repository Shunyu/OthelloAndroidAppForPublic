package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 指定した場所に石を置くシチュエーションにて、
 * 周囲の相手石を反転させたのちに、指定した場所に石を置く
 * という動作をおこなうクラス
 */
public class OthelloTurnOverStonesRule {

    OthelloBoardAllDirectionTurnOverExecutor othelloBoardAllDirectionTurnOverExecutor;
    OthelloBoardSpecificIndexTurnOverExecutor othelloBoardSpecificIndexTurnOverExecutor;

    public OthelloTurnOverStonesRule(
            OthelloBoardAllDirectionTurnOverExecutor othelloBoardAllDirectionTurnOverExecutor,
            OthelloBoardSpecificIndexTurnOverExecutor othelloBoardSpecificIndexTurnOverExecutor
    ) {
        this.othelloBoardAllDirectionTurnOverExecutor = othelloBoardAllDirectionTurnOverExecutor;
        this.othelloBoardSpecificIndexTurnOverExecutor = othelloBoardSpecificIndexTurnOverExecutor;
    }

    public void turnOverStones(UpdatableBoardIndex boardIndex){

        othelloBoardAllDirectionTurnOverExecutor.executeTurnOverStones(boardIndex);
        othelloBoardSpecificIndexTurnOverExecutor.executeTurnOverStone(boardIndex);


    }
}
