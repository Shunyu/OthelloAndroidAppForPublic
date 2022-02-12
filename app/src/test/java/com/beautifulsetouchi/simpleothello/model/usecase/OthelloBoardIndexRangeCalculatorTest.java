package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexRangeCalculator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloHorizontalSingleIndexRangeCalculator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloVerticalSingleIndexRangeCalculator;
import com.beautifulsetouchi.simpleothello.utilities.MinListLengthCalculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloBoardIndexRangeCalculatorTest {

    OthelloVerticalSingleIndexRangeCalculator othelloVerticalSingleIndexRangeCalculatorStub;
    OthelloHorizontalSingleIndexRangeCalculator othelloHorizontalSingleIndexRangeCalculatorStub;
    MinListLengthCalculator spy;
    OthelloBoardIndexRangeCalculator instance;

    List<Integer> verticalIndexRangeList;
    List<Integer> horizontalIndexRangeList;

    @Before
    public void setUp() {
        othelloVerticalSingleIndexRangeCalculatorStub = mock(OthelloVerticalSingleIndexRangeCalculator.class);
        othelloHorizontalSingleIndexRangeCalculatorStub = mock(OthelloHorizontalSingleIndexRangeCalculator.class);
        verticalIndexRangeList = new ArrayList<>(Arrays.asList(3, 2, 1, 0));
        horizontalIndexRangeList = new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2));
        when(othelloVerticalSingleIndexRangeCalculatorStub.getIndexRangeList(any(), any()))
                .thenReturn(verticalIndexRangeList);
        when(othelloHorizontalSingleIndexRangeCalculatorStub.getIndexRangeList(any(), any()))
                .thenReturn(horizontalIndexRangeList);
        spy = spy(MinListLengthCalculator.class);

        instance = new OthelloBoardIndexRangeCalculator(
                othelloVerticalSingleIndexRangeCalculatorStub,
                othelloHorizontalSingleIndexRangeCalculatorStub,
                spy
        );

    }

    @Test
    public void getBoardIndexRangeList_givenValidBoardIndex_validateBoardIndexRangeList() {

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,1);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
        List<BoardIndex> actual = instance.getBoardIndexRangeList(boardIndex, boardDirection);

        ArgumentCaptor<UpdatableBoardIndex> updatableBoardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);
        verify(othelloVerticalSingleIndexRangeCalculatorStub, times(1))
                .getIndexRangeList(
                        updatableBoardIndexArgumentCaptor.capture(),
                        boardDirectionArgumentCaptor.capture()
                );
        UpdatableBoardIndex actualBoardIndexOfVerticalStub = updatableBoardIndexArgumentCaptor.getValue();
        BoardDirection actualBoardDirectionOfVerticalStub = boardDirectionArgumentCaptor.getValue();
        assertThat(actualBoardIndexOfVerticalStub, is(boardIndex));
        assertThat(actualBoardDirectionOfVerticalStub, is(boardDirection));

        verify(othelloHorizontalSingleIndexRangeCalculatorStub, times(1))
                .getIndexRangeList(
                        updatableBoardIndexArgumentCaptor.capture(),
                        boardDirectionArgumentCaptor.capture()
                );
        UpdatableBoardIndex actualBoardIndexOfHorizontalStub = updatableBoardIndexArgumentCaptor.getValue();
        BoardDirection actualBoardDirectionOfHorizontalStub = boardDirectionArgumentCaptor.getValue();
        assertThat(actualBoardIndexOfHorizontalStub, is(boardIndex));
        assertThat(actualBoardDirectionOfHorizontalStub, is(boardDirection));


        ArgumentCaptor<List> verticalListArgumentCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<List> horizontalListArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(spy, times(1))
                .getMinListLength(
                        verticalListArgumentCaptor.capture(),
                        horizontalListArgumentCaptor.capture()
                );
        List<Integer> actualVerticalIndexList = verticalListArgumentCaptor.getValue();
        List<Integer> actualHorizontalIndexList = horizontalListArgumentCaptor.getValue();
        assertEquals(verticalIndexRangeList, actualVerticalIndexList);
        assertEquals(horizontalIndexRangeList, actualHorizontalIndexList);

        List<BoardIndex> expected = new ArrayList<>();
        expected.add(new BoardIndex(3,2));
        expected.add(new BoardIndex(2,2));
        expected.add(new BoardIndex(1,2));
        expected.add(new BoardIndex(0,2));
        assertEquals(expected, actual);


    }

}
