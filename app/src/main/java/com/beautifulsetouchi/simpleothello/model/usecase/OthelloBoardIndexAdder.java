package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 指定のマスから見て、指定の方向に、指定の個数だけ進んだ際のマスの場所を取得するクラス
 */
public class OthelloBoardIndexAdder {

    public BoardIndex addBoardIndex(
            UpdatableBoardIndex boardIndex,
            BoardDirection boardDirection,
            int targetOffset
    ) {

        if (targetOffset<=0||9<=targetOffset) {
            throw new IllegalArgumentException("targetOffsetは1以上8以下です。");
        }

        int verticalIndex = boardIndex.getVerticalIndex();
        int horizontalIndex = boardIndex.getHorizontalIndex();

        SingleBoardDirection verticalDirection = boardDirection.getVerticalDirection();
        SingleBoardDirection horizontalDirection = boardDirection.getHorizontalDirection();

        int targetVerticalIndex = verticalIndex + targetOffset * verticalDirection.getSingleDirection();
        int targetHorizontalIndex = horizontalIndex + targetOffset * horizontalDirection.getSingleDirection();

        if (targetVerticalIndex < 0 || 9 < targetVerticalIndex) {
            throw new IllegalArgumentException("verticalIndexが範囲外です。");
        }
        if (targetHorizontalIndex < 0 || 9 < targetHorizontalIndex) {
            throw new IllegalArgumentException("horizontalIndexが範囲外です。");
        }

        BoardIndex targetBoardIndex = new BoardIndex(targetVerticalIndex, targetHorizontalIndex);

        return targetBoardIndex;
    }
}
