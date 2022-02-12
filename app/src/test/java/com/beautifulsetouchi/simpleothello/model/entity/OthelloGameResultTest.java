package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.constant.Winner;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloGameResult;

import org.junit.Test;

//import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.BLACK;
//import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.DRAW;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class OthelloGameResultTest {

    @Test
    public void testOthelloGameResultDefaultConstructor() {
        OthelloGameResult instance = new OthelloGameResult();

        Winner expectedWinner = Winner.DRAW;
        Winner actualWinner = instance.getWinner();
        assertThat(actualWinner, is(expectedWinner));

        int expectedBlackStoneNum = 2;
        int actualBlackStoneNum = instance.getBlackStoneNum();
        assertThat(actualBlackStoneNum, is(expectedBlackStoneNum));

        int expectedWhiteStoneNum = 2;
        int actualWhiteStoneNum = instance.getWhiteStoneNum();
        assertThat(actualWhiteStoneNum, is(expectedWhiteStoneNum));

    }

    @Test
    public void setWinner_givenValidWinner_validateWinner() {
        OthelloGameResult othelloGameResult = new OthelloGameResult();

        Winner expectedWinner = Winner.BLACK;
        othelloGameResult.setWinner(expectedWinner);
        Winner actualWinner = othelloGameResult.getWinner();

        assertThat(actualWinner, is(expectedWinner));
    }

    @Test
    public void setBlackStoneNum_givenValidBlackStoneNum_validateBlackStoneNum() {
        OthelloGameResult othelloGameResult = new OthelloGameResult();

        int expectedBlackStoneNum = 22;
        othelloGameResult.setBlackStoneNum(expectedBlackStoneNum);
        int actualBlackStoneNum = othelloGameResult.getBlackStoneNum();

        assertThat(actualBlackStoneNum, is(expectedBlackStoneNum));
    }

    @Test(expected=IllegalArgumentException.class)
    public void setBlackStoneNum_givenLowerBoundInvalidBlackStoneNum_throwIllegalArgumentException() {
        OthelloGameResult othelloGameResult = new OthelloGameResult();
        othelloGameResult.setBlackStoneNum(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void setBlackStoneNum_givenUpperBoundInvalidBlackStoneNum_throwIllegalArgumentException() {
        OthelloGameResult othelloGameResult = new OthelloGameResult();
        othelloGameResult.setBlackStoneNum(65);
    }

    @Test
    public void setWhiteStoneNum_givenValidWhiteStoneNum_validateWhiteStoneNum() {
        OthelloGameResult othelloGameResult = new OthelloGameResult();

        int expectedWhiteStoneNum = 22;
        othelloGameResult.setWhiteStoneNum(expectedWhiteStoneNum);
        int actualWhiteStoneNum = othelloGameResult.getWhiteStoneNum();

        assertThat(actualWhiteStoneNum, is(expectedWhiteStoneNum));
    }

    @Test(expected=IllegalArgumentException.class)
    public void setWhiteStoneNum_givenLowerBoundInvalidWhiteStoneNum_throwIllegalArgumentException() {
        OthelloGameResult othelloGameResult = new OthelloGameResult();
        othelloGameResult.setWhiteStoneNum(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void setWhiteStoneNum_givenUpperBoundInvalidWhiteStoneNum_throwIllegalArgumentException() {
        OthelloGameResult othelloGameResult = new OthelloGameResult();
        othelloGameResult.setWhiteStoneNum(65);
    }

}
