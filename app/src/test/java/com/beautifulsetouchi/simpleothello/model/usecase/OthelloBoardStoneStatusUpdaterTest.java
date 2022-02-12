package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStoneNum;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardStoneCounter;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardStoneStatusUpdater;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloBoardStoneStatusUpdaterTest {

    OthelloBoardStoneCounter othelloBoardStoneCounterStub;
    OthelloBoardStatus othelloBoardStatusStub;

    OthelloBoardStoneStatusUpdater instance;

    private OthelloBoardStoneNum othelloBoardStoneNum;

    @Before
    public void setUp() {

        othelloBoardStoneNum = new OthelloBoardStoneNum();
        othelloBoardStoneNum.setBlackStoneNum(2);
        othelloBoardStoneNum.setWhiteStoneNum(1);

        othelloBoardStoneCounterStub = mock(OthelloBoardStoneCounter.class);
        when(othelloBoardStoneCounterStub.countStoneNum())
                .thenReturn(othelloBoardStoneNum);

        othelloBoardStatusStub = mock(OthelloBoardStatus.class);
        doNothing()
                .when(othelloBoardStatusStub)
                .setOthelloBoardStoneNum(any());

        instance = new OthelloBoardStoneStatusUpdater(
                othelloBoardStoneCounterStub,
                othelloBoardStatusStub
        );
    }

    @Test
    public void updateOthelloBoardStoneStatus() {

        ArgumentCaptor<OthelloBoardStoneNum> othelloBoardStoneNumArgumentCaptor = ArgumentCaptor.forClass(OthelloBoardStoneNum.class);

        instance.updateOthelloBoardStoneStatus();

        verify(othelloBoardStoneCounterStub, times(1))
                .countStoneNum();

        verify(othelloBoardStatusStub, times(1))
                .setOthelloBoardStoneNum(
                        othelloBoardStoneNumArgumentCaptor.capture()
                );
        OthelloBoardStoneNum actualOthelloBoardStoneNum = othelloBoardStoneNumArgumentCaptor.getValue();
        assertEquals(othelloBoardStoneNum, actualOthelloBoardStoneNum);
    }
}