package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.List;

/**
 * 指定したマス目一覧に対して、そのマスに、現在のPlayerの石を置くクラス
 */
public class OthelloBoardSpecificIndexListTurnOverExecutor {
    OthelloBoard othelloBoard;
    OthelloBoardStatus othelloBoardStatus;

    public OthelloBoardSpecificIndexListTurnOverExecutor(
            OthelloBoard othelloBoard,
            OthelloBoardStatus othelloBoardStatus
    ){
        this.othelloBoard = othelloBoard;
        this.othelloBoardStatus = othelloBoardStatus;
    }

    public void executeTurnOverStones(List<UpdatableBoardIndex> boardIndexList) {

        Player player = othelloBoardStatus.getPlayer();
        othelloBoard.setBoardStones(boardIndexList, player);
        
    }
}
