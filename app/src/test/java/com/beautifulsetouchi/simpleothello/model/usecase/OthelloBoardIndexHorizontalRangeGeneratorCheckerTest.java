package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexHorizontalRangeGeneratorChecker;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OthelloBoardIndexHorizontalRangeGeneratorCheckerTest {

    OthelloBoardIndexHorizontalRangeGeneratorChecker instance;

    @Before
    public void setUp() {
        instance = new OthelloBoardIndexHorizontalRangeGeneratorChecker();
    }

    @Test
    public void isUpdatableBoardIndex_givenUpdatableBoardIndex() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,3);
        BoardDirection boardDirection = new BoardDirection(
                SingleBoardDirection.NO_DIRECTION,
                SingleBoardDirection.INCREMENTAL_DIRECTION
        );
        int offset = 5;

        boolean actual = instance.isUpdatableBoardIndex(
                boardIndex,
                boardDirection,
                offset
        );
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void isUpdatableBoardIndex_givenLowerBoundNonUpdatableBoardIndex() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2, 3);
        BoardDirection boardDirection = new BoardDirection(
                SingleBoardDirection.NO_DIRECTION,
                SingleBoardDirection.DECREMENTAL_DIRECTION
        );
        int offset = 3;

        boolean actual = instance.isUpdatableBoardIndex(
                boardIndex,
                boardDirection,
                offset
        );
        boolean expected = false;

        assertThat(actual, is(expected));
    }

    @Test
    public void isUpdatableBoardIndex_givenUpperBoundNonUpdatableBoardIndex() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(5,8);
        BoardDirection boardDirection = new BoardDirection(
                SingleBoardDirection.NO_DIRECTION,
                SingleBoardDirection.INCREMENTAL_DIRECTION
        );
        int offset = 1;

        boolean actual = instance.isUpdatableBoardIndex(
                boardIndex,
                boardDirection,
                offset
        );
        boolean expected = false;

        assertThat(actual, is(expected));

    }
}