package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloAnyDirectionTurnOverStoneExistenceChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardSpecificIndexLegalMoveChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardStonePutablePositionChecker;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class OthelloBoardSpecificIndexLegalMoveCheckerTest {

    public static class PutableFlagIsFalse {
        OthelloBoardStonePutablePositionChecker othelloBoardStonePutablePositionCheckerStub;
        OthelloAnyDirectionTurnOverStoneExistenceChecker othelloAnyDirectionTurnOverStoneExistenceCheckerStub;

        OthelloBoardSpecificIndexLegalMoveChecker instance;

        @Before
        public void setUp() {
            othelloBoardStonePutablePositionCheckerStub = mock(OthelloBoardStonePutablePositionChecker.class);
            when(othelloBoardStonePutablePositionCheckerStub.canPutBoardStone(any()))
                    .thenReturn(false);

            othelloAnyDirectionTurnOverStoneExistenceCheckerStub = mock(OthelloAnyDirectionTurnOverStoneExistenceChecker.class);
            when(othelloAnyDirectionTurnOverStoneExistenceCheckerStub.turnOverStoneExistsInAnyDirections(any()))
                    .thenReturn(true);

            instance = new OthelloBoardSpecificIndexLegalMoveChecker(
                    othelloBoardStonePutablePositionCheckerStub,
                    othelloAnyDirectionTurnOverStoneExistenceCheckerStub
            );

        }

        @Test
        public void isLegalMove_givenValidBoardIndex() {
            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

            boolean actualFlag = instance.isLegalMove(boardIndex);

            verify(othelloBoardStonePutablePositionCheckerStub, times(1))
                    .canPutBoardStone(boardIndexArgumentCaptor.capture());
            UpdatableBoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();
            assertEquals(boardIndex, actualBoardIndex);

            verify(othelloAnyDirectionTurnOverStoneExistenceCheckerStub, times(0))
                    .turnOverStoneExistsInAnyDirections(any());

            boolean expectedFlag = false;
            assertThat(actualFlag, is(expectedFlag));

        }

        @Test(expected = IllegalArgumentException.class)
        public void isLegalMove_givenLowerBoundInvalidVerticalIndex() {
            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(0,2);
            instance.isLegalMove(boardIndex);
        }

        @Test(expected = IllegalArgumentException.class)
        public void isLegalMove_givenUpperBoundInvalidVerticalIndex() {
            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(9,2);
            instance.isLegalMove(boardIndex);
        }

        @Test(expected = IllegalArgumentException.class)
        public void isLegalMove_givenLowerBoundInvalidHorizontalIndex() {
            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,0);
            instance.isLegalMove(boardIndex);
        }

        @Test(expected = IllegalArgumentException.class)
        public void isLegalMove_givenUpperBoundInvalidHorizontalIndex() {
            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,9);
            instance.isLegalMove(boardIndex);
        }
    }

    public static class PutableFlagIsTrueAndTurnOverStoneExistsFlagIsFalse {
        OthelloBoardStonePutablePositionChecker othelloBoardStonePutablePositionCheckerStub;
        OthelloAnyDirectionTurnOverStoneExistenceChecker othelloAnyDirectionTurnOverStoneExistenceCheckerStub;

        OthelloBoardSpecificIndexLegalMoveChecker instance;

        @Before
        public void setUp() {
            othelloBoardStonePutablePositionCheckerStub = mock(OthelloBoardStonePutablePositionChecker.class);
            when(othelloBoardStonePutablePositionCheckerStub.canPutBoardStone(any()))
                    .thenReturn(true);

            othelloAnyDirectionTurnOverStoneExistenceCheckerStub = mock(OthelloAnyDirectionTurnOverStoneExistenceChecker.class);
            when(othelloAnyDirectionTurnOverStoneExistenceCheckerStub.turnOverStoneExistsInAnyDirections(any()))
                    .thenReturn(false);

            instance = new OthelloBoardSpecificIndexLegalMoveChecker(
                    othelloBoardStonePutablePositionCheckerStub,
                    othelloAnyDirectionTurnOverStoneExistenceCheckerStub
            );

        }

        @Test
        public void isLegalMove_givenValidBoardIndex() {
            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2, 2);
            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

            boolean actualFlag = instance.isLegalMove(boardIndex);

            verify(othelloBoardStonePutablePositionCheckerStub, times(1))
                    .canPutBoardStone(
                            boardIndexArgumentCaptor.capture()
                    );
            UpdatableBoardIndex actualBoardIndexOfCanPutBoardStone = boardIndexArgumentCaptor.getValue();
            assertEquals(boardIndex, actualBoardIndexOfCanPutBoardStone);

            verify(othelloAnyDirectionTurnOverStoneExistenceCheckerStub, times(1))
                    .turnOverStoneExistsInAnyDirections(
                            boardIndexArgumentCaptor.capture()
                    );
            UpdatableBoardIndex actualBoardIndexOfTurnOverStoneExistsInAnyDirections = boardIndexArgumentCaptor.getValue();
            assertEquals(boardIndex, actualBoardIndexOfTurnOverStoneExistsInAnyDirections);

            boolean expectedFlag = false;
            assertThat(actualFlag, is(expectedFlag));

        }
    }

    public static class PutableFlagIsTrueAndTurnOverStoneExistsFlagIsTrue {
        OthelloBoardStonePutablePositionChecker othelloBoardStonePutablePositionCheckerStub;
        OthelloAnyDirectionTurnOverStoneExistenceChecker othelloAnyDirectionTurnOverStoneExistenceCheckerStub;

        OthelloBoardSpecificIndexLegalMoveChecker instance;

        @Before
        public void setUp() {
            othelloBoardStonePutablePositionCheckerStub = mock(OthelloBoardStonePutablePositionChecker.class);
            when(othelloBoardStonePutablePositionCheckerStub.canPutBoardStone(any()))
                    .thenReturn(true);

            othelloAnyDirectionTurnOverStoneExistenceCheckerStub = mock(OthelloAnyDirectionTurnOverStoneExistenceChecker.class);
            when(othelloAnyDirectionTurnOverStoneExistenceCheckerStub.turnOverStoneExistsInAnyDirections(any()))
                    .thenReturn(true);

            instance = new OthelloBoardSpecificIndexLegalMoveChecker(
                    othelloBoardStonePutablePositionCheckerStub,
                    othelloAnyDirectionTurnOverStoneExistenceCheckerStub
            );

        }

        @Test
        public void isLegalMove_givenValidBoardIndex() {
            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2, 2);
            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

            boolean actualFlag = instance.isLegalMove(boardIndex);

            verify(othelloBoardStonePutablePositionCheckerStub, times(1))
                    .canPutBoardStone(
                            boardIndexArgumentCaptor.capture()
                    );
            UpdatableBoardIndex actualBoardIndexOfCanPutBoardStone = boardIndexArgumentCaptor.getValue();
            assertEquals(boardIndex, actualBoardIndexOfCanPutBoardStone);

            verify(othelloAnyDirectionTurnOverStoneExistenceCheckerStub, times(1))
                    .turnOverStoneExistsInAnyDirections(
                            boardIndexArgumentCaptor.capture()
                    );
            UpdatableBoardIndex actualBoardIndexOfTurnOverStoneExistsInAnyDirections = boardIndexArgumentCaptor.getValue();
            assertEquals(boardIndex, actualBoardIndexOfTurnOverStoneExistsInAnyDirections);

            boolean expectedFlag = true;
            assertThat(actualFlag, is(expectedFlag));

        }
    }

}