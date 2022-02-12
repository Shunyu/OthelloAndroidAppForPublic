package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloOpponentPlayerStoneSequenceInformation;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.List;

/**
 * 特定のマスにおいて、特定の方向を考えて、
 * その方向に相手Playerの石が何個連続しているかを計算するクラス
 */
public class OthelloOpponentPlayerStoneCounter {

    OthelloBoardIndexRangeCalculator othelloBoardIndexRangeCalculator;
    OthelloOpponentPlayerStoneSequenceInformationGenerator othelloOpponentPlayerStoneSequenceInformationGenerator;

    public OthelloOpponentPlayerStoneCounter(
            OthelloBoardIndexRangeCalculator othelloBoardIndexRangeCalculator,
            OthelloOpponentPlayerStoneSequenceInformationGenerator othelloOpponentPlayerStoneSequenceInformationGenerator
    ){
        this.othelloBoardIndexRangeCalculator = othelloBoardIndexRangeCalculator;
        this.othelloOpponentPlayerStoneSequenceInformationGenerator = othelloOpponentPlayerStoneSequenceInformationGenerator;
    }

    public int countOpponentPlayerStones(UpdatableBoardIndex boardIndex, BoardDirection boardDirection) {

        List<BoardIndex> boardIndexRangeList = othelloBoardIndexRangeCalculator.getBoardIndexRangeList(boardIndex, boardDirection);
        OthelloOpponentPlayerStoneSequenceInformation othelloOpponentPlayerStoneSequenceInformation = othelloOpponentPlayerStoneSequenceInformationGenerator.getOthelloOpponentPlayerStoneSequenceInformation(boardIndexRangeList);

        boolean nonOpponentPlayerExistenceFlag = othelloOpponentPlayerStoneSequenceInformation.isNonOpponentPlayerExistenceFlag();
        int opponentPlayerStoneNum = othelloOpponentPlayerStoneSequenceInformation.getOpponentPlayerStoneNum();

        if (nonOpponentPlayerExistenceFlag) {
            return opponentPlayerStoneNum;
        } else {
            throw new RuntimeException("forループ内の処理を実施してもなお、終了条件である「nextBoardIndexPlayer!=opponentPlayer」を満たさなかったため、異常終了します。");
        }
    }
}
