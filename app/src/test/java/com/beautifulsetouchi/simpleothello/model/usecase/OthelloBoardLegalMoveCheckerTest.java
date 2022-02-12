package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloAllUpdatableBoardIndexListGenerator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardLegalMoveChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardSpecificIndexLegalMoveChecker;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
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

@RunWith(Enclosed.class)
public class OthelloBoardLegalMoveCheckerTest {

    public static class AllLegalMoveFlagsAreFalse {

        OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGeneratorStub;
        OthelloBoardSpecificIndexLegalMoveChecker othelloBoardSpecificIndexLegalMoveCheckerStub;

        OthelloBoardLegalMoveChecker instance;

        private List<UpdatableBoardIndex> boardIndexList;

        @Before
        public void setUp() {
            boardIndexList = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloAllUpdatableBoardIndexListGeneratorStub = mock(OthelloAllUpdatableBoardIndexListGenerator.class);
            when(othelloAllUpdatableBoardIndexListGeneratorStub.getAllUpdatableBoardIndexList())
                    .thenReturn(boardIndexList);


            othelloBoardSpecificIndexLegalMoveCheckerStub = mock(OthelloBoardSpecificIndexLegalMoveChecker.class);
            when(othelloBoardSpecificIndexLegalMoveCheckerStub.isLegalMove(any()))
                    .thenReturn(false);

            instance = new OthelloBoardLegalMoveChecker(
                    othelloAllUpdatableBoardIndexListGeneratorStub,
                    othelloBoardSpecificIndexLegalMoveCheckerStub
            );
        }

        @Test
        public void legalMoveExists() {
            boolean actualFlag = instance.legalMoveExists();

            verify(othelloAllUpdatableBoardIndexListGeneratorStub, times(1))
                    .getAllUpdatableBoardIndexList();

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            verify(othelloBoardSpecificIndexLegalMoveCheckerStub, times(boardIndexList.size()))
                    .isLegalMove(boardIndexArgumentCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
            assertEquals(boardIndexList, actualBoardIndexList);

            boolean expectedFlag = false;
            assertThat(actualFlag, is(expectedFlag));

        }

    }

    public static class LegalMoveFlagIsTrueInSecondTime {

        OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGeneratorStub;
        OthelloBoardSpecificIndexLegalMoveChecker othelloBoardSpecificIndexLegalMoveCheckerStub;

        OthelloBoardLegalMoveChecker instance;

        private List<UpdatableBoardIndex> boardIndexList;

        @Before
        public void setUp() {
            boardIndexList = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloAllUpdatableBoardIndexListGeneratorStub = mock(OthelloAllUpdatableBoardIndexListGenerator.class);
            when(othelloAllUpdatableBoardIndexListGeneratorStub.getAllUpdatableBoardIndexList())
                    .thenReturn(boardIndexList);


            othelloBoardSpecificIndexLegalMoveCheckerStub = mock(OthelloBoardSpecificIndexLegalMoveChecker.class);
            when(othelloBoardSpecificIndexLegalMoveCheckerStub.isLegalMove(any()))
                    .thenReturn(false);
            when(othelloBoardSpecificIndexLegalMoveCheckerStub.isLegalMove(
                    eq(new UpdatableBoardIndex(2,3))))
                    .thenReturn(true);

            instance = new OthelloBoardLegalMoveChecker(
                    othelloAllUpdatableBoardIndexListGeneratorStub,
                    othelloBoardSpecificIndexLegalMoveCheckerStub
            );
        }

        @Test
        public void legalMoveExists() {
            boolean actualFlag = instance.legalMoveExists();

            verify(othelloAllUpdatableBoardIndexListGeneratorStub, times(1))
                    .getAllUpdatableBoardIndexList();

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            verify(othelloBoardSpecificIndexLegalMoveCheckerStub, times(2))
                    .isLegalMove(boardIndexArgumentCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
            assertEquals(boardIndexList, actualBoardIndexList);

            boolean expectedFlag = true;
            assertThat(actualFlag, is(expectedFlag));

        }

    }

    public static class LegalMoveFlagIsTrueInFirstTime {

        OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGeneratorStub;
        OthelloBoardSpecificIndexLegalMoveChecker othelloBoardSpecificIndexLegalMoveCheckerStub;

        OthelloBoardLegalMoveChecker instance;

        private List<UpdatableBoardIndex> boardIndexList;

        @Before
        public void setUp() {
            boardIndexList = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloAllUpdatableBoardIndexListGeneratorStub = mock(OthelloAllUpdatableBoardIndexListGenerator.class);
            when(othelloAllUpdatableBoardIndexListGeneratorStub.getAllUpdatableBoardIndexList())
                    .thenReturn(boardIndexList);


            othelloBoardSpecificIndexLegalMoveCheckerStub = mock(OthelloBoardSpecificIndexLegalMoveChecker.class);
            when(othelloBoardSpecificIndexLegalMoveCheckerStub.isLegalMove(any()))
                    .thenReturn(false);
            when(othelloBoardSpecificIndexLegalMoveCheckerStub.isLegalMove(
                    eq(new UpdatableBoardIndex(2,2))))
                    .thenReturn(true);

            instance = new OthelloBoardLegalMoveChecker(
                    othelloAllUpdatableBoardIndexListGeneratorStub,
                    othelloBoardSpecificIndexLegalMoveCheckerStub
            );
        }

        @Test
        public void legalMoveExists() {
            boolean actualFlag = instance.legalMoveExists();

            verify(othelloAllUpdatableBoardIndexListGeneratorStub, times(1))
                    .getAllUpdatableBoardIndexList();

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            verify(othelloBoardSpecificIndexLegalMoveCheckerStub, times(1))
                    .isLegalMove(boardIndexArgumentCaptor.capture());
            BoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();
            assertEquals(boardIndexList.get(0), actualBoardIndex);

            boolean expectedFlag = true;
            assertThat(actualFlag, is(expectedFlag));

        }
    }
}