package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloBoardSpecificIndexTurnOverExecutorTest {

    OthelloBoard othelloBoardStub;
    OthelloBoardStatus othelloBoardStatusStub;
    OthelloBoardSpecificIndexTurnOverExecutor instance;

    @Before
    public void setUp() {
        othelloBoardStub = mock(OthelloBoard.class);
        doNothing()
                .when(othelloBoardStub)
                .setBoardStone(any(), any());

        othelloBoardStatusStub = mock(OthelloBoardStatus.class);
        when(othelloBoardStatusStub.getPlayer())
                .thenReturn(Player.BLACK);

        instance = new OthelloBoardSpecificIndexTurnOverExecutor(
                othelloBoardStub,
                othelloBoardStatusStub
        );

    }

    @Test
    public void executeTurnOverStone_giveSpecificArgumentsForStub() {
        ArgumentCaptor<UpdatableBoardIndex> boardIndexCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);

        UpdatableBoardIndex givenBoardIndex = new UpdatableBoardIndex(2,2);
        instance.executeTurnOverStone(givenBoardIndex);

        verify(othelloBoardStatusStub, times(1))
                .getPlayer();
        verify(othelloBoardStub, times(1))
                .setBoardStone(
                        boardIndexCaptor.capture(),
                        playerCaptor.capture()
                );

        UpdatableBoardIndex actualBoardIndex = boardIndexCaptor.getValue();
        assertThat(actualBoardIndex, is(givenBoardIndex));

        Player actualPlayer = playerCaptor.getValue();
        assertThat(actualPlayer, is(Player.BLACK));

    }


}