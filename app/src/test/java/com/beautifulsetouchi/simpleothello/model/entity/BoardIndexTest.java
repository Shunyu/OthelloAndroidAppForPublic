package com.beautifulsetouchi.simpleothello.model.entity;


import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class BoardIndexTest {

    @Test
    public void testBoardIndexConstructor() {

        int expectedVerticalIndex = 2;
        int expectedHorizontalIndex = 3;
        BoardIndex instance = new BoardIndex(expectedVerticalIndex, expectedHorizontalIndex);

        int actualVerticalIndex = instance.getVerticalIndex();
        int actualHorizontalIndex = instance.getHorizontalIndex();

        assertThat(actualVerticalIndex, is(expectedVerticalIndex));
        assertThat(actualHorizontalIndex, is(expectedHorizontalIndex));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBoardIndexConstructor_givenOutOfUpperBoundVerticalIndex() {
        new BoardIndex(10, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBoardIndexConstructor_givenOutOfLowerBoundVerticalIndex(){
        new BoardIndex(-1, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBoardIndexConstructor_givenOutOfUpperBoundHorizontalIndex() {
        new BoardIndex(0,10);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBoardIndexConstructor_givenOutOfLowerBoundHorizontalIndex() {
        new BoardIndex(0,-1);
    }

    @Test
    public void setVerticalIndex_givenBoardIndex_validateVerticalIndex(){
        BoardIndex boardIndex = new BoardIndex(0,0);

        int expected = 2;
        boardIndex.setVerticalIndex(expected);
        int actual = boardIndex.getVerticalIndex();

        assertThat(actual, is(expected));
    }

    @Test
    public void setHorizontalIndex_givenBoardIndex_validateHorizontalIndex(){
        BoardIndex boardIndex = new BoardIndex(0,0);

        int expected = 3;
        boardIndex.setHorizontalIndex(expected);
        int actual = boardIndex.getHorizontalIndex();

        assertThat(actual, is(expected));
    }

    @Test(expected=IllegalArgumentException.class)
    public void setVerticalIndex_givenOutOfLowerBoundVerticalIndex_throwIllegalArgumentException() {
        BoardIndex boardIndex = new BoardIndex(2,2);
        boardIndex.setVerticalIndex(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void setVerticalIndex_givenOutOfUpperBoundVerticalIndex_throwIllegalArgumentException() {
        BoardIndex boardIndex = new BoardIndex(1,1);
        boardIndex.setVerticalIndex(10);
    }

    @Test(expected=IllegalArgumentException.class)
    public void setHorizontalIndex_givenOutOfLowerBoardHorizontalIndex_throwIllegalArgumentException() {
        BoardIndex boardIndex = new BoardIndex(1,1);
        boardIndex.setHorizontalIndex(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void setHorizontalIndex_givenOutOfUpperBoundHorizontalIndex_throwIllegalArgumentException() {
        BoardIndex boardIndex = new BoardIndex(2,2);
        boardIndex.setHorizontalIndex(10);
    }

}
