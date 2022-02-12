package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.BoardIndexCastor;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexAdder;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexRangeGenerator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexRangeGeneratorChecker;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class OthelloBoardIndexRangeGeneratorTest {

    public static class GivenValidTargetBoardIndex {

        OthelloBoardIndexAdder othelloBoardIndexAdderStub;
        BoardIndexCastor boardIndexCastorStub;
        OthelloBoardIndexRangeGeneratorChecker othelloBoardIndexRangeGeneratorCheckerStub;
        OthelloBoardIndexRangeGenerator instance;

        @Before
        public void setUp() {
            othelloBoardIndexAdderStub = mock(OthelloBoardIndexAdder.class);
            when(othelloBoardIndexAdderStub.addBoardIndex(any(), any(), anyInt()))
                    .thenReturn(new BoardIndex(1,1));

            boardIndexCastorStub = mock(BoardIndexCastor.class);
            when(boardIndexCastorStub.castToUpdatableBoardIndex(any()))
                    .thenReturn(new UpdatableBoardIndex(1,1));

            othelloBoardIndexRangeGeneratorCheckerStub = mock(OthelloBoardIndexRangeGeneratorChecker.class);
            when(othelloBoardIndexRangeGeneratorCheckerStub.isUpdatableBoardIndex(any(), any(), anyInt()))
                    .thenReturn(true)
                    .thenReturn(true);

            instance = new OthelloBoardIndexRangeGenerator(
                    othelloBoardIndexAdderStub,
                    boardIndexCastorStub,
                    othelloBoardIndexRangeGeneratorCheckerStub
            );
        }

        @Test
        public void generateBoardIndexRangeList_givenLegalOffset() {

            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
            BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
            int startOffset = 1;
            int endOffset = 4;
            int expectedCounter = endOffset - startOffset + 1;

            List<UpdatableBoardIndex> actualBoardIndexList = instance.generateBoardIndexRangeList(
                    boardIndex,
                    boardDirection,
                    startOffset,
                    endOffset
            );

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptorForChecker = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptorForChecker = ArgumentCaptor.forClass(BoardDirection.class);
            ArgumentCaptor<Integer> integerArgumentCaptorForChecker = ArgumentCaptor.forClass(Integer.class);
            verify(othelloBoardIndexRangeGeneratorCheckerStub, times(2))
                    .isUpdatableBoardIndex(
                            boardIndexArgumentCaptorForChecker.capture(),
                            boardDirectionArgumentCaptorForChecker.capture(),
                            integerArgumentCaptorForChecker.capture()
                    );
            List<UpdatableBoardIndex> actualArgumentBoardIndexListForChecker = boardIndexArgumentCaptorForChecker.getAllValues();
            List<BoardDirection> actualArgumentBoardDirectionListForChecker = boardDirectionArgumentCaptorForChecker.getAllValues();
            List<Integer> actualArgumentIntegerListForChecker = integerArgumentCaptorForChecker.getAllValues();

            List<UpdatableBoardIndex> expectedArgumentBoardIndexListForChecker = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,2)
                    )
            );
            List<BoardDirection> expectedArgumentBoardDirectionListForChecker = new ArrayList<>(
                    Arrays.asList(
                            new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION),
                            new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION)
                    )
            );
            List<Integer> expectedArgumentIntegerListForChecker = new ArrayList<>(
                    Arrays.asList(
                            startOffset,
                            endOffset
                    )
            );

            assertEquals(expectedArgumentBoardIndexListForChecker, actualArgumentBoardIndexListForChecker);
            assertEquals(expectedArgumentBoardDirectionListForChecker, actualArgumentBoardDirectionListForChecker);
            assertEquals(expectedArgumentIntegerListForChecker, actualArgumentIntegerListForChecker);

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);
            ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            verify(othelloBoardIndexAdderStub, times(expectedCounter))
                    .addBoardIndex(
                            boardIndexArgumentCaptor.capture(),
                            boardDirectionArgumentCaptor.capture(),
                            integerArgumentCaptor.capture()
                    );

            List<UpdatableBoardIndex> actualArgumentBoardIndexList = boardIndexArgumentCaptor.getAllValues();
            List<BoardDirection> actualArgumentBoardDirectionList = boardDirectionArgumentCaptor.getAllValues();
            List<Integer> actualArgumentInteger = integerArgumentCaptor.getAllValues();

            ArgumentCaptor<BoardIndex> boardIndexArgumentCaptorForBoardIndexCastor = ArgumentCaptor.forClass(BoardIndex.class);
            verify(boardIndexCastorStub, times(expectedCounter))
                    .castToUpdatableBoardIndex(
                            boardIndexArgumentCaptorForBoardIndexCastor.capture()
                    );
            List<BoardIndex> actualArgumentBoardIndexListForBoardIndexCastor = boardIndexArgumentCaptorForBoardIndexCastor.getAllValues();

            List<UpdatableBoardIndex> expectedArgumentBoardIndexList = new ArrayList<>();
            List<BoardDirection> expectedArgumentBoardDirectionList = new ArrayList<>();
            List<Integer> expectedArgumentIntegerList = new ArrayList<>();
            List<BoardIndex> expectedArgumentBoardIndexListForBoardIndexCastor = new ArrayList<>();
            List<UpdatableBoardIndex> expectedBoardIndexList = new ArrayList<>();
            for (int count = 1; count<= expectedCounter; count++) {
                expectedArgumentBoardIndexList.add(new UpdatableBoardIndex(2,2));
                expectedArgumentBoardDirectionList.add(new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION));
                expectedArgumentIntegerList.add(startOffset + count - 1);
                expectedArgumentBoardIndexListForBoardIndexCastor.add(new BoardIndex(1,1));
                expectedBoardIndexList.add(new UpdatableBoardIndex(1,1));
            }

            assertEquals(expectedArgumentBoardIndexList, actualArgumentBoardIndexList);
            assertEquals(expectedArgumentBoardDirectionList, actualArgumentBoardDirectionList);
            assertEquals(expectedArgumentIntegerList, actualArgumentInteger);
            assertEquals(expectedArgumentBoardIndexListForBoardIndexCastor, actualArgumentBoardIndexListForBoardIndexCastor);
            assertEquals(expectedBoardIndexList, actualBoardIndexList);

        }

        @Test(expected = IllegalArgumentException.class)
        public void generateBoardIndexRangeList_givenLowerBoundInvalidStartOffset() {

            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
            BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
            int startOffset = 0;
            int endOffset = 4;

            instance.generateBoardIndexRangeList(
                    boardIndex,
                    boardDirection,
                    startOffset,
                    endOffset
            );

        }

        @Test(expected = IllegalArgumentException.class)
        public void generateBoardIndexRangeList_givenUpperBoundInvalidEndOffset() {

            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
            BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
            int startOffset = 1;
            int endOffset = 9;

            instance.generateBoardIndexRangeList(
                    boardIndex,
                    boardDirection,
                    startOffset,
                    endOffset
            );

        }

        @Test(expected = IllegalArgumentException.class)
        public void generateBoardIndexRangeList_givenInvalidOffsetRelation() {

            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
            BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
            int startOffset = 5;
            int endOffset = 4;

            instance.generateBoardIndexRangeList(
                    boardIndex,
                    boardDirection,
                    startOffset,
                    endOffset
            );

        }
    }

    public static class GivenInvalidStartOffset {

        OthelloBoardIndexAdder othelloBoardIndexAdderStub;
        BoardIndexCastor boardIndexCastorStub;
        OthelloBoardIndexRangeGeneratorChecker othelloBoardIndexRangeGeneratorCheckerStub;
        OthelloBoardIndexRangeGenerator instance;

        @Test(expected = IllegalArgumentException.class)
        public void generateBoardIndexRangeList() {

            othelloBoardIndexAdderStub = mock(OthelloBoardIndexAdder.class);
            when(othelloBoardIndexAdderStub.addBoardIndex(any(), any(), anyInt()))
                    .thenReturn(new BoardIndex(1,1));

            boardIndexCastorStub = mock(BoardIndexCastor.class);
            when(boardIndexCastorStub.castToUpdatableBoardIndex(any()))
                    .thenReturn(new UpdatableBoardIndex(1,1));

            othelloBoardIndexRangeGeneratorCheckerStub = mock(OthelloBoardIndexRangeGeneratorChecker.class);
            when(othelloBoardIndexRangeGeneratorCheckerStub.isUpdatableBoardIndex(any(), any(), anyInt()))
                    .thenReturn(false)
                    .thenReturn(true);

            instance = new OthelloBoardIndexRangeGenerator(
                    othelloBoardIndexAdderStub,
                    boardIndexCastorStub,
                    othelloBoardIndexRangeGeneratorCheckerStub
            );

            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
            BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
            int startOffset = 2;
            int endOffset = 4;

            instance.generateBoardIndexRangeList(
                    boardIndex,
                    boardDirection,
                    startOffset,
                    endOffset
            );

        }

    }

    public static class GivenInvalidEndOffset {

        OthelloBoardIndexAdder othelloBoardIndexAdderStub;
        BoardIndexCastor boardIndexCastorStub;
        OthelloBoardIndexRangeGeneratorChecker othelloBoardIndexRangeGeneratorCheckerStub;
        OthelloBoardIndexRangeGenerator instance;

        @Test(expected = IllegalArgumentException.class)
        public void generateBoardIndexRangeList() {

            othelloBoardIndexAdderStub = mock(OthelloBoardIndexAdder.class);
            when(othelloBoardIndexAdderStub.addBoardIndex(any(), any(), anyInt()))
                    .thenReturn(new BoardIndex(1,1));

            boardIndexCastorStub = mock(BoardIndexCastor.class);
            when(boardIndexCastorStub.castToUpdatableBoardIndex(any()))
                    .thenReturn(new UpdatableBoardIndex(1,1));

            othelloBoardIndexRangeGeneratorCheckerStub = mock(OthelloBoardIndexRangeGeneratorChecker.class);
            when(othelloBoardIndexRangeGeneratorCheckerStub.isUpdatableBoardIndex(any(), any(), anyInt()))
                    .thenReturn(true)
                    .thenReturn(false);

            instance = new OthelloBoardIndexRangeGenerator(
                    othelloBoardIndexAdderStub,
                    boardIndexCastorStub,
                    othelloBoardIndexRangeGeneratorCheckerStub
            );

            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
            BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
            int startOffset = 2;
            int endOffset = 4;

            instance.generateBoardIndexRangeList(
                    boardIndex,
                    boardDirection,
                    startOffset,
                    endOffset
            );

        }

    }
}