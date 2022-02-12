package com.beautifulsetouchi.simpleothello.model.entity;

/**
 * オセロ盤面の石の数を格納するクラス
 */
public class OthelloBoardStoneNum {

    private int blackStoneNum;
    private int whiteStoneNum;

    public void setBlackStoneNum(int blackStoneNum) {

        if (blackStoneNum <= -1 || 65 <= blackStoneNum) {
            throw new IllegalArgumentException("blackStoneNumは0以上64以下でなければなりません。");
        }
        this.blackStoneNum = blackStoneNum;
    }

    public void setWhiteStoneNum(int whiteStoneNum) {

        if (whiteStoneNum <= -1 || 65 <= whiteStoneNum) {
            throw new IllegalArgumentException("whiteStoneNumは0以上64以下でなければなりません。");
        }
        this.whiteStoneNum = whiteStoneNum;
    }

    public int getBlackStoneNum() {
        return blackStoneNum;
    }

    public int getWhiteStoneNum() {
        return whiteStoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OthelloBoardStoneNum that = (OthelloBoardStoneNum) o;
        return blackStoneNum == that.blackStoneNum &&
                whiteStoneNum == that.whiteStoneNum;
    }

}
