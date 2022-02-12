package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.List;

/**
 * 指定のマスについて、方向を指定した際に、
 * 縦方向について、何番から何番までオセロ盤に収まるかを取得するクラス
 */
public class OthelloVerticalSingleIndexRangeCalculator extends OthelloSpecificSingleIndexRangeCalculator {

    public OthelloVerticalSingleIndexRangeCalculator(
            OthelloSingleIndexRangeCalculator othelloSingleIndexRangeCalculator
    ) {
        super(othelloSingleIndexRangeCalculator);
    }

    @Override
    public List<Integer> getIndexRangeList(
            UpdatableBoardIndex boardIndex,
            BoardDirection boardDirection
    ) {
        int verticalIndex = boardIndex.getVerticalIndex();
        SingleBoardDirection verticalDirection = boardDirection.getVerticalDirection();

        List<Integer> verticalIndexRangeList = othelloSingleIndexRangeCalculator.getIndexRangeList(verticalIndex, verticalDirection);

        return verticalIndexRangeList;
    }

}
