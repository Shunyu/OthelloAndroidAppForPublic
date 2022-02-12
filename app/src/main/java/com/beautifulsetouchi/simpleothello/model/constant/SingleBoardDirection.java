package com.beautifulsetouchi.simpleothello.model.constant;

/**
 * オセロの盤面上の方向を表す列挙型
 * 左or上, 方向なし, 右or下
 */
public enum SingleBoardDirection {

    DECREMENTAL_DIRECTION(-1),
    NO_DIRECTION(0),
    INCREMENTAL_DIRECTION(1);

    private int singleDirection;

    SingleBoardDirection(int singleDirection){
        this.singleDirection = singleDirection;
    }

    public int getSingleDirection() {
        return singleDirection;
    }

}
