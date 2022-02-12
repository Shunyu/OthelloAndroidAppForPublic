package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;

/**
 * オセロ盤面上で、探索する方向を格納するクラス
 * 探索する方向として、ありうる方向としては8方向
 */
public class BoardDirection {

    private SingleBoardDirection verticalDirection;
    private SingleBoardDirection horizontalDirection;

    public BoardDirection(
            SingleBoardDirection verticalDirection,
            SingleBoardDirection horizontalDirection
    ){

        if(verticalDirection== SingleBoardDirection.NO_DIRECTION && horizontalDirection== SingleBoardDirection.NO_DIRECTION){
            throw new IllegalArgumentException("vericalDirection=0かつhorizontalDirection=0は指定できません。");
        }

        this.verticalDirection = verticalDirection;
        this.horizontalDirection = horizontalDirection;
    }

    public SingleBoardDirection getVerticalDirection() {
        return verticalDirection;
    }
    
    public SingleBoardDirection getHorizontalDirection() {
        return horizontalDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDirection that = (BoardDirection) o;
        return verticalDirection == that.verticalDirection &&
                horizontalDirection == that.horizontalDirection;
    }

}
