package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexRangeGenerator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardSpecificDirectionTurnOverExecutor;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardSpecificIndexListTurnOverExecutor;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloTurnOverStoneCounter;

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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class OthelloBoardSpecificDirectionTurnOverExecutorTest {

    public static class TurnOverStoneIsZero {

        OthelloTurnOverStoneCounter othelloTurnOverStoneCounterStub;
        OthelloBoardIndexRangeGenerator othelloBoardIndexRangeGeneratorStub;
        OthelloBoardSpecificIndexListTurnOverExecutor othelloBoardSpecificIndexListTurnOverExecutorStub;
        OthelloBoardSpecificDirectionTurnOverExecutor instance;

        private int othelloTurnOverStoneNumExpected;
        private List<UpdatableBoardIndex> targetBoardIndexRangeListExpected;

        @Before
        public void setUp() {
            othelloTurnOverStoneNumExpected = 0;
            othelloTurnOverStoneCounterStub = mock(OthelloTurnOverStoneCounter.class);
            when(othelloTurnOverStoneCounterStub.countTurnOverStones(any(), any()))
                    .thenReturn(othelloTurnOverStoneNumExpected);


            targetBoardIndexRangeListExpected = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloBoardIndexRangeGeneratorStub = mock(OthelloBoardIndexRangeGenerator.class);
            when(othelloBoardIndexRangeGeneratorStub
                    .generateBoardIndexRangeList(any(), any(), anyInt(), anyInt()))
                    .thenReturn(targetBoardIndexRangeListExpected);


            othelloBoardSpecificIndexListTurnOverExecutorStub = mock(OthelloBoardSpecificIndexListTurnOverExecutor.class);
            doNothing()
                    .when(othelloBoardSpecificIndexListTurnOverExecutorStub)
                    .executeTurnOverStones(any());

            instance = new OthelloBoardSpecificDirectionTurnOverExecutor(
                    othelloTurnOverStoneCounterStub,
                    othelloBoardIndexRangeGeneratorStub,
                    othelloBoardSpecificIndexListTurnOverExecutorStub
            );

        }

        @Test
        public void executeTurnOverStones_givenSpecificBoardIndexAndBoardDirection() {

            UpdatableBoardIndex expectedBoardIndex = new UpdatableBoardIndex(1,1);
            BoardDirection expectedBoardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);

            instance.executeTurnOverStones(expectedBoardIndex, expectedBoardDirection);

            verify(othelloTurnOverStoneCounterStub, times(1))
                    .countTurnOverStones(
                            boardIndexArgumentCaptor.capture(),
                            boardDirectionArgumentCaptor.capture()
                    );

            BoardIndex actualBoardIndexInCountTurnOverStones = boardIndexArgumentCaptor.getValue();
            BoardDirection actualBoardDirectionInCountTurnOverStones = boardDirectionArgumentCaptor.getValue();

            assertEquals(expectedBoardIndex, actualBoardIndexInCountTurnOverStones);
            assertEquals(expectedBoardDirection, actualBoardDirectionInCountTurnOverStones);

            verify(othelloBoardIndexRangeGeneratorStub, times(0))
                    .generateBoardIndexRangeList(any(), any(), anyInt(), anyInt());

            verify(othelloBoardSpecificIndexListTurnOverExecutorStub, times(0))
                    .executeTurnOverStones(any());

        }
    }

    public static class TurnOverStoneIsOne {

        OthelloTurnOverStoneCounter othelloTurnOverStoneCounterStub;
        OthelloBoardIndexRangeGenerator othelloBoardIndexRangeGeneratorStub;
        OthelloBoardSpecificIndexListTurnOverExecutor othelloBoardSpecificIndexListTurnOverExecutorStub;
        OthelloBoardSpecificDirectionTurnOverExecutor instance;

        private int othelloTurnOverStoneNumExpected;
        private List<UpdatableBoardIndex> targetBoardIndexRangeListExpected;

        @Before
        public void setUp() {
            othelloTurnOverStoneNumExpected = 1;
            othelloTurnOverStoneCounterStub = mock(OthelloTurnOverStoneCounter.class);
            when(othelloTurnOverStoneCounterStub.countTurnOverStones(any(), any()))
                    .thenReturn(othelloTurnOverStoneNumExpected);


            targetBoardIndexRangeListExpected = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloBoardIndexRangeGeneratorStub = mock(OthelloBoardIndexRangeGenerator.class);
            when(othelloBoardIndexRangeGeneratorStub
                    .generateBoardIndexRangeList(any(), any(), anyInt(), anyInt()))
                    .thenReturn(targetBoardIndexRangeListExpected);


            othelloBoardSpecificIndexListTurnOverExecutorStub = mock(OthelloBoardSpecificIndexListTurnOverExecutor.class);
            doNothing()
                    .when(othelloBoardSpecificIndexListTurnOverExecutorStub)
                    .executeTurnOverStones(any());

            instance = new OthelloBoardSpecificDirectionTurnOverExecutor(
                    othelloTurnOverStoneCounterStub,
                    othelloBoardIndexRangeGeneratorStub,
                    othelloBoardSpecificIndexListTurnOverExecutorStub
            );

        }

        @Test
        public void executeTurnOverStones_givenSpecificBoardIndexAndBoardDirection() {

            UpdatableBoardIndex expectedBoardIndex = new UpdatableBoardIndex(1,1);
            BoardDirection expectedBoardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);
            ArgumentCaptor<Integer> startOffsetArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Integer> endOffsetArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<List<UpdatableBoardIndex>> boardIndexListCaptor = ArgumentCaptor.forClass(List.class);

            instance.executeTurnOverStones(expectedBoardIndex, expectedBoardDirection);

            verify(othelloTurnOverStoneCounterStub, times(1))
                    .countTurnOverStones(
                            boardIndexArgumentCaptor.capture(),
                            boardDirectionArgumentCaptor.capture()
                    );

            UpdatableBoardIndex actualBoardIndexInCountTurnOverStones = boardIndexArgumentCaptor.getValue();
            BoardDirection actualBoardDirectionInCountTurnOverStones = boardDirectionArgumentCaptor.getValue();

            assertEquals(expectedBoardIndex, actualBoardIndexInCountTurnOverStones);
            assertEquals(expectedBoardDirection, actualBoardDirectionInCountTurnOverStones);

            verify(othelloBoardIndexRangeGeneratorStub, times(1))
                    .generateBoardIndexRangeList(
                            boardIndexArgumentCaptor.capture(),
                            boardDirectionArgumentCaptor.capture(),
                            startOffsetArgumentCaptor.capture(),
                            endOffsetArgumentCaptor.capture()
                    );

            UpdatableBoardIndex actualBoardIndexInGenerateBoardIndexRangeList = boardIndexArgumentCaptor.getValue();
            BoardDirection actualBoardDirectionInGenerateBoardIndexRangeList = boardDirectionArgumentCaptor.getValue();
            int actualStartOffset = startOffsetArgumentCaptor.getValue();
            int actualEndOffset = endOffsetArgumentCaptor.getValue();
            int expectedStartOffset = 1;

            assertEquals(expectedBoardIndex, actualBoardIndexInGenerateBoardIndexRangeList);
            assertEquals(expectedBoardDirection, actualBoardDirectionInGenerateBoardIndexRangeList);
            assertThat(actualStartOffset, is(expectedStartOffset));
            assertThat(actualEndOffset, is(othelloTurnOverStoneNumExpected));

            verify(othelloBoardSpecificIndexListTurnOverExecutorStub, times(1))
                    .executeTurnOverStones(boardIndexListCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexListCaptor.getValue();
            assertEquals(targetBoardIndexRangeListExpected, actualBoardIndexList);

        }
    }


    public static class TurnOverStoneIsTwo {

        OthelloTurnOverStoneCounter othelloTurnOverStoneCounterStub;
        OthelloBoardIndexRangeGenerator othelloBoardIndexRangeGeneratorStub;
        OthelloBoardSpecificIndexListTurnOverExecutor othelloBoardSpecificIndexListTurnOverExecutorStub;
        OthelloBoardSpecificDirectionTurnOverExecutor instance;

        private int othelloTurnOverStoneNumExpected;
        private List<UpdatableBoardIndex> targetBoardIndexRangeListExpected;

        @Before
        public void setUp() {
            othelloTurnOverStoneNumExpected = 2;
            othelloTurnOverStoneCounterStub = mock(OthelloTurnOverStoneCounter.class);
            when(othelloTurnOverStoneCounterStub.countTurnOverStones(any(), any()))
                    .thenReturn(othelloTurnOverStoneNumExpected);


            targetBoardIndexRangeListExpected = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloBoardIndexRangeGeneratorStub = mock(OthelloBoardIndexRangeGenerator.class);
            when(othelloBoardIndexRangeGeneratorStub
                    .generateBoardIndexRangeList(any(), any(), anyInt(), anyInt()))
                    .thenReturn(targetBoardIndexRangeListExpected);


            othelloBoardSpecificIndexListTurnOverExecutorStub = mock(OthelloBoardSpecificIndexListTurnOverExecutor.class);
            doNothing()
                    .when(othelloBoardSpecificIndexListTurnOverExecutorStub)
                    .executeTurnOverStones(any());

            instance = new OthelloBoardSpecificDirectionTurnOverExecutor(
                    othelloTurnOverStoneCounterStub,
                    othelloBoardIndexRangeGeneratorStub,
                    othelloBoardSpecificIndexListTurnOverExecutorStub
            );

        }

        @Test
        public void executeTurnOverStones_givenSpecificBoardIndexAndBoardDirection() {

            UpdatableBoardIndex expectedBoardIndex = new UpdatableBoardIndex(1,1);
            BoardDirection expectedBoardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);
            ArgumentCaptor<Integer> startOffsetArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Integer> endOffsetArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<List<UpdatableBoardIndex>> boardIndexListCaptor = ArgumentCaptor.forClass(List.class);

            instance.executeTurnOverStones(expectedBoardIndex, expectedBoardDirection);

            verify(othelloTurnOverStoneCounterStub, times(1))
                    .countTurnOverStones(
                            boardIndexArgumentCaptor.capture(),
                            boardDirectionArgumentCaptor.capture()
                    );

            UpdatableBoardIndex actualBoardIndexInCountTurnOverStones = boardIndexArgumentCaptor.getValue();
            BoardDirection actualBoardDirectionInCountTurnOverStones = boardDirectionArgumentCaptor.getValue();

            assertEquals(expectedBoardIndex, actualBoardIndexInCountTurnOverStones);
            assertEquals(expectedBoardDirection, actualBoardDirectionInCountTurnOverStones);

            verify(othelloBoardIndexRangeGeneratorStub, times(1))
                    .generateBoardIndexRangeList(
                            boardIndexArgumentCaptor.capture(),
                            boardDirectionArgumentCaptor.capture(),
                            startOffsetArgumentCaptor.capture(),
                            endOffsetArgumentCaptor.capture()
                    );

            UpdatableBoardIndex actualBoardIndexInGenerateBoardIndexRangeList = boardIndexArgumentCaptor.getValue();
            BoardDirection actualBoardDirectionInGenerateBoardIndexRangeList = boardDirectionArgumentCaptor.getValue();
            int actualStartOffset = startOffsetArgumentCaptor.getValue();
            int actualEndOffset = endOffsetArgumentCaptor.getValue();
            int expectedStartOffset = 1;

            assertEquals(expectedBoardIndex, actualBoardIndexInGenerateBoardIndexRangeList);
            assertEquals(expectedBoardDirection, actualBoardDirectionInGenerateBoardIndexRangeList);
            assertThat(actualStartOffset, is(expectedStartOffset));
            assertThat(actualEndOffset, is(othelloTurnOverStoneNumExpected));

            verify(othelloBoardSpecificIndexListTurnOverExecutorStub, times(1))
                    .executeTurnOverStones(boardIndexListCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexListCaptor.getValue();
            assertEquals(targetBoardIndexRangeListExpected, actualBoardIndexList);

        }
    }

    public static class TurnOverStoneIsSix {

        OthelloTurnOverStoneCounter othelloTurnOverStoneCounterStub;
        OthelloBoardIndexRangeGenerator othelloBoardIndexRangeGeneratorStub;
        OthelloBoardSpecificIndexListTurnOverExecutor othelloBoardSpecificIndexListTurnOverExecutorStub;
        OthelloBoardSpecificDirectionTurnOverExecutor instance;

        private int othelloTurnOverStoneNumExpected;
        private List<UpdatableBoardIndex> targetBoardIndexRangeListExpected;

        @Before
        public void setUp() {
            othelloTurnOverStoneNumExpected = 6;
            othelloTurnOverStoneCounterStub = mock(OthelloTurnOverStoneCounter.class);
            when(othelloTurnOverStoneCounterStub.countTurnOverStones(any(), any()))
                    .thenReturn(othelloTurnOverStoneNumExpected);


            targetBoardIndexRangeListExpected = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloBoardIndexRangeGeneratorStub = mock(OthelloBoardIndexRangeGenerator.class);
            when(othelloBoardIndexRangeGeneratorStub
                    .generateBoardIndexRangeList(any(), any(), anyInt(), anyInt()))
                    .thenReturn(targetBoardIndexRangeListExpected);


            othelloBoardSpecificIndexListTurnOverExecutorStub = mock(OthelloBoardSpecificIndexListTurnOverExecutor.class);
            doNothing()
                    .when(othelloBoardSpecificIndexListTurnOverExecutorStub)
                    .executeTurnOverStones(any());

            instance = new OthelloBoardSpecificDirectionTurnOverExecutor(
                    othelloTurnOverStoneCounterStub,
                    othelloBoardIndexRangeGeneratorStub,
                    othelloBoardSpecificIndexListTurnOverExecutorStub
            );

        }

        @Test
        public void executeTurnOverStones_givenSpecificBoardIndexAndBoardDirection() {

            UpdatableBoardIndex expectedBoardIndex = new UpdatableBoardIndex(1,1);
            BoardDirection expectedBoardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);
            ArgumentCaptor<Integer> startOffsetArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Integer> endOffsetArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<List<UpdatableBoardIndex>> boardIndexListCaptor = ArgumentCaptor.forClass(List.class);

            instance.executeTurnOverStones(expectedBoardIndex, expectedBoardDirection);

            verify(othelloTurnOverStoneCounterStub, times(1))
                    .countTurnOverStones(
                            boardIndexArgumentCaptor.capture(),
                            boardDirectionArgumentCaptor.capture()
                    );

            UpdatableBoardIndex actualBoardIndexInCountTurnOverStones = boardIndexArgumentCaptor.getValue();
            BoardDirection actualBoardDirectionInCountTurnOverStones = boardDirectionArgumentCaptor.getValue();

            assertEquals(expectedBoardIndex, actualBoardIndexInCountTurnOverStones);
            assertEquals(expectedBoardDirection, actualBoardDirectionInCountTurnOverStones);

            verify(othelloBoardIndexRangeGeneratorStub, times(1))
                    .generateBoardIndexRangeList(
                            boardIndexArgumentCaptor.capture(),
                            boardDirectionArgumentCaptor.capture(),
                            startOffsetArgumentCaptor.capture(),
                            endOffsetArgumentCaptor.capture()
                    );

            UpdatableBoardIndex actualBoardIndexInGenerateBoardIndexRangeList = boardIndexArgumentCaptor.getValue();
            BoardDirection actualBoardDirectionInGenerateBoardIndexRangeList = boardDirectionArgumentCaptor.getValue();
            int actualStartOffset = startOffsetArgumentCaptor.getValue();
            int actualEndOffset = endOffsetArgumentCaptor.getValue();
            int expectedStartOffset = 1;

            assertEquals(expectedBoardIndex, actualBoardIndexInGenerateBoardIndexRangeList);
            assertEquals(expectedBoardDirection, actualBoardDirectionInGenerateBoardIndexRangeList);
            assertThat(actualStartOffset, is(expectedStartOffset));
            assertThat(actualEndOffset, is(othelloTurnOverStoneNumExpected));

            verify(othelloBoardSpecificIndexListTurnOverExecutorStub, times(1))
                    .executeTurnOverStones(boardIndexListCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexListCaptor.getValue();
            assertEquals(targetBoardIndexRangeListExpected, actualBoardIndexList);

        }
    }

    public static class TurnOverStoneIsMinusOne {

        OthelloTurnOverStoneCounter othelloTurnOverStoneCounterStub;
        OthelloBoardIndexRangeGenerator othelloBoardIndexRangeGeneratorStub;
        OthelloBoardSpecificIndexListTurnOverExecutor othelloBoardSpecificIndexListTurnOverExecutorStub;
        OthelloBoardSpecificDirectionTurnOverExecutor instance;

        private int othelloTurnOverStoneNumExpected;
        private List<UpdatableBoardIndex> targetBoardIndexRangeListExpected;

        @Before
        public void setUp() {
            othelloTurnOverStoneNumExpected = -1;
            othelloTurnOverStoneCounterStub = mock(OthelloTurnOverStoneCounter.class);
            when(othelloTurnOverStoneCounterStub.countTurnOverStones(any(), any()))
                    .thenReturn(othelloTurnOverStoneNumExpected);


            targetBoardIndexRangeListExpected = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloBoardIndexRangeGeneratorStub = mock(OthelloBoardIndexRangeGenerator.class);
            when(othelloBoardIndexRangeGeneratorStub
                    .generateBoardIndexRangeList(any(), any(), anyInt(), anyInt()))
                    .thenReturn(targetBoardIndexRangeListExpected);


            othelloBoardSpecificIndexListTurnOverExecutorStub = mock(OthelloBoardSpecificIndexListTurnOverExecutor.class);
            doNothing()
                    .when(othelloBoardSpecificIndexListTurnOverExecutorStub)
                    .executeTurnOverStones(any());

            instance = new OthelloBoardSpecificDirectionTurnOverExecutor(
                    othelloTurnOverStoneCounterStub,
                    othelloBoardIndexRangeGeneratorStub,
                    othelloBoardSpecificIndexListTurnOverExecutorStub
            );

        }

        @Test(expected = RuntimeException.class)
        public void executeTurnOverStones_givenSpecificBoardIndexAndBoardDirection() {

            UpdatableBoardIndex expectedBoardIndex = new UpdatableBoardIndex(1,1);
            BoardDirection expectedBoardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);

            instance.executeTurnOverStones(expectedBoardIndex, expectedBoardDirection);

            verify(othelloTurnOverStoneCounterStub, times(1))
                    .countTurnOverStones(
                            boardIndexArgumentCaptor.capture(),
                            boardDirectionArgumentCaptor.capture()
                    );

            UpdatableBoardIndex actualBoardIndexInCountTurnOverStones = boardIndexArgumentCaptor.getValue();
            BoardDirection actualBoardDirectionInCountTurnOverStones = boardDirectionArgumentCaptor.getValue();

            assertEquals(expectedBoardIndex, actualBoardIndexInCountTurnOverStones);
            assertEquals(expectedBoardDirection, actualBoardDirectionInCountTurnOverStones);

        }
    }

    public static class TurnOverStoneIsSeven {

        OthelloTurnOverStoneCounter othelloTurnOverStoneCounterStub;
        OthelloBoardIndexRangeGenerator othelloBoardIndexRangeGeneratorStub;
        OthelloBoardSpecificIndexListTurnOverExecutor othelloBoardSpecificIndexListTurnOverExecutorStub;
        OthelloBoardSpecificDirectionTurnOverExecutor instance;

        private int othelloTurnOverStoneNumExpected;
        private List<UpdatableBoardIndex> targetBoardIndexRangeListExpected;

        @Before
        public void setUp() {
            othelloTurnOverStoneNumExpected = 7;
            othelloTurnOverStoneCounterStub = mock(OthelloTurnOverStoneCounter.class);
            when(othelloTurnOverStoneCounterStub.countTurnOverStones(any(), any()))
                    .thenReturn(othelloTurnOverStoneNumExpected);


            targetBoardIndexRangeListExpected = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloBoardIndexRangeGeneratorStub = mock(OthelloBoardIndexRangeGenerator.class);
            when(othelloBoardIndexRangeGeneratorStub
                    .generateBoardIndexRangeList(any(), any(), anyInt(), anyInt()))
                    .thenReturn(targetBoardIndexRangeListExpected);


            othelloBoardSpecificIndexListTurnOverExecutorStub = mock(OthelloBoardSpecificIndexListTurnOverExecutor.class);
            doNothing()
                    .when(othelloBoardSpecificIndexListTurnOverExecutorStub)
                    .executeTurnOverStones(any());

            instance = new OthelloBoardSpecificDirectionTurnOverExecutor(
                    othelloTurnOverStoneCounterStub,
                    othelloBoardIndexRangeGeneratorStub,
                    othelloBoardSpecificIndexListTurnOverExecutorStub
            );

        }

        @Test(expected = RuntimeException.class)
        public void executeTurnOverStones_givenSpecificBoardIndexAndBoardDirection() {

            UpdatableBoardIndex expectedBoardIndex = new UpdatableBoardIndex(1,1);
            BoardDirection expectedBoardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);

            instance.executeTurnOverStones(expectedBoardIndex, expectedBoardDirection);

            verify(othelloTurnOverStoneCounterStub, times(1))
                    .countTurnOverStones(
                            boardIndexArgumentCaptor.capture(),
                            boardDirectionArgumentCaptor.capture()
                    );

            UpdatableBoardIndex actualBoardIndexInCountTurnOverStones = boardIndexArgumentCaptor.getValue();
            BoardDirection actualBoardDirectionInCountTurnOverStones = boardDirectionArgumentCaptor.getValue();

            assertEquals(expectedBoardIndex, actualBoardIndexInCountTurnOverStones);
            assertEquals(expectedBoardDirection, actualBoardDirectionInCountTurnOverStones);

        }
    }
}