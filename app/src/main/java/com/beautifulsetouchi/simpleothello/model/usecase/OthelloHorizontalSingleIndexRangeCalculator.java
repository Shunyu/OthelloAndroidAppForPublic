package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.List;

/**
 * 指定のマスについて、方向を指定した際に、
 * 横方向について、何番から何番までオセロ盤に収まるか取得するクラス
 */
public class OthelloHorizontalSingleIndexRangeCalculator extends OthelloSpecificSingleIndexRangeCalculator {

    public OthelloHorizontalSingleIndexRangeCalculator(
            OthelloSingleIndexRangeCalculator othelloSingleIndexRangeCalculator
    ) {
        super(othelloSingleIndexRangeCalculator);
    }

    @Override
    public List<Integer> getIndexRangeList(
            UpdatableBoardIndex boardIndex,
            BoardDirection boardDirection
    ) {
        int horizontalIndex = boardIndex.getHorizontalIndex();
        SingleBoardDirection horizontalDirection = boardDirection.getHorizontalDirection();

        List<Integer> horizontalIndexRangeList = othelloSingleIndexRangeCalculator.getIndexRangeList(horizontalIndex, horizontalDirection);

        return horizontalIndexRangeList;
    }

}
