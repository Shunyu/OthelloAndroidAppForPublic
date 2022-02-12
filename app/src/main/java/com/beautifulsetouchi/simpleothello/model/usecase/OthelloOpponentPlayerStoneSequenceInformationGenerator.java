package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloOpponentPlayerStoneSequenceInformation;

import java.util.List;

/**
 * 何番目かの要素で、相手Playerの石以外のマスが登場するようなリストを与えた際に、
 * 相手Playerの石が何個連続していたか、実際に相手Player以外の石は存在していたかの情報を生成するクラス
 */
public class OthelloOpponentPlayerStoneSequenceInformationGenerator {

    OthelloBoard othelloBoard;
    OthelloBoardStatus othelloBoardStatus;

    public OthelloOpponentPlayerStoneSequenceInformationGenerator(
            OthelloBoard othelloBoard,
            OthelloBoardStatus othelloBoardStatus
    ) {
        this.othelloBoard = othelloBoard;
        this.othelloBoardStatus = othelloBoardStatus;
    }

    public OthelloOpponentPlayerStoneSequenceInformation getOthelloOpponentPlayerStoneSequenceInformation(
            List<BoardIndex> boardIndexRangeList
    ){

        Player opponentPlayer = othelloBoardStatus.getOpponentPlayer();
        OthelloOpponentPlayerStoneSequenceInformation othelloOpponentPlayerStoneSequenceInformation = new OthelloOpponentPlayerStoneSequenceInformation();

        int boardIndexRangeListLength = boardIndexRangeList.size();
        if(boardIndexRangeListLength == 0 || 9 <= boardIndexRangeListLength) {
            throw new IllegalArgumentException("入力に与えるboardIndexRangeListが長すぎます。");
        }

        for (int count = 0; count < boardIndexRangeListLength; count++) {

            BoardIndex nextBoardIndex = boardIndexRangeList.get(count);
            int nextBoardStatus = othelloBoard.getBoardStone(nextBoardIndex);

           if (nextBoardStatus == opponentPlayer.getPlayer()) {
                othelloOpponentPlayerStoneSequenceInformation.incrementOpponentPlayerStoneNum();
            } else {
                othelloOpponentPlayerStoneSequenceInformation.setNonOpponentPlayerExistenceFlag(true);
                break;
            }
        }

        return othelloOpponentPlayerStoneSequenceInformation;
    }
}
