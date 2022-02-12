package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloAllBoardDirectionListGenerator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloAnyDirectionTurnOverStoneExistenceChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloSpecificDirectionTurnOverStoneExistenceChecker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloAnyDirectionTurnOverStoneExistenceCheckerTest {

    OthelloAllBoardDirectionListGenerator othelloAllBoardDirectionListGeneratorStub;
    OthelloSpecificDirectionTurnOverStoneExistenceChecker othelloSpecificDirectionTurnOverStoneExistenceCheckerStub;
    OthelloAnyDirectionTurnOverStoneExistenceChecker instance;

    private List<BoardDirection> boardDirectionList;

    @Before
    public void setUp() {

        boardDirectionList = new ArrayList<>(
                Arrays.asList(
                        new BoardDirection(SingleBoardDirection.NO_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION),
                        new BoardDirection(SingleBoardDirection.DECREMENTAL_DIRECTION, SingleBoardDirection.DECREMENTAL_DIRECTION)
                )
        );
        othelloAllBoardDirectionListGeneratorStub = mock(OthelloAllBoardDirectionListGenerator.class);
        when(othelloAllBoardDirectionListGeneratorStub.getAllBoardDirectionList())
                .thenReturn(boardDirectionList);

        othelloSpecificDirectionTurnOverStoneExistenceCheckerStub = mock(OthelloSpecificDirectionTurnOverStoneExistenceChecker.class);
        when(othelloSpecificDirectionTurnOverStoneExistenceCheckerStub
                .turnOverStoneExistsInSpecificDirection(any(), any()))
                .thenReturn(false);

        when(othelloSpecificDirectionTurnOverStoneExistenceCheckerStub
                .turnOverStoneExistsInSpecificDirection(
                        eq(new UpdatableBoardIndex(1,1)),
                        eq(new BoardDirection(SingleBoardDirection.NO_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION))
                ))
                .thenReturn(true);

        when(othelloSpecificDirectionTurnOverStoneExistenceCheckerStub
                .turnOverStoneExistsInSpecificDirection(
                        eq(new UpdatableBoardIndex(2,2)),
                        eq(new BoardDirection(SingleBoardDirection.DECREMENTAL_DIRECTION,SingleBoardDirection.DECREMENTAL_DIRECTION))
                ))
                .thenReturn(true);

        instance = new OthelloAnyDirectionTurnOverStoneExistenceChecker(
                othelloAllBoardDirectionListGeneratorStub,
                othelloSpecificDirectionTurnOverStoneExistenceCheckerStub
        );

    }

    @Test
    public void turnOverStoneExistsInAnyDirections_givenAllFalseBoardIndex_returnFalse() {

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(3,3);

        ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);

        boolean actualFlag = instance.turnOverStoneExistsInAnyDirections(boardIndex);

        verify(othelloAllBoardDirectionListGeneratorStub, times(1))
                .getAllBoardDirectionList();

        verify(othelloSpecificDirectionTurnOverStoneExistenceCheckerStub, times(boardDirectionList.size()))
                .turnOverStoneExistsInSpecificDirection(
                        boardIndexArgumentCaptor.capture(),
                        boardDirectionArgumentCaptor.capture()
                );
        List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
        List<BoardDirection> actualBoardDirectionList = boardDirectionArgumentCaptor.getAllValues();
        for(UpdatableBoardIndex actualBoardIndex : actualBoardIndexList) {
            assertEquals(boardIndex, actualBoardIndex);
        }
        assertEquals(boardDirectionList, actualBoardDirectionList);

        boolean expectedFlag = false;
        assertThat(actualFlag, is(expectedFlag));

    }

    @Test
    public void turnOverStoneExistsInAnyDirections_givenTrueInSecondTimeBoardIndex_returnTrue() {

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);

        ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);

        boolean actualFlag = instance.turnOverStoneExistsInAnyDirections(boardIndex);

        verify(othelloAllBoardDirectionListGeneratorStub, times(1))
                .getAllBoardDirectionList();

        verify(othelloSpecificDirectionTurnOverStoneExistenceCheckerStub, times(2))
                .turnOverStoneExistsInSpecificDirection(
                        boardIndexArgumentCaptor.capture(),
                        boardDirectionArgumentCaptor.capture()
                );
        List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
        List<BoardDirection> actualBoardDirectionList = boardDirectionArgumentCaptor.getAllValues();
        for(UpdatableBoardIndex actualBoardIndex : actualBoardIndexList) {
            assertEquals(boardIndex, actualBoardIndex);
        }
        assertEquals(boardDirectionList, actualBoardDirectionList);

        boolean expectedFlag = true;
        assertThat(actualFlag, is(expectedFlag));

    }

    @Test
    public void turnOverStoneExistsInAnyDirections_givenTrueInFirstTimeBoardIndex_returnTrue() {

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,1);

        ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);

        boolean actualFlag = instance.turnOverStoneExistsInAnyDirections(boardIndex);

        verify(othelloAllBoardDirectionListGeneratorStub, times(1))
                .getAllBoardDirectionList();

        verify(othelloSpecificDirectionTurnOverStoneExistenceCheckerStub, times(1))
                .turnOverStoneExistsInSpecificDirection(
                        boardIndexArgumentCaptor.capture(),
                        boardDirectionArgumentCaptor.capture()
                );
        UpdatableBoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();
        BoardDirection actualBoardDirection = boardDirectionArgumentCaptor.getValue();
        assertEquals(boardIndex, actualBoardIndex);
        assertEquals(boardDirectionList.get(0), actualBoardDirection);

        boolean expectedFlag = true;
        assertThat(actualFlag, is(expectedFlag));

    }
}