package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 指定のマスから、指定の方向に、指定の個数だけずらした際に、
 * オセロ盤面の64マスに入っているか確認するクラス
 */
public class OthelloBoardIndexRangeGeneratorChecker implements OthelloBoardIndexSingleRangeGeneratorChecker{

    OthelloBoardIndexVerticalRangeGeneratorChecker othelloBoardIndexVerticalRangeGeneratorChecker;
    OthelloBoardIndexHorizontalRangeGeneratorChecker othelloBoardIndexHorizontalRangeGeneratorChecker;

    public OthelloBoardIndexRangeGeneratorChecker(
            OthelloBoardIndexVerticalRangeGeneratorChecker othelloBoardIndexVerticalRangeGeneratorChecker,
            OthelloBoardIndexHorizontalRangeGeneratorChecker othelloBoardIndexHorizontalRangeGeneratorChecker
    ) {
        this.othelloBoardIndexVerticalRangeGeneratorChecker = othelloBoardIndexVerticalRangeGeneratorChecker;
        this.othelloBoardIndexHorizontalRangeGeneratorChecker = othelloBoardIndexHorizontalRangeGeneratorChecker;
    }

    @Override
    public boolean isUpdatableBoardIndex(
            UpdatableBoardIndex boardIndex,
            BoardDirection boardDirection,
            int offset
    ) {

        boolean verticalFlag = othelloBoardIndexVerticalRangeGeneratorChecker.isUpdatableBoardIndex(
                boardIndex,
                boardDirection,
                offset
        );

        boolean horizontalFlag = othelloBoardIndexHorizontalRangeGeneratorChecker.isUpdatableBoardIndex(
                boardIndex,
                boardDirection,
                offset
        );

        if (verticalFlag && horizontalFlag) {
            return true;
        } else {
            return false;
        }

    }
}
