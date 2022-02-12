package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * 指定のマスから、指定の方向に、指定の個数だけずらした際に、
 * オセロ盤面の64マスに入っているか確認する際に
 * 実装すべきメソッドを示したインターフェース
 */
public interface OthelloBoardIndexSingleRangeGeneratorChecker {

    boolean isUpdatableBoardIndex(
            UpdatableBoardIndex boardIndex,
            BoardDirection boardDirection,
            int offset
    );
}
