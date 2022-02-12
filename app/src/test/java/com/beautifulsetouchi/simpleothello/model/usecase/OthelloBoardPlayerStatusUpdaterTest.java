package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardPlayerStatusUpdater;

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

public class OthelloBoardPlayerStatusUpdaterTest {

    OthelloBoardStatus othelloBoardStatusStub;

    OthelloBoardPlayerStatusUpdater instance;

    private Player nextPlayer;

    @Before
    public void setUp() {
        nextPlayer = Player.BLACK;

        othelloBoardStatusStub = mock(OthelloBoardStatus.class);
        when(othelloBoardStatusStub.getOpponentPlayer())
                .thenReturn(nextPlayer);
        doNothing()
                .when(othelloBoardStatusStub)
                .setPlayer(any());

        instance = new OthelloBoardPlayerStatusUpdater(
                othelloBoardStatusStub
        );
    }

    @Test
    public void updateOthelloBoardPlayerStatus() {

        ArgumentCaptor<Player> playerArgumentCaptor = ArgumentCaptor.forClass(Player.class);

        instance.updateOthelloBoardPlayerStatus();

        verify(othelloBoardStatusStub, times(1))
                .getOpponentPlayer();

        verify(othelloBoardStatusStub, times(1))
                .setPlayer(playerArgumentCaptor.capture());
        Player actualNextPlayer = playerArgumentCaptor.getValue();
        assertThat(actualNextPlayer, is(nextPlayer));

    }

}