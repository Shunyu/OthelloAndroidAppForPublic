package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloBoardSpecificIndexListTurnOverExecutorTest {

    OthelloBoard othelloBoardStub;
    OthelloBoardStatus othelloBoardStatusStub;
    OthelloBoardSpecificIndexListTurnOverExecutor instance;

    @Before
    public void setUp() {
        othelloBoardStub = mock(OthelloBoard.class);
        doNothing()
                .when(othelloBoardStub)
                .setBoardStone(any(), any());

        othelloBoardStatusStub = mock(OthelloBoardStatus.class);
        when(othelloBoardStatusStub.getPlayer())
                .thenReturn(Player.BLACK);

        instance = new OthelloBoardSpecificIndexListTurnOverExecutor(
                othelloBoardStub,
                othelloBoardStatusStub
        );

    }

    @Test
    public void executeTurnOverStone_giveSpecificArgumentsForStub() {
        ArgumentCaptor<List<UpdatableBoardIndex>> boardIndexListCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);

        List<UpdatableBoardIndex> givenBoardIndexList = new ArrayList<>(Arrays.asList(
                new UpdatableBoardIndex(2,2),
                new UpdatableBoardIndex(2,3)
        ));
        instance.executeTurnOverStones(givenBoardIndexList);

        verify(othelloBoardStatusStub, times(1))
                .getPlayer();
        verify(othelloBoardStub, times(1))
                .setBoardStones(
                        boardIndexListCaptor.capture(),
                        playerCaptor.capture()
                );

        List<UpdatableBoardIndex> actualBoardIndex = boardIndexListCaptor.getValue();
        assertEquals(givenBoardIndexList, actualBoardIndex);

        Player actualPlayer = playerCaptor.getValue();
        assertThat(actualPlayer, is(Player.BLACK));

    }


}