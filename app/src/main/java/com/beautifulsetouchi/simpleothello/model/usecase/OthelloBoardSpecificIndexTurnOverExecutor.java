package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 指定した一つのマスに対して、そのマスに、現在のPlayerの石を置くクラス
 */
public class OthelloBoardSpecificIndexTurnOverExecutor {
    OthelloBoard othelloBoard;
    OthelloBoardStatus othelloBoardStatus;

    public OthelloBoardSpecificIndexTurnOverExecutor(
            OthelloBoard othelloBoard,
            OthelloBoardStatus othelloBoardStatus
    ){
        this.othelloBoard = othelloBoard;
        this.othelloBoardStatus = othelloBoardStatus;
    }

    public void executeTurnOverStone(UpdatableBoardIndex boardIndex) {

        Player player = othelloBoardStatus.getPlayer();
        othelloBoard.setBoardStone(boardIndex, player);

    }
}
