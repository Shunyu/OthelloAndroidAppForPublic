package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardAllDirectionTurnOverExecutor;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardSpecificIndexTurnOverExecutor;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloTurnOverStonesRule;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OthelloTurnOverStonesRuleTest {

    OthelloBoardAllDirectionTurnOverExecutor othelloBoardAllDirectionTurnOverExecutorStub;
    OthelloBoardSpecificIndexTurnOverExecutor othelloBoardSpecificIndexTurnOverExecutorStub;
    OthelloTurnOverStonesRule instance;

    @Before
    public void setUp() {
        othelloBoardAllDirectionTurnOverExecutorStub = mock(OthelloBoardAllDirectionTurnOverExecutor.class);
        doNothing()
                .when(othelloBoardAllDirectionTurnOverExecutorStub)
                .executeTurnOverStones(any());

        othelloBoardSpecificIndexTurnOverExecutorStub = mock(OthelloBoardSpecificIndexTurnOverExecutor.class);
        doNothing()
                .when(othelloBoardSpecificIndexTurnOverExecutorStub)
                .executeTurnOverStone(any());

        instance = new OthelloTurnOverStonesRule(
                othelloBoardAllDirectionTurnOverExecutorStub,
                othelloBoardSpecificIndexTurnOverExecutorStub
        );
    }

    @Test
    public void turnOverStones_givenValidBoardIndex() {

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
        instance.turnOverStones(boardIndex);

        ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        verify(othelloBoardAllDirectionTurnOverExecutorStub, times(1))
                .executeTurnOverStones(boardIndexArgumentCaptor.capture());
        UpdatableBoardIndex actualBoardIndexOfExecuteTurnOverStones = boardIndexArgumentCaptor.getValue();
        assertEquals(boardIndex, actualBoardIndexOfExecuteTurnOverStones);

        verify(othelloBoardSpecificIndexTurnOverExecutorStub, times(1))
                .executeTurnOverStone(boardIndexArgumentCaptor.capture());
        UpdatableBoardIndex actualBoardIndexOfExecuteTurnOverStone = boardIndexArgumentCaptor.getValue();
        assertEquals(boardIndex, actualBoardIndexOfExecuteTurnOverStone);


    }

//    @Test(expected = IllegalArgumentException.class)
//    public void turnOverStones_givenLowerBoundInvalidVerticalIndex() {
//        BoardIndex boardIndex = new BoardIndex(0,2);
//        instance.turnOverStones(boardIndex);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void turnOverStones_givenUpperBoundInvalidVerticalIndex() {
//        BoardIndex boardIndex = new BoardIndex(9,2);
//        instance.turnOverStones(boardIndex);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void turnOverStones_givenLowerBoundInvalidHorizontalIndex() {
//        BoardIndex boardIndex = new BoardIndex(2,0);
//        instance.turnOverStones(boardIndex);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void turnOverStones_givenUpperBoundInvalidHorizontalIndex() {
//        BoardIndex boardIndex = new BoardIndex(2,9);
//        instance.turnOverStones(boardIndex);
//    }
}