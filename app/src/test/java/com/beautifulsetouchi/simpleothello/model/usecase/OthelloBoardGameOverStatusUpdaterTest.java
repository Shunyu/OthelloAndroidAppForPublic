package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardGameOverStatusUpdater;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OthelloBoardGameOverStatusUpdaterTest {

    OthelloBoardStatus othelloBoardStatusStb;

    OthelloBoardGameOverStatusUpdater instance;

    @Before
    public void setUp() {
        othelloBoardStatusStb = mock(OthelloBoardStatus.class);
        doNothing()
                .when(othelloBoardStatusStb)
                .setGameOverFlag(anyBoolean());

        instance = new OthelloBoardGameOverStatusUpdater(
                othelloBoardStatusStb
        );
    }

    @Test
    public void updateOthelloBoardStatusToGameOver() {

        ArgumentCaptor<Boolean> booleanArgumentCaptor = ArgumentCaptor.forClass(Boolean.class);

        instance.updateOthelloBoardStatusToGameOver();

        verify(othelloBoardStatusStb, times(1))
                .setGameOverFlag(booleanArgumentCaptor.capture());

        boolean actual = booleanArgumentCaptor.getValue();
        boolean expected = true;

        assertThat(actual, is(expected));

    }

}