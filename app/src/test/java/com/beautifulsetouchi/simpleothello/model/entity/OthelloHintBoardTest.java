package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloHintBoard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.PUTABLE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(JUnit4.class)
public class OthelloHintBoardTest {

    @Test
    public void testOthelloHintBoardDefaultConstructor() {

        OthelloHintBoard instance = new OthelloHintBoard();

        int[][] actual = instance.getBoardArray();

        int[][] expected = {
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, 0, 0, 0, 0, 0, 0, 0, 0, -1},
                {-1, 0, 0, 0, 0, 0, 0, 0, 0, -1},
                {-1, 0, 0, 0, 0, 0, 0, 0, 0, -1},
                {-1, 0, 0, 0, 0, 0, 0, 0, 0, -1},
                {-1, 0, 0, 0, 0, 0, 0, 0, 0, -1},
                {-1, 0, 0, 0, 0, 0, 0, 0, 0, -1},
                {-1, 0, 0, 0, 0, 0, 0, 0, 0, -1},
                {-1, 0, 0, 0, 0, 0, 0, 0, 0, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
        };

        assertArrayEquals(expected, actual);
    }

    @Test
    public void getBoardStone_givenOthelloHintBoard_returnSpecificIntValue() {

        OthelloHintBoard othelloHintBoard = new OthelloHintBoard();

        BoardIndex boardIndex2And3 = new BoardIndex(2, 3);
        int actual2And3 = othelloHintBoard.getBoardStone(boardIndex2And3);
        int expected2And3 = PUTABLE;

        assertThat(actual2And3, is(expected2And3));

        BoardIndex boardIndex5And5 = new BoardIndex(5,5);
        int actual5And5 = othelloHintBoard.getBoardStone(boardIndex5And5);
        int expected5And5 = PUTABLE;

        assertThat(actual5And5, is(expected5And5));

    }

    @Test
    public void setBoardStone_givenOthelloHintBoardSpy_setSpecificArguments() {

        OthelloHintBoard othelloHintBoardSpy = spy(OthelloHintBoard.class);
        ArgumentCaptor<UpdatableBoardIndex> boardIndexCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        ArgumentCaptor<Player> playerCapture = ArgumentCaptor.forClass(Player.class);

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,1);
        Player expectedPlayer = Player.WHITE;

        othelloHintBoardSpy.setBoardStone(boardIndex, expectedPlayer);

        verify(othelloHintBoardSpy, times(1))
                .setBoardArrayOneElement(boardIndexCaptor.capture(), playerCapture.capture());

        UpdatableBoardIndex actualBoardIndex = boardIndexCaptor.getValue();
        assertEquals(boardIndex, actualBoardIndex);

        Player actualPlayer = playerCapture.getValue();
        assertThat(actualPlayer, is(expectedPlayer));

    }

    @Test
    public void setBoardStone_givenOthelloHintBoard_setSpecificIntValue() {
        OthelloHintBoard othelloHintBoard = new OthelloHintBoard();

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,1);
        Player expectedPlayer = Player.WHITE;

        othelloHintBoard.setBoardStone(boardIndex, expectedPlayer);
        int[][] boardArray = othelloHintBoard.getBoardArray();
        int actualPlayer = boardArray[1][1];

        assertThat(actualPlayer, is(expectedPlayer.getPlayer()));
    }

    @Test
    public void setBoardStones_givenOthelloHintBoardMock_setSpecificArguments() {

        OthelloHintBoard othelloHintBoardSpy = spy(OthelloHintBoard.class);
        ArgumentCaptor<UpdatableBoardIndex> boardIndexCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        ArgumentCaptor<Player> playerCapture = ArgumentCaptor.forClass(Player.class);

        List<UpdatableBoardIndex> boardIndexList = new ArrayList<>(
                Arrays.asList(
                        new UpdatableBoardIndex(2,2),
                        new UpdatableBoardIndex(2,3)
                )
        );
        Player expectedPlayer = Player.BLACK;
        othelloHintBoardSpy.setBoardStones(boardIndexList, expectedPlayer);

        verify(othelloHintBoardSpy, times(boardIndexList.size()))
                .setBoardArrayOneElement(boardIndexCaptor.capture(), playerCapture.capture());

        List<UpdatableBoardIndex> actualBoardIndexList = boardIndexCaptor.getAllValues();
        assertEquals(boardIndexList, actualBoardIndexList);

        List<Player> actualPlayer = playerCapture.getAllValues();
        for (int count = 0; count < boardIndexList.size(); count++){
            assertThat(actualPlayer.get(count), is(expectedPlayer));
        }

    }

    @Test
    public void setBoardStones_givenOthelloHintBoard_setSpecificIntValue() {
        OthelloHintBoard othelloHintBoard = new OthelloHintBoard();

        List<UpdatableBoardIndex> boardIndexList = new ArrayList<>(
                Arrays.asList(
                        new UpdatableBoardIndex(2,2),
                        new UpdatableBoardIndex(2,3)
                )
        );
        Player expectedPlayer = Player.BLACK;
        othelloHintBoard.setBoardStones(boardIndexList, expectedPlayer);

        int[][] boardArray = othelloHintBoard.getBoardArray();
        int actualPlayer22 = boardArray[2][2];
        int actualPlayer23 = boardArray[2][3];

        assertThat(actualPlayer22, is(expectedPlayer.getPlayer()));
        assertThat(actualPlayer23, is(expectedPlayer.getPlayer()));

    }

}
