package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.utilities.MinListLengthCalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * 指定のマス目から、指定の方向に進んだ際の、マスの一覧を取得するクラス
 */
public class OthelloBoardIndexRangeCalculator {

    OthelloVerticalSingleIndexRangeCalculator othelloVerticalSingleIndexRangeCalculator;
    OthelloHorizontalSingleIndexRangeCalculator othelloHorizontalSingleIndexRangeCalculator;
    MinListLengthCalculator minListLengthCalculator;

    public OthelloBoardIndexRangeCalculator(
            OthelloVerticalSingleIndexRangeCalculator othelloVerticalSingleIndexRangeCalculator,
            OthelloHorizontalSingleIndexRangeCalculator othelloHorizontalSingleIndexRangeCalculator,
            MinListLengthCalculator minListLengthCalculator
    ) {
        this.othelloVerticalSingleIndexRangeCalculator = othelloVerticalSingleIndexRangeCalculator;
        this.othelloHorizontalSingleIndexRangeCalculator = othelloHorizontalSingleIndexRangeCalculator;
        this.minListLengthCalculator = minListLengthCalculator;
    }

    public List<BoardIndex> getBoardIndexRangeList(
            UpdatableBoardIndex boardIndex,
            BoardDirection boardDirection
    ){

        List<Integer> verticalIndexRangeList = othelloVerticalSingleIndexRangeCalculator.getIndexRangeList(boardIndex, boardDirection);
        List<Integer> horizontalIndexRangeList = othelloHorizontalSingleIndexRangeCalculator.getIndexRangeList(boardIndex, boardDirection);


        int minListLength = minListLengthCalculator.getMinListLength(
                verticalIndexRangeList,
                horizontalIndexRangeList
        );

        List<BoardIndex> boardIndexRangeList = new ArrayList<>();
        for (int count = 0; count < minListLength; count++){
            int nextVerticalIndex = verticalIndexRangeList.get(count);
            int nextHorizontalIndex = horizontalIndexRangeList.get(count);
            BoardIndex nextBoardIndex = new BoardIndex(nextVerticalIndex, nextHorizontalIndex);
            boardIndexRangeList.add(nextBoardIndex);
        }

        return boardIndexRangeList;
    }
}
