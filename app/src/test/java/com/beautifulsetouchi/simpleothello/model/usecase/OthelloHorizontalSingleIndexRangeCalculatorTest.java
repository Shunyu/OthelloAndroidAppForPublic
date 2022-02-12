package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloHorizontalSingleIndexRangeCalculator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloSingleIndexRangeCalculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloHorizontalSingleIndexRangeCalculatorTest {

    OthelloSingleIndexRangeCalculator stub;
    OthelloHorizontalSingleIndexRangeCalculator instance;

    List<Integer> horizontalIndexRangeList;

    @Before
    public void setUp() {
        stub = mock(OthelloSingleIndexRangeCalculator.class);
        horizontalIndexRangeList = new ArrayList<>(
            Arrays.asList(2, 3)
        );
        when(stub.getIndexRangeList(anyInt(), any()))
                .thenReturn(horizontalIndexRangeList);
        instance = new OthelloHorizontalSingleIndexRangeCalculator(stub);
    }

    @Test
    public void getIndexRangeList() {
        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,3);
        BoardDirection boardDirection = new BoardDirection(
                SingleBoardDirection.DECREMENTAL_DIRECTION,
                SingleBoardDirection.NO_DIRECTION
        );

        List<Integer> actualHorizontalIndexRangeList = instance.getIndexRangeList(
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
        int actualHorizontalIndex = integerArgumentCaptor.getValue();
        SingleBoardDirection actualSingleBoardDirection = singleBoardDirectionArgumentCaptor.getValue();
        assertThat(actualHorizontalIndex, is(boardIndex.getHorizontalIndex()));
        assertThat(actualSingleBoardDirection, is(boardDirection.getHorizontalDirection()));

        assertEquals(horizontalIndexRangeList, actualHorizontalIndexRangeList);

    }

}