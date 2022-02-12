package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexAdder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;


public class OthelloBoardIndexAdderTest {

    OthelloBoardIndexAdder instance;

    @Before
    public void setUp() {
        instance = new OthelloBoardIndexAdder();
    }

    @Test
    public void addBoardIndex_givenValidArgumentOnlyVerticalDirection() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,2);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.NO_DIRECTION);
        int targetOffset = 2;

        BoardIndex actual = instance.addBoardIndex(boardIndex, boardDirection, targetOffset);
        BoardIndex expected = new BoardIndex(3,2);

        assertThat(actual, is(expected));
    }

    @Test
    public void addBoardIndex_givenValidArgumentOnlyHorizontalDirection() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,2);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.NO_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
        int targetOffset = 2;

        BoardIndex actual = instance.addBoardIndex(boardIndex, boardDirection, targetOffset);
        BoardIndex expected = new BoardIndex(1,4);

        assertThat(actual, is(expected));
    }


    @Test(expected=IllegalArgumentException.class)
    public void addBoardIndex_givenLowerBoundInvalidVerticalDirection() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,2);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.DECREMENTAL_DIRECTION,SingleBoardDirection.NO_DIRECTION);
        int targetOffset = 2;

        instance.addBoardIndex(boardIndex, boardDirection, targetOffset);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addBoardIndex_givenUpperBoundInvalidVerticalDirection() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(3,2);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.NO_DIRECTION);
        int targetOffset = 7;

        instance.addBoardIndex(boardIndex, boardDirection, targetOffset);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addBoardIndex_givenLowerBoundInvalidHorizontalDirection() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,2);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.NO_DIRECTION,SingleBoardDirection.DECREMENTAL_DIRECTION);
        int targetOffset = 3;

        instance.addBoardIndex(boardIndex, boardDirection, targetOffset);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addBoardIndex_givenUpperBoundInvalidHorizontalDirection() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,2);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.NO_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
        int targetOffset = 8;

        instance.addBoardIndex(boardIndex, boardDirection, targetOffset);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addBoardIndex_givenLowerBoundInvalidTargetOffset() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,2);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.NO_DIRECTION,SingleBoardDirection.DECREMENTAL_DIRECTION);
        int targetOffset = 0;

        instance.addBoardIndex(boardIndex, boardDirection, targetOffset);
    }

    @Test(expected=IllegalArgumentException.class)
    public void addBoardIndex_givenUpperBoundInvalidTargetOffset() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,2);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.NO_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
        int targetOffset = 9;

        instance.addBoardIndex(boardIndex, boardDirection, targetOffset);
    }
}