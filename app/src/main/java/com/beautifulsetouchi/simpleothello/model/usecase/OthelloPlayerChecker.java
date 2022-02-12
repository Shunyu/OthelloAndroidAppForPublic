package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 特定のマスからみて、特定の方向に、指定の個数だけずらしたマスを考え、
 * そのマスにはどちらの石が置かれているのか
 * そのマスは石を置ける場所なのか・置けない場所なのか
 * といった盤面の状態を考えた後、
 * 現在の手番のPlayerの石がそこに置かれているかを確認するクラス
 */
public class OthelloPlayerChecker {

    OthelloBoard othelloBoard;
    OthelloBoardStatus othelloBoardStatus;
    OthelloBoardIndexAdder othelloBoardIndexAdder;

    public OthelloPlayerChecker(
            OthelloBoard othelloBoard,
            OthelloBoardStatus othelloBoardStatus,
            OthelloBoardIndexAdder othelloBoardIndexAdder
    ){
        this.othelloBoard = othelloBoard;
        this.othelloBoardStatus = othelloBoardStatus;
        this.othelloBoardIndexAdder = othelloBoardIndexAdder;
    }

    public boolean isMyself(UpdatableBoardIndex boardIndex, BoardDirection boardDirection, int targetOffset) {

        BoardIndex targetBoardIndex = othelloBoardIndexAdder.addBoardIndex(boardIndex, boardDirection, targetOffset);
        int targetBoardStatus = othelloBoard.getBoardStone(targetBoardIndex);
        Player myself = othelloBoardStatus.getPlayer();

        if (targetBoardStatus == myself.getPlayer()){
            return true;
        } else {
            return false;
        }
    }
}
