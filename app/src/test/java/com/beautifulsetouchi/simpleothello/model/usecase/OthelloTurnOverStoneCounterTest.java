package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloOpponentPlayerStoneCounter;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloPlayerChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloTurnOverStoneCounter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloTurnOverStoneCounterTest {

    OthelloOpponentPlayerStoneCounter othelloOpponentPlayerStoneCounterStub;
    OthelloPlayerChecker othelloPlayerCheckerStub;
    OthelloTurnOverStoneCounter instance;

    @Before
    public void setUp() {
        othelloOpponentPlayerStoneCounterStub = mock(OthelloOpponentPlayerStoneCounter.class);
        when(othelloOpponentPlayerStoneCounterStub.countOpponentPlayerStones(any(),any()))
                .thenReturn(3);
    }

    @Test
    public void countTurnOverStones_givenMyselfFlagIsTrue() {
        othelloPlayerCheckerStub = mock(OthelloPlayerChecker.class);
        when(othelloPlayerCheckerStub.isMyself(any(), any(), anyInt()))
                .thenReturn(true);

        instance = new OthelloTurnOverStoneCounter(
                othelloOpponentPlayerStoneCounterStub,
                othelloPlayerCheckerStub
        );

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,1);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
        int actual = instance.countTurnOverStones(boardIndex, boardDirection);
        int expected = 3;
        assertThat(actual, is(expected));

    }

    @Test
    public void countTurnOverStones_givenMyselfFlagIsFalse() {
        othelloPlayerCheckerStub = mock(OthelloPlayerChecker.class);
        when(othelloPlayerCheckerStub.isMyself(any(), any(), anyInt()))
                .thenReturn(false);

        instance = new OthelloTurnOverStoneCounter(
                othelloOpponentPlayerStoneCounterStub,
                othelloPlayerCheckerStub
        );

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,1);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
        int actual = instance.countTurnOverStones(boardIndex, boardDirection);
        int expected = 0;
        assertThat(actual, is(expected));

    }

    @Test
    public void countTurnOverStones_validateTargetOffsetStone() {
        othelloPlayerCheckerStub = mock(OthelloPlayerChecker.class);
        when(othelloPlayerCheckerStub.isMyself(any(), any(), anyInt()))
                .thenReturn(true);

        instance = new OthelloTurnOverStoneCounter(
                othelloOpponentPlayerStoneCounterStub,
                othelloPlayerCheckerStub
        );

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,1);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
        instance.countTurnOverStones(boardIndex, boardDirection);

        ArgumentCaptor<Integer> targetOffsetCapture = ArgumentCaptor.forClass(Integer.class);
        verify(othelloPlayerCheckerStub, times(1))
                .isMyself(any(), any(), targetOffsetCapture.capture());
        int actualTargetOffset = targetOffsetCapture.getValue();
        int expectedTargetOffset = 4;
        assertThat(actualTargetOffset, is(expectedTargetOffset));
    }
}