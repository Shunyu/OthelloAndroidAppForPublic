package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexVerticalRangeGeneratorChecker;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OthelloBoardIndexVerticalRangeGeneratorCheckerTest {

    OthelloBoardIndexVerticalRangeGeneratorChecker instance;

    @Before
    public void setUp() {
        instance = new OthelloBoardIndexVerticalRangeGeneratorChecker();
    }

    @Test
    public void isUpdatableBoardIndex_givenUpdatableBoardIndex() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,3);
        BoardDirection boardDirection = new BoardDirection(
                SingleBoardDirection.INCREMENTAL_DIRECTION,
                SingleBoardDirection.NO_DIRECTION
        );
        int offset = 6;

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
                SingleBoardDirection.DECREMENTAL_DIRECTION,
                SingleBoardDirection.NO_DIRECTION
        );
        int offset = 2;

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
                SingleBoardDirection.INCREMENTAL_DIRECTION,
                SingleBoardDirection.NO_DIRECTION
        );
        int offset = 4;

        boolean actual = instance.isUpdatableBoardIndex(
                boardIndex,
                boardDirection,
                offset
        );
        boolean expected = false;

        assertThat(actual, is(expected));

    }

}