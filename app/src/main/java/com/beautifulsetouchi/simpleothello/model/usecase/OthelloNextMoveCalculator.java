package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloHintBoard;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.List;

/**
 * Viewからの求めを起点として、ViewModelからヒント盤面（次に石を置くことができる場所を示す盤面）の情報を求められた際に、
 * 次に石を置くことができる場所を計算するクラス
 */
public class OthelloNextMoveCalculator {

    OthelloBoardStatus othelloBoardStatus;
    OthelloRule othelloRule;
    OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGenerator;

    public OthelloNextMoveCalculator(
            OthelloBoardStatus othelloBoardStatus,
            OthelloRule othelloRule,
            OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGenerator
    ){
        this.othelloBoardStatus = othelloBoardStatus;
        this.othelloRule = othelloRule;
        this.othelloAllUpdatableBoardIndexListGenerator = othelloAllUpdatableBoardIndexListGenerator;
    }

    // 求めに応じてヒントを生成する
    public OthelloHintBoard calculateOthelloHintBoard(){

        OthelloHintBoard othelloHintBoard = new OthelloHintBoard();

        List<UpdatableBoardIndex> boardIndexList = othelloAllUpdatableBoardIndexListGenerator.getAllUpdatableBoardIndexList();
        Player player = othelloBoardStatus.getPlayer();

        for (UpdatableBoardIndex boardIndex : boardIndexList) {
            if (othelloRule.isLegalMove(boardIndex)){
                othelloHintBoard.setBoardStone(boardIndex, player);
            }
        }

        return othelloHintBoard;
    }

}
