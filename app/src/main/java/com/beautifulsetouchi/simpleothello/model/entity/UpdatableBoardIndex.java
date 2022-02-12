package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;

/**
 * オセロ盤面の場所を指し示す情報を格納するクラス
 * ただし、64マス+外周の32マスのうち、外周32マスを除く、オセロ石が置かれる場所のみに限定して、場所を指し示す。
 */
public class UpdatableBoardIndex extends BoardIndex {

    public UpdatableBoardIndex(
            int verticalIndex,
            int horizontalIndex
    ) {

        super(verticalIndex, horizontalIndex);

        if (verticalIndex <= 0 || verticalIndex >= 9){
            throw new IllegalArgumentException("指定するverticalIndexは1から8の範囲である必要があります。verticalIndex:"+verticalIndex);
        }
        if (horizontalIndex <= 0 || horizontalIndex >= 9){
            throw new IllegalArgumentException("指定するhorizontalIndexは1から8の範囲である必要があります。horizontalIndex:"+horizontalIndex);
        }

    }

    public void setVerticalIndex(int verticalIndex) {
        if (verticalIndex <= 0 || verticalIndex >= 9){
            throw new IllegalArgumentException("指定するverticalIndexは1から8の範囲である必要があります。verticalIndex:"+verticalIndex);
        }

        super.setVerticalIndex(verticalIndex);
    }

    public void setHorizontalIndex(int horizontalIndex) {
        if (horizontalIndex <= 0 || horizontalIndex >= 9){
            throw new IllegalArgumentException("指定するhorizontalIndexは1から8の範囲である必要があります。horizontalIndex:"+horizontalIndex);
        }

        super.setHorizontalIndex(horizontalIndex);
    }

}
