package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloSingleIndexRangeCalculator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloVerticalSingleIndexRangeCalculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloVerticalSingleIndexRangeCalculatorTest {

    OthelloSingleIndexRangeCalculator stub;
    OthelloVerticalSingleIndexRangeCalculator instance;

    List<Integer> verticalIndexRangeList;

    @Before
    public void setUp() {
        stub = mock(OthelloSingleIndexRangeCalculator.class);
        verticalIndexRangeList = new ArrayList<>(
            Arrays.asList(2, 3)
        );
        when(stub.getIndexRangeList(anyInt(), any()))
                .thenReturn(verticalIndexRangeList);
        instance = new OthelloVerticalSingleIndexRangeCalculator(stub);
    }

    @Test
    public void getIndexRangeList() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,3);
        BoardDirection boardDirection = new BoardDirection(
                SingleBoardDirection.DECREMENTAL_DIRECTION,
                SingleBoardDirection.NO_DIRECTION
        );

        List<Integer> actualVerticalIndexRangeList = instance.getIndexRangeList(
                boardIndex,
                boardDirection
        );

        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<SingleBoardDirection> singleBoardDirectionArgumentCaptor = ArgumentCaptor.forClass(SingleBoardDirection.class);
        verify(stub, times(1))
                .getIndexRangeList(
                        integerArgumentCaptor.capture(),
                        singleBoardDirectionArgumentCaptor.capture()
                );
        int actualVerticalIndex = integerArgumentCaptor.getValue();
        SingleBoardDirection actualSingleBoardDirection = singleBoardDirectionArgumentCaptor.getValue();
        assertThat(actualVerticalIndex, is(boardIndex.getVerticalIndex()));
        assertThat(actualSingleBoardDirection, is(boardDirection.getVerticalDirection()));

        assertEquals(verticalIndexRangeList, actualVerticalIndexRangeList);

    }

}