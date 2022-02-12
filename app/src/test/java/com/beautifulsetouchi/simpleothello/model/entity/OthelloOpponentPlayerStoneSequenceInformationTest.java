package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.entity.OthelloOpponentPlayerStoneSequenceInformation;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OthelloOpponentPlayerStoneSequenceInformationTest {

    @Test
    public void testOthelloOpponentPlayerStoneSequenceInformationDefaultConstructor() {
        OthelloOpponentPlayerStoneSequenceInformation instance = new OthelloOpponentPlayerStoneSequenceInformation();

        int actualOpponentPlayerStoneNum = instance.getOpponentPlayerStoneNum();
        boolean actualNonOpponentPlayerExistenceFlag = instance.isNonOpponentPlayerExistenceFlag();

        int expectedOpponentPlayerStoneNum = 0;
        boolean expectedNonOpponentPlayerExistenceFlag = false;

        assertThat(actualOpponentPlayerStoneNum, is(expectedOpponentPlayerStoneNum));
        assertThat(actualNonOpponentPlayerExistenceFlag, is(expectedNonOpponentPlayerExistenceFlag));
    }

    @Test
    public void setNonOpponentPlayerExistenceFlag_givenDefaultInstance_validateNonOpponentPlayerExistenceFlag() {
        OthelloOpponentPlayerStoneSequenceInformation instance = new OthelloOpponentPlayerStoneSequenceInformation();

        boolean expected = true;
        instance.setNonOpponentPlayerExistenceFlag(expected);
        boolean actual = instance.isNonOpponentPlayerExistenceFlag();

        assertThat(actual, is(expected));
    }

    @Test
    public void incrementOpponentPlayerStoneNum_givenDefaultInstance_validateOpponentPlayerStoneNum() {
        OthelloOpponentPlayerStoneSequenceInformation instance = new OthelloOpponentPlayerStoneSequenceInformation();

        int expected = 2;
        instance.incrementOpponentPlayerStoneNum();
        instance.incrementOpponentPlayerStoneNum();
        int actual = instance.getOpponentPlayerStoneNum();

        assertThat(actual, is(expected));
    }
}
