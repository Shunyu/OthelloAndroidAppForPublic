package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;

/**
 * BoardIndexについて、縦方向・横方向の位置が、
 * オセロ盤面の64マスの中であれば（外周の32マスでなければ）
 * UpdatableBoardIndexにキャストするクラス
 */
public class BoardIndexCastor {

    public UpdatableBoardIndex castToUpdatableBoardIndex(
            BoardIndex targetBoardIndex
    ) {

        int targetVerticalBoardIndex = targetBoardIndex.getVerticalIndex();
        int targetHorizontalBoardIndex = targetBoardIndex.getHorizontalIndex();

        if (targetVerticalBoardIndex <= 0 || 9 <= targetVerticalBoardIndex) {
            throw new RuntimeException("targetVerticalBoardIndexは1以上8以下でなければなりません。");
        }
        if (targetHorizontalBoardIndex <= 0 || 9 <= targetHorizontalBoardIndex) {
            throw new RuntimeException("targetHorizontalBoardIndexは1以上8以下でなければなりません。");
        }

        UpdatableBoardIndex targetUpdatableBoardIndex = new UpdatableBoardIndex(
                targetVerticalBoardIndex,
                targetHorizontalBoardIndex
        );

        return targetUpdatableBoardIndex;
    }
}
