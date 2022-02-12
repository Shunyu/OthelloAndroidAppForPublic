package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.constant.Winner;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloGameResult;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloHintBoard;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloServiceTest {

    OthelloBoard othelloBoardStub;
    OthelloBoardStatus othelloBoardStatusStub;
    OthelloGameResult othelloGameResultStub;
    OthelloNextMoveCalculator othelloNextMoveCalculatorStub;
    OthelloTaskManager othelloTaskManagerStub;

    OthelloService instance;

    private int[][] boardArray = new int[2][2];
    private int blackStoneNum;
    private int whiteStoneNum;
    private Player player;
    private boolean gameOverFlag;
    private boolean restartFlag;
    private Winner winner;
    private OthelloHintBoard othelloHintBoardStub;
    private int[][] hintBoardArray = new int[2][2];
    private boolean gameUpdateFlag;

    @Before
    public void setUp() {

        boardArray[0][0] = 1;
        boardArray[0][1] = 1;
        boardArray[1][0] = 1;
        boardArray[1][1] = 1;

        othelloBoardStub = mock(OthelloBoard.class);
        when(othelloBoardStub.getBoardArray())
                .thenReturn(boardArray);

        blackStoneNum = 10;
        whiteStoneNum = 20;
        player = Player.BLACK;
        gameOverFlag = false;
        restartFlag = false;
        othelloBoardStatusStub = mock(OthelloBoardStatus.class);
        when(othelloBoardStatusStub.getBlackStoneNum())
                .thenReturn(blackStoneNum);
        when(othelloBoardStatusStub.getWhiteStoneNum())
                .thenReturn(whiteStoneNum);
        when(othelloBoardStatusStub.getPlayer())
                .thenReturn(player);
        when(othelloBoardStatusStub.isGameOverFlag())
                .thenReturn(gameOverFlag);
        when(othelloBoardStatusStub.isRestartFlag())
                .thenReturn(restartFlag);
        doNothing()
                .when(othelloBoardStatusStub)
                .setRestartFlag(anyBoolean());

        winner = Winner.WHITE;
        othelloGameResultStub = mock(OthelloGameResult.class);
        when(othelloGameResultStub.getWinner())
                .thenReturn(winner);

        hintBoardArray[0][0] = 1;
        hintBoardArray[0][1] = 1;
        hintBoardArray[1][0] = 1;
        hintBoardArray[1][1] = 1;
        othelloHintBoardStub = mock(OthelloHintBoard.class);
        when(othelloHintBoardStub.getBoardArray())
                .thenReturn(hintBoardArray);
        othelloNextMoveCalculatorStub = mock(OthelloNextMoveCalculator.class);
        when(othelloNextMoveCalculatorStub.calculateOthelloHintBoard())
                .thenReturn(othelloHintBoardStub);

        gameUpdateFlag = true;
        othelloTaskManagerStub = mock(OthelloTaskManager.class);
        when(othelloTaskManagerStub.isGameUpdate(any()))
                .thenReturn(gameUpdateFlag);

        instance = new OthelloService(
                othelloBoardStub,
                othelloBoardStatusStub,
                othelloGameResultStub,
                othelloNextMoveCalculatorStub,
                othelloTaskManagerStub
        );
    }

    @Test
    public void isGameUpdate_givenValidCounter() {

        int tappedVerticalCounter = 2;
        int tappedHorizontalCounter = 3;
        UpdatableBoardIndex tappedBoardIndex = new UpdatableBoardIndex(tappedVerticalCounter, tappedHorizontalCounter);
        ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

        boolean actualFlag = instance.isGameUpdate(
                tappedVerticalCounter,
                tappedHorizontalCounter
        );

        verify(othelloTaskManagerStub, times(1))
                .isGameUpdate(boardIndexArgumentCaptor.capture());
        UpdatableBoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();
        assertEquals(tappedBoardIndex, actualBoardIndex);

        boolean expectedFlag = true;
        assertThat(actualFlag)
                .isEqualTo(expectedFlag);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isGameUpdate_givenLowerBoundInvalidVerticalIndex() {

        int tappedVerticalCounter = 0;
        int tappedHorizontalCounter = 4;

        instance.isGameUpdate(tappedVerticalCounter, tappedHorizontalCounter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isGameUpdate_givenUpperBoundInvalidVerticalIndex() {

        int tappedVerticalCounter = 9;
        int tappedHorizontalCounter = 4;

        instance.isGameUpdate(tappedVerticalCounter, tappedHorizontalCounter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isGameUpdate_givenLowerBoundInvalidHorizontalIndex() {

        int tappedVerticalIndex = 4;
        int tappedHorizontalIndex = 0;

        instance.isGameUpdate(tappedVerticalIndex, tappedHorizontalIndex);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isGameUpdate_givenUpperBoundInvalidHorizontalIndex() {

        int tappedVerticalIndex = 4;
        int tappedHorizontalIndex = 9;

        instance.isGameUpdate(tappedVerticalIndex, tappedHorizontalIndex);
    }

    @Test
    public void getBoardArray() {

        int[][] actualBoardArray = instance.getBoardArray();

        verify(othelloBoardStub, times(1))
                .getBoardArray();

        assertThat(actualBoardArray)
                .isDeepEqualTo(boardArray);

    }

    @Test
    public void getHintBoardArray() {

        int[][] actualHintBoardArray = instance.getHintArray();

        verify(othelloNextMoveCalculatorStub, times(1))
                .calculateOthelloHintBoard();

        verify(othelloHintBoardStub, times(1))
                .getBoardArray();

        assertThat(actualHintBoardArray)
                .isDeepEqualTo(hintBoardArray);
    }

    @Test
    public void getBlackStoneNum() {
        int actualBlackStoneNum = instance.getBlackStoneNum();

        verify(othelloBoardStatusStub, times(1))
                .getBlackStoneNum();

        assertThat(actualBlackStoneNum)
                .isEqualTo(blackStoneNum);
    }

    @Test
    public void getWhiteStoneNum() {
        int actualWhiteStoneNum = instance.getWhiteStoneNum();

        verify(othelloBoardStatusStub, times(1))
                .getWhiteStoneNum();

        assertThat(actualWhiteStoneNum)
                .isEqualTo(whiteStoneNum);
    }

    @Test
    public void getPlayer() {
        int actualPlayer = instance.getPlayer();

        verify(othelloBoardStatusStub, times(1))
                .getPlayer();

        assertThat(actualPlayer)
                .isEqualTo(player.getPlayer());

    }

    @Test
    public void isGameOverFlag() {
        boolean actualGameOverFlag = instance.isGameOverFlag();

        verify(othelloBoardStatusStub, times(1))
                .isGameOverFlag();

        assertThat(actualGameOverFlag)
                .isEqualTo(gameOverFlag);
    }

    @Test
    public void isRestartFlag() {
        boolean actualRestartFlag = instance.isRestartFlag();

        verify(othelloBoardStatusStub, times(1))
                .isRestartFlag();

        assertThat(actualRestartFlag)
                .isEqualTo(restartFlag);
    }

    @Test
    public void getWinner() {
        Winner actualWinner = instance.getWinner();

        verify(othelloGameResultStub, times(1))
                .getWinner();

        assertThat(actualWinner)
                .isEqualTo(winner);
    }

    @Test
    public void restartGame() {
        ArgumentCaptor<Boolean> booleanArgumentCaptor = ArgumentCaptor.forClass(Boolean.class);

        instance.restartGame();

        verify(othelloBoardStatusStub, times(1))
                .setRestartFlag(booleanArgumentCaptor.capture());
        boolean actualFlag = booleanArgumentCaptor.getValue();
        boolean expected = true;
        assertThat(actualFlag)
                .isEqualTo(expected);
    }

}