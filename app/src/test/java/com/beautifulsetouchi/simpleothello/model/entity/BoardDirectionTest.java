package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class BoardDirectionTest {

    @Test
    public void testBoardDirectionConstructor_givenValidDirection_validateVerticalDirection(){
        SingleBoardDirection expectedVerticalDirection = SingleBoardDirection.DECREMENTAL_DIRECTION;
        SingleBoardDirection expectedHorizontalDirection = SingleBoardDirection.INCREMENTAL_DIRECTION;

        BoardDirection instance = new BoardDirection(expectedVerticalDirection, expectedHorizontalDirection);

        SingleBoardDirection actualVerticalDirection = instance.getVerticalDirection();
        SingleBoardDirection actualHorizontalDirection = instance.getHorizontalDirection();

        assertThat(actualVerticalDirection, is(expectedVerticalDirection));
        assertThat(actualHorizontalDirection, is(expectedHorizontalDirection));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testBoardDirectionConstructor_givenInvalidVerticalAndHorizontalDirection_throwIllegalArgumentException(){
        SingleBoardDirection invalidVerticalDirection = SingleBoardDirection.NO_DIRECTION;
        SingleBoardDirection invalidHorizontalDirection = SingleBoardDirection.NO_DIRECTION;
        new BoardDirection(invalidVerticalDirection, invalidHorizontalDirection);
    }
}
