package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

import java.util.List;

/**
 * 指定のマスについて、方向を指定した際に、
 * 縦もしくは横の単一方向について、何番から何番までオセロ盤面に収まるかを取得するクラスの基底クラス
 */
public abstract class OthelloSpecificSingleIndexRangeCalculator {

    OthelloSingleIndexRangeCalculator othelloSingleIndexRangeCalculator;

    public OthelloSpecificSingleIndexRangeCalculator(
            OthelloSingleIndexRangeCalculator othelloSingleIndexRangeCalculator
    ) {
        this.othelloSingleIndexRangeCalculator = othelloSingleIndexRangeCalculator;
    }

    public abstract List<Integer> getIndexRangeList(
            UpdatableBoardIndex boardIndex,
            BoardDirection boardDirection
    );


}
