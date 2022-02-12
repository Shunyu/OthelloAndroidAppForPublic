package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardLegalMoveChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardSpecificIndexLegalMoveChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloRule;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloTurnOverStonesRule;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloRuleTest {

    OthelloTurnOverStonesRule othelloTurnOverStonesRuleStub;
    OthelloBoardSpecificIndexLegalMoveChecker othelloBoardSpecificIndexLegalMoveCheckerStub;
    OthelloBoardLegalMoveChecker othelloBoardLegalMoveCheckerStub;

    OthelloRule instance;

    @Before
    public void setUp() {
        othelloTurnOverStonesRuleStub = mock(OthelloTurnOverStonesRule.class);
        doNothing()
                .when(othelloTurnOverStonesRuleStub)
                .turnOverStones(any());

        othelloBoardSpecificIndexLegalMoveCheckerStub = mock(OthelloBoardSpecificIndexLegalMoveChecker.class);
        when(othelloBoardSpecificIndexLegalMoveCheckerStub.isLegalMove(any()))
                .thenReturn(false);

        othelloBoardLegalMoveCheckerStub = mock(OthelloBoardLegalMoveChecker.class);
        when(othelloBoardLegalMoveCheckerStub.legalMoveExists())
                .thenReturn(false);

        instance = new OthelloRule(
                othelloTurnOverStonesRuleStub,
                othelloBoardSpecificIndexLegalMoveCheckerStub,
                othelloBoardLegalMoveCheckerStub
        );

    }

    @Test
    public void turnOverStones_givenSpecificBoardIndex() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
        ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

        instance.turnOverStones(boardIndex);

        verify(othelloTurnOverStonesRuleStub, times(1))
                .turnOverStones(boardIndexArgumentCaptor.capture());
        BoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();

        assertEquals(boardIndex, actualBoardIndex);
    }

    @Test
    public void isLegalMove_givenSpecificBoardIndex() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
        ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

        boolean actualFlag = instance.isLegalMove(boardIndex);

        verify(othelloBoardSpecificIndexLegalMoveCheckerStub, times(1))
                .isLegalMove(boardIndexArgumentCaptor.capture());
        BoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();

        assertEquals(boardIndex, actualBoardIndex);

        boolean expectedFlag = false;
        assertThat(actualFlag, is(expectedFlag));
    }

    @Test
    public void legalMoveExists() {
        boolean actualFlag = instance.legalMoveExists();

        verify(othelloBoardLegalMoveCheckerStub, times(1))
                .legalMoveExists();

        boolean expectedFlag = false;
        assertThat(actualFlag, is(expectedFlag));
    }

}