package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloSingleIndexRangeCalculator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OthelloSingleIndexRangeCalculatorTest {

    OthelloSingleIndexRangeCalculator othelloSingleIndexRangeCalculator;

    @Before
    public void setUp() {
        othelloSingleIndexRangeCalculator = new OthelloSingleIndexRangeCalculator();
    }

    @Test
    public void getIndexRangeList_givenValidIndexAndNoDirection_returnIndexList(){
        int validIndex = 2;
        SingleBoardDirection noDirection = SingleBoardDirection.NO_DIRECTION;

        List<Integer> expected = new ArrayList<>(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2));
        List<Integer> actual = othelloSingleIndexRangeCalculator.getIndexRangeList(validIndex, noDirection);


        assertEquals(expected, actual);
    }

    @Test
    public void getIndexRangeList_givenValidIndexAndDecrementDirection_returnIndexList() {
        int validIndex = 5;
        SingleBoardDirection decrementDirection = SingleBoardDirection.DECREMENTAL_DIRECTION;

        List<Integer> expected = new ArrayList<>(Arrays.asList(4, 3, 2, 1, 0));
        List<Integer> actual = othelloSingleIndexRangeCalculator.getIndexRangeList(validIndex, decrementDirection);

        assertEquals(expected, actual);
    }

    @Test
    public void getIndexRangeList_givenValidIndexAndIncrementDirection_returnIndexList() {
        int validIndex = 6;
        SingleBoardDirection incrementDirection = SingleBoardDirection.INCREMENTAL_DIRECTION;

        List<Integer> expected = new ArrayList<>(Arrays.asList(7, 8, 9));
        List<Integer> actual = othelloSingleIndexRangeCalculator.getIndexRangeList(validIndex, incrementDirection);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getIndexRangeList_givenLowerBoundInvalidIndex_throwIllegalArgumentException() {
        int lowerBoundInvalidIndex = 0;
        SingleBoardDirection direction = SingleBoardDirection.NO_DIRECTION;

        othelloSingleIndexRangeCalculator.getIndexRangeList(lowerBoundInvalidIndex, direction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getIndexRangeList_givenUpperBoundInvalidIndex_throwIllegalArgumentException(){
        int upperBoundInvalidIndex = 9;
        SingleBoardDirection direction = SingleBoardDirection.NO_DIRECTION;

        othelloSingleIndexRangeCalculator.getIndexRangeList(upperBoundInvalidIndex, direction);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void getIndexRangeList_givenInvalidDirection_throwIllegalArgumentException() {
//        int index = 2;
//        int invalidDirection = 2;
//
//        othelloSingleIndexRangeCalculator.getIndexRangeList(index, invalidDirection);
//    }
}
