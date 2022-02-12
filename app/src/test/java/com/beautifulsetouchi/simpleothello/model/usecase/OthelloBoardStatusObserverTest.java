package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardGameOverStatusUpdater;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardPlayerStatusUpdater;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardStatusObserver;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardStoneStatusUpdater;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloGameResultUpdater;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class OthelloBoardStatusObserverTest {

    OthelloBoardStoneStatusUpdater othelloBoardStoneStatusUpdaterStub;
    OthelloBoardPlayerStatusUpdater othelloBoardPlayerStatusUpdaterStub;
    OthelloBoardGameOverStatusUpdater othelloBoardGameOverStatusUpdaterStub;
    OthelloGameResultUpdater othelloGameResultUpdaterStub;

    OthelloBoardStatusObserver instance;

    @Before
    public void setUp() {
        othelloBoardStoneStatusUpdaterStub = mock(OthelloBoardStoneStatusUpdater.class);
        doNothing()
                .when(othelloBoardStoneStatusUpdaterStub)
                .updateOthelloBoardStoneStatus();

        othelloBoardPlayerStatusUpdaterStub = mock(OthelloBoardPlayerStatusUpdater.class);
        doNothing()
                .when(othelloBoardPlayerStatusUpdaterStub)
                .updateOthelloBoardPlayerStatus();

        othelloBoardGameOverStatusUpdaterStub = mock(OthelloBoardGameOverStatusUpdater.class);
        doNothing()
                .when(othelloBoardGameOverStatusUpdaterStub)
                .updateOthelloBoardStatusToGameOver();

        othelloGameResultUpdaterStub = mock(OthelloGameResultUpdater.class);
        doNothing()
                .when(othelloGameResultUpdaterStub)
                .updateOthelloGameResult();

        instance = new OthelloBoardStatusObserver(
                othelloBoardStoneStatusUpdaterStub,
                othelloBoardPlayerStatusUpdaterStub,
                othelloBoardGameOverStatusUpdaterStub,
                othelloGameResultUpdaterStub
        );
    }

    @Test
    public void updatePlayerStatus() {
        instance.updatePlayerStatus();

        verify(othelloBoardPlayerStatusUpdaterStub, times(1))
                .updateOthelloBoardPlayerStatus();
    }

    @Test
    public void updateOthelloBoardStatus() {
        instance.updateOthelloBoardStatus();

        verify(othelloBoardStoneStatusUpdaterStub, times(1))
                .updateOthelloBoardStoneStatus();

        verify(othelloBoardPlayerStatusUpdaterStub, times(1))
                .updateOthelloBoardPlayerStatus();
    }

    @Test
    public void updateOthelloBoardStatusToGameOver() {
        instance.updateOthelloBoardStatusToGameOver();

        verify(othelloBoardStoneStatusUpdaterStub, times(1))
                .updateOthelloBoardStoneStatus();

        verify(othelloBoardGameOverStatusUpdaterStub, times(1))
                .updateOthelloBoardStatusToGameOver();

        verify(othelloGameResultUpdaterStub, times(1))
                .updateOthelloGameResult();
    }

}