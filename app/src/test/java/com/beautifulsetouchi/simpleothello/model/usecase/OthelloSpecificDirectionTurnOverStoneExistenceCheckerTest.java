package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloSpecificDirectionTurnOverStoneExistenceChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloTurnOverStoneCounter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloSpecificDirectionTurnOverStoneExistenceCheckerTest {

    OthelloTurnOverStoneCounter othelloTurnOverStoneCounterStub;
    OthelloSpecificDirectionTurnOverStoneExistenceChecker instance;

    @Before
    public void setUp() {
        othelloTurnOverStoneCounterStub = mock(OthelloTurnOverStoneCounter.class);
        when(othelloTurnOverStoneCounterStub.countTurnOverStones(
                any(UpdatableBoardIndex.class),
                any(BoardDirection.class)))
                .thenReturn(0);
        when(othelloTurnOverStoneCounterStub.countTurnOverStones(
                eq(new UpdatableBoardIndex(2,2)),
                eq(new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION))))
                .thenReturn(1);


        instance = new OthelloSpecificDirectionTurnOverStoneExistenceChecker(
                othelloTurnOverStoneCounterStub
        );
    }

    @Test
    public void turnOverStoneExistsInSpecificDirection_givenSpecificArguments_returnTrue() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);

        ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);

        boolean actualFlag = instance.turnOverStoneExistsInSpecificDirection(boardIndex, boardDirection);

        verify(othelloTurnOverStoneCounterStub, times(1))
                .countTurnOverStones(
                        boardIndexArgumentCaptor.capture(),
                        boardDirectionArgumentCaptor.capture()
                );
        UpdatableBoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();
        assertEquals(boardIndex, actualBoardIndex);
        BoardDirection actualBoardDirection = boardDirectionArgumentCaptor.getValue();
        assertEquals(boardDirection, actualBoardDirection);

        boolean expectedFlag = true;
        assertThat(actualFlag, is(expectedFlag));

    }

    @Test
    public void turnOverStoneExistsInSpecificDirection_givenSpecificArguments_returnFalse() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,3);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);

        ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);

        boolean actualFlag = instance.turnOverStoneExistsInSpecificDirection(boardIndex, boardDirection);

        verify(othelloTurnOverStoneCounterStub, times(1))
                .countTurnOverStones(
                        boardIndexArgumentCaptor.capture(),
                        boardDirectionArgumentCaptor.capture()
                );
        UpdatableBoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();
        assertEquals(boardIndex, actualBoardIndex);
        BoardDirection actualBoardDirection = boardDirectionArgumentCaptor.getValue();
        assertEquals(boardDirection, actualBoardDirection);

        boolean expectedFlag = false;
        assertThat(actualFlag, is(expectedFlag));

    }
}