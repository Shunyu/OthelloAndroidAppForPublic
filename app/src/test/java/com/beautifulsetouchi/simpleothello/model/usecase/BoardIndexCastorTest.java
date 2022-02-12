package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.BoardIndexCastor;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BoardIndexCastorTest {

    BoardIndexCastor instance;

    @Before
    public void setUp() {
        instance = new BoardIndexCastor();
    }

    @Test
    public void castToUpdatableBoardIndex_givenValidBoardIndex() {
        BoardIndex boardIndex = new BoardIndex(2,3);
        UpdatableBoardIndex expected = new UpdatableBoardIndex(2,3);

        UpdatableBoardIndex actual = instance.castToUpdatableBoardIndex(boardIndex);

        assertThat(actual, is(expected));
    }

    @Test(expected=RuntimeException.class)
    public void castToUpdatableBoardIndex_givenLowerBoundInvalidVerticalIndex() {
        BoardIndex boardIndex = new BoardIndex(0,2);
        instance.castToUpdatableBoardIndex(boardIndex);
    }

    @Test(expected=RuntimeException.class)
    public void castToUpdatableBoardIndex_givenUpperBoundInvalidVerticalIndex() {
        BoardIndex boardIndex = new BoardIndex(9,2);
        instance.castToUpdatableBoardIndex(boardIndex);
    }

    @Test(expected=RuntimeException.class)
    public void castToUpdatableBoardIndex_givenLowerBoundInvalidHorizontalIndex() {
        BoardIndex boardIndex = new BoardIndex(2,0);
        instance.castToUpdatableBoardIndex(boardIndex);
    }

    @Test(expected=RuntimeException.class)
    public void castToUpdatableBoardIndex_givenUpperBoundInvalidHorizontalIndex() {
        BoardIndex boardIndex = new BoardIndex(2,9);
        instance.castToUpdatableBoardIndex(boardIndex);
    }

}