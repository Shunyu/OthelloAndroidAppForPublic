package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 指定のマスから、指定の方向に、指定の個数だけずらした際に、
 * 横方向に関して、オセロ盤面の64マスに入っているか確認するクラス
 */
public class OthelloBoardIndexHorizontalRangeGeneratorChecker implements OthelloBoardIndexSingleRangeGeneratorChecker{

    @Override
    public boolean isUpdatableBoardIndex(
            UpdatableBoardIndex boardIndex,
            BoardDirection boardDirection,
            int offset
    ) {

        int horizontalBoardIndex = boardIndex.getHorizontalIndex();
        SingleBoardDirection horizontalBoardDirection = boardDirection.getHorizontalDirection();
        int horizontalBoardIndexPlusOffset = horizontalBoardIndex + offset * horizontalBoardDirection.getSingleDirection();

        if (horizontalBoardIndexPlusOffset <= 0 || 9 <= horizontalBoardIndexPlusOffset) {
            return false;
        } else {
            return true;
        }

    }
}
