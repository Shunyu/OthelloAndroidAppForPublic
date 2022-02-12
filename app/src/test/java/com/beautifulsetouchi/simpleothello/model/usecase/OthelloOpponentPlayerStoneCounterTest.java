package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloOpponentPlayerStoneSequenceInformation;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexRangeCalculator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloOpponentPlayerStoneCounter;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloOpponentPlayerStoneSequenceInformationGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class OthelloOpponentPlayerStoneCounterTest {

    public static class NonOpponentPlayerExistenceFlagIsTrue {

        OthelloBoardIndexRangeCalculator othelloBoardIndexRangeCalculatorStub;
        OthelloOpponentPlayerStoneSequenceInformationGenerator othelloOpponentPlayerStoneSequenceInformationGeneratorStub;
        OthelloOpponentPlayerStoneCounter othelloOpponentPlayerStoneCounter;

        private List<BoardIndex> boardIndexRangeList;

        @Before
        public void setUp() {

            othelloBoardIndexRangeCalculatorStub = mock(OthelloBoardIndexRangeCalculator.class);
            boardIndexRangeList = new ArrayList<>(
                    Arrays.asList(
                            new BoardIndex(2,2),
                            new BoardIndex(2,3)
                    )
            );
            when(othelloBoardIndexRangeCalculatorStub.getBoardIndexRangeList(any(), any()))
                    .thenReturn(boardIndexRangeList);

            othelloOpponentPlayerStoneSequenceInformationGeneratorStub = mock(OthelloOpponentPlayerStoneSequenceInformationGenerator.class);
            OthelloOpponentPlayerStoneSequenceInformation sequenceInformation = new OthelloOpponentPlayerStoneSequenceInformation();
            sequenceInformation.setNonOpponentPlayerExistenceFlag(true);
            sequenceInformation.setOpponentPlayerStoneNum(2);
            when(othelloOpponentPlayerStoneSequenceInformationGeneratorStub.getOthelloOpponentPlayerStoneSequenceInformation(any()))
                    .thenReturn(sequenceInformation);

            othelloOpponentPlayerStoneCounter = new OthelloOpponentPlayerStoneCounter(
                    othelloBoardIndexRangeCalculatorStub,
                    othelloOpponentPlayerStoneSequenceInformationGeneratorStub
            );
        }

        @Test
        public void countOpponentPlayerStones_givenValidBoardIndex_returnZero() {

            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1, 1);
            BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION, SingleBoardDirection.INCREMENTAL_DIRECTION);

            int expected = 2;
            int actual = othelloOpponentPlayerStoneCounter.countOpponentPlayerStones(boardIndex, boardDirection);
            assertThat(actual, is(expected));

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);
            verify(othelloBoardIndexRangeCalculatorStub, times(1))
                    .getBoardIndexRangeList(
                            boardIndexArgumentCaptor.capture(),
                            boardDirectionArgumentCaptor.capture()
                    );
            UpdatableBoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();
            BoardDirection actualBoardDirection = boardDirectionArgumentCaptor.getValue();
            assertThat(actualBoardIndex, is(boardIndex));
            assertThat(actualBoardDirection, is(boardDirection));

            ArgumentCaptor<List> boardIndexListArgumentCaptor = ArgumentCaptor.forClass(List.class);
            verify(othelloOpponentPlayerStoneSequenceInformationGeneratorStub, times(1))
                    .getOthelloOpponentPlayerStoneSequenceInformation(boardIndexListArgumentCaptor.capture());
            List<BoardIndex> actualBoardIndexRangeList = boardIndexListArgumentCaptor.getValue();
            assertEquals(actualBoardIndexRangeList, boardIndexRangeList);

        }

    }

    public static class NonOpponentPlayerExistenceFlagIsFalse{

        OthelloBoardIndexRangeCalculator othelloBoardIndexRangeCalculatorStub;
        OthelloOpponentPlayerStoneSequenceInformationGenerator othelloOpponentPlayerStoneSequenceInformationGeneratorStub;
        OthelloOpponentPlayerStoneCounter othelloOpponentPlayerStoneCounter;

        @Before
        public void setUp() {

            othelloBoardIndexRangeCalculatorStub = mock(OthelloBoardIndexRangeCalculator.class);
            List<BoardIndex> boardIndexRangeList = new ArrayList<>();
            when(othelloBoardIndexRangeCalculatorStub.getBoardIndexRangeList(any(), any()))
                    .thenReturn(boardIndexRangeList);

            othelloOpponentPlayerStoneSequenceInformationGeneratorStub = mock(OthelloOpponentPlayerStoneSequenceInformationGenerator.class);
            OthelloOpponentPlayerStoneSequenceInformation sequenceInformation = new OthelloOpponentPlayerStoneSequenceInformation();
            when(othelloOpponentPlayerStoneSequenceInformationGeneratorStub.getOthelloOpponentPlayerStoneSequenceInformation(any()))
                    .thenReturn(sequenceInformation);

            othelloOpponentPlayerStoneCounter = new OthelloOpponentPlayerStoneCounter(
                    othelloBoardIndexRangeCalculatorStub,
                    othelloOpponentPlayerStoneSequenceInformationGeneratorStub
            );
        }

        @Test(expected = RuntimeException.class)
        public void countOpponentPlayerStones_givenValidBoardIndex_returnOne() {

            UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,1);
            BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);

            othelloOpponentPlayerStoneCounter.countOpponentPlayerStones(boardIndex, boardDirection);

        }


    }

}
