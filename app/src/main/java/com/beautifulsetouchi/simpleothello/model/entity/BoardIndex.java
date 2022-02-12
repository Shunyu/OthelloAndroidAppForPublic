package com.beautifulsetouchi.simpleothello.model.entity;

/**
 * オセロ盤面の64マス+外周の32マスのうち、場所を指し示す情報を格納するクラス
 */
public class BoardIndex {

    private int verticalIndex;
    private int horizontalIndex;

    public BoardIndex(int verticalIndex, int horizontalIndex) {

        if (verticalIndex <= -1 || verticalIndex >= 10){
            throw new IllegalArgumentException("指定するverticalIndexは0から9の範囲である必要があります。verticalIndex:"+verticalIndex);
        }
        if (horizontalIndex <= -1 || horizontalIndex >= 10){
            throw new IllegalArgumentException("指定するhorizontalIndexは0から9の範囲である必要があります。horizontalIndex:"+horizontalIndex);
        }

        this.verticalIndex = verticalIndex;
        this.horizontalIndex = horizontalIndex;
    }

    public void setVerticalIndex(int verticalIndex) {
        if (verticalIndex <= -1 || verticalIndex >= 10){
            throw new IllegalArgumentException("指定するverticalIndexは0から9の範囲である必要があります。verticalIndex:"+verticalIndex);
        }
        this.verticalIndex = verticalIndex;
    }

    public void setHorizontalIndex(int horizontalIndex) {
        if (horizontalIndex <= -1 || horizontalIndex >= 10){
            throw new IllegalArgumentException("指定するhorizontalIndexは0から9の範囲である必要があります。horizontalIndex:"+horizontalIndex);
        }
        this.horizontalIndex = horizontalIndex;
    }


    public int getVerticalIndex() {
        return verticalIndex;
    }

    public int getHorizontalIndex() {
        return horizontalIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardIndex that = (BoardIndex) o;
        return verticalIndex == that.verticalIndex &&
                horizontalIndex == that.horizontalIndex;
    }

}
