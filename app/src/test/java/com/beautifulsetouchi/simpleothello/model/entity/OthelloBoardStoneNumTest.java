package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStoneNum;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OthelloBoardStoneNumTest {

    @Test
    public void setBlackStoneNum_givenValidNum() {
        OthelloBoardStoneNum instance = new OthelloBoardStoneNum();
        int expected = 64;
        instance.setBlackStoneNum(expected);

        int actual = instance.getBlackStoneNum();

        assertThat(actual, is(expected));
    }

    @Test(expected=IllegalArgumentException.class)
    public void setBlackStoneNum_givenLowerBoundInvalidNum() {
        OthelloBoardStoneNum instance = new OthelloBoardStoneNum();
        instance.setBlackStoneNum(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void setBlackStoneNum_givenUpperBoundInvalidNum() {
        OthelloBoardStoneNum instance = new OthelloBoardStoneNum();
        instance.setBlackStoneNum(65);
    }

    @Test
    public void setWhiteStoneNum_givenValidNum() {
        OthelloBoardStoneNum instance = new OthelloBoardStoneNum();
        int expected = 64;
        instance.setWhiteStoneNum(expected);

        int actual = instance.getWhiteStoneNum();

        assertThat(actual, is(expected));
    }

    @Test(expected=IllegalArgumentException.class)
    public void setWhiteStoneNum_givenLowerBoundInvalidNum() {
        OthelloBoardStoneNum instance = new OthelloBoardStoneNum();
        instance.setWhiteStoneNum(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void setWhiteStoneNum_givenUpperBoundInvalidNum() {
        OthelloBoardStoneNum instance = new OthelloBoardStoneNum();
        instance.setWhiteStoneNum(65);
    }

}