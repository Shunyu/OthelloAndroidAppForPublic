package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 指定のマスから、指定の方向に、指定の個数だけずらした際に、
 * 縦方向に関して、オセロ盤面の64マスに入っているか確認するクラス
 */
public class OthelloBoardIndexVerticalRangeGeneratorChecker implements OthelloBoardIndexSingleRangeGeneratorChecker {

    @Override
    public boolean isUpdatableBoardIndex(
            UpdatableBoardIndex boardIndex,
            BoardDirection boardDirection,
            int offset
    ) {

        int verticalBoardIndex = boardIndex.getVerticalIndex();
        SingleBoardDirection verticalBoardDirection = boardDirection.getVerticalDirection();
        int verticalBoardIndexPlusOffset = verticalBoardIndex + offset * verticalBoardDirection.getSingleDirection();

        if (verticalBoardIndexPlusOffset <= 0 || 9 <= verticalBoardIndexPlusOffset) {
            return false;
        } else {
            return true;
        }

    }

}
