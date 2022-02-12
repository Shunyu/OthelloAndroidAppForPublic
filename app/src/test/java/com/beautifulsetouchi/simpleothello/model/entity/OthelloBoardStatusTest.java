package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;

import org.junit.Test;

//import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.BLACK;
//import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.DRAW;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OthelloBoardStatusTest {

    @Test
    public void testOthelloBoardStatusDefaultConstructor() {
        OthelloBoardStatus instance = new OthelloBoardStatus();

        Player expectedPlayer = Player.BLACK;
        Player actualPlayer = instance.getPlayer();
        assertThat(actualPlayer, is(expectedPlayer));

        int expectedBlackStoneNum = 2;
        int actualBlackStoneNum = instance.getBlackStoneNum();
        assertThat(actualBlackStoneNum, is(expectedBlackStoneNum));

        int expectedWhiteStoneNum = 2;
        int actualWhiteStoneNum = instance.getWhiteStoneNum();
        assertThat(actualWhiteStoneNum, is(expectedWhiteStoneNum));

        boolean expectedGameOverFlag = false;
        boolean actualGameOverFlag = instance.isGameOverFlag();
        assertThat(actualGameOverFlag, is(expectedGameOverFlag));

        boolean expectedRestartFlag = false;
        boolean actualRestartFlag = instance.isRestartFlag();
        assertThat(actualRestartFlag, is(expectedRestartFlag));

    }

    @Test
    public void setPlayer_givenValidPlayer_validatePlayer() {
        OthelloBoardStatus othelloBoardStatus = new OthelloBoardStatus();

        Player expectedPlayer = Player.BLACK;
        othelloBoardStatus.setPlayer(expectedPlayer);
        Player actualPlayer = othelloBoardStatus.getPlayer();

        assertThat(actualPlayer, is(expectedPlayer));
    }

    @Test
    public void setBlackStoneNum_givenValidBlackStoneNum_validateBlackStoneNum() {
        OthelloBoardStatus othelloBoardStatus = new OthelloBoardStatus();

        int expectedBlackStoneNum = 22;
        othelloBoardStatus.setBlackStoneNum(expectedBlackStoneNum);
        int actualBlackStoneNum = othelloBoardStatus.getBlackStoneNum();

        assertThat(actualBlackStoneNum, is(expectedBlackStoneNum));
    }

    @Test(expected=IllegalArgumentException.class)
    public void setBlackStoneNum_givenLowerBoundInvalidBlackStoneNum_throwIllegalArgumentException() {
        OthelloBoardStatus othelloBoardStatus = new OthelloBoardStatus();
        othelloBoardStatus.setBlackStoneNum(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void setBlackStoneNum_givenUpperBoundInvalidBlackStoneNum_throwIllegalArgumentException() {
        OthelloBoardStatus othelloBoardStatus = new OthelloBoardStatus();
        othelloBoardStatus.setBlackStoneNum(65);
    }

    @Test
    public void setWhiteStoneNum_givenValidWhiteStoneNum_validateWhiteStoneNum() {
        OthelloBoardStatus othelloBoardStatus = new OthelloBoardStatus();

        int expectedWhiteStoneNum = 22;
        othelloBoardStatus.setWhiteStoneNum(expectedWhiteStoneNum);
        int actualWhiteStoneNum = othelloBoardStatus.getWhiteStoneNum();

        assertThat(actualWhiteStoneNum, is(expectedWhiteStoneNum));
    }

    @Test(expected=IllegalArgumentException.class)
    public void setWhiteStoneNum_givenLowerBoundInvalidWhiteStoneNum_throwIllegalArgumentException() {
        OthelloBoardStatus othelloBoardStatus = new OthelloBoardStatus();
        othelloBoardStatus.setWhiteStoneNum(-1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void setWhiteStoneNum_givenUpperBoundInvalidWhiteStoneNum_throwIllegalArgumentException() {
        OthelloBoardStatus othelloBoardStatus = new OthelloBoardStatus();
        othelloBoardStatus.setWhiteStoneNum(65);
    }

    @Test
    public void setGameOverFlag_givenValidGameOverFlag_validateGameOverFlag() {
        OthelloBoardStatus othelloBoardStatus = new OthelloBoardStatus();

        boolean expectedGameOverFlag = true;
        othelloBoardStatus.setGameOverFlag(expectedGameOverFlag);
        boolean actualGameOverFlag = othelloBoardStatus.isGameOverFlag();

        assertThat(actualGameOverFlag, is(expectedGameOverFlag));
    }

    @Test
    public void setRestartFlag_givenValidRestartFlag_validateRestartFlag() {
        OthelloBoardStatus othelloBoardStatus = new OthelloBoardStatus();

        boolean expectedRestartFlag = true;
        othelloBoardStatus.setRestartFlag(expectedRestartFlag);
        boolean actualRestartFlag = othelloBoardStatus.isRestartFlag();

        assertThat(actualRestartFlag, is(expectedRestartFlag));
    }

    @Test
    public void getOpponentPlayer_givenPlayerIsBlack() {

        OthelloBoardStatus othelloBoardStatus = new OthelloBoardStatus();
        Player expectedPlayer = Player.BLACK;
        Player expectedOpponentPlayer = Player.WHITE;
        othelloBoardStatus.setPlayer(expectedPlayer);
        Player actualOpponentPlayer = othelloBoardStatus.getOpponentPlayer();
        assertThat(actualOpponentPlayer, is(expectedOpponentPlayer));

    }

    @Test
    public void getOpponentPlayer_givenPlayerIsWhite() {

        OthelloBoardStatus othelloBoardStatus = new OthelloBoardStatus();
        Player expectedPlayer = Player.WHITE;
        Player expectedOpponentPlayer = Player.BLACK;
        othelloBoardStatus.setPlayer(expectedPlayer);
        Player actualOpponentPlayer = othelloBoardStatus.getOpponentPlayer();
        assertThat(actualOpponentPlayer, is(expectedOpponentPlayer));

    }
}
