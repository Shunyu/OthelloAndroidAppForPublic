package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardStatusObserver;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloGameUpdater;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloRule;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class OthelloGameUpdaterTest {

    public static class LegalMoveExistsFlagAtFirstTimeIsTrue {

        OthelloRule othelloRuleStub;
        OthelloBoardStatusObserver othelloBoardStatusObserverStub;

        OthelloGameUpdater instance;

        private UpdatableBoardIndex boardIndex;

        @Before
        public void setUp() {

            othelloRuleStub = mock(OthelloRule.class);
            doNothing()
                    .when(othelloRuleStub)
                    .turnOverStones(any());
            when(othelloRuleStub.legalMoveExists())
                    .thenReturn(true);

            othelloBoardStatusObserverStub = mock(OthelloBoardStatusObserver.class);
            doNothing()
                    .when(othelloBoardStatusObserverStub)
                    .updateOthelloBoardStatus();
            doNothing()
                    .when(othelloBoardStatusObserverStub)
                    .updatePlayerStatus();
            doNothing()
                    .when(othelloBoardStatusObserverStub)
                    .updateOthelloBoardStatusToGameOver();

            instance = spy(new OthelloGameUpdater(
                    othelloRuleStub,
                    othelloBoardStatusObserverStub
            ));

        }

        @Test
        public void updateGame() {
            boardIndex = new UpdatableBoardIndex(2,2);
            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

            instance.updateGame(boardIndex);

            verify(othelloRuleStub, times(1))
                    .turnOverStones(boardIndexArgumentCaptor.capture());
            UpdatableBoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();
            assertEquals(boardIndex, actualBoardIndex);

            verify(instance, times(1))
                    .notifyOthelloBoardForOthelloBoardStatusObserver();

            verify(othelloBoardStatusObserverStub, times(1))
                    .updateOthelloBoardStatus();

            verify(othelloRuleStub, times(1))
                    .legalMoveExists();

            verify(instance, times(0))
                    .notifyPlayerSwappingForOthelloBoardStatusObserver();

            verify(othelloBoardStatusObserverStub, times(0))
                    .updatePlayerStatus();

            verify(instance, times(0))
                    .notifyGameOverForOthelloBoardStatusObserver();

            verify(othelloBoardStatusObserverStub, times(0))
                    .updateOthelloBoardStatusToGameOver();

        }

    }

    public static class LegalMoveExistsFlagAtFirstTimeIsFalseAndAtSecondTimeIsTrue {

        OthelloRule othelloRuleStub;
        OthelloBoardStatusObserver othelloBoardStatusObserverStub;

        OthelloGameUpdater instance;

        private UpdatableBoardIndex boardIndex;

        @Before
        public void setUp() {

            othelloRuleStub = mock(OthelloRule.class);
            doNothing()
                    .when(othelloRuleStub)
                    .turnOverStones(any());
            when(othelloRuleStub.legalMoveExists())
                    .thenReturn(false)
                    .thenReturn(true);

            othelloBoardStatusObserverStub = mock(OthelloBoardStatusObserver.class);
            doNothing()
                    .when(othelloBoardStatusObserverStub)
                    .updateOthelloBoardStatus();
            doNothing()
                    .when(othelloBoardStatusObserverStub)
                    .updatePlayerStatus();
            doNothing()
                    .when(othelloBoardStatusObserverStub)
                    .updateOthelloBoardStatusToGameOver();

            instance = spy(new OthelloGameUpdater(
                    othelloRuleStub,
                    othelloBoardStatusObserverStub
            ));
        }

        @Test
        public void updateGame() {
            boardIndex = new UpdatableBoardIndex(2,2);
            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

            instance.updateGame(boardIndex);

            verify(othelloRuleStub, times(1))
                    .turnOverStones(boardIndexArgumentCaptor.capture());
            UpdatableBoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();
            assertEquals(boardIndex, actualBoardIndex);

            verify(instance, times(1))
                    .notifyOthelloBoardForOthelloBoardStatusObserver();

            verify(othelloBoardStatusObserverStub, times(1))
                    .updateOthelloBoardStatus();

            verify(othelloRuleStub, times(2))
                    .legalMoveExists();

            verify(instance, times(1))
                    .notifyPlayerSwappingForOthelloBoardStatusObserver();

            verify(othelloBoardStatusObserverStub, times(1))
                    .updatePlayerStatus();

            verify(instance, times(0))
                    .notifyGameOverForOthelloBoardStatusObserver();

            verify(othelloBoardStatusObserverStub, times(0))
                    .updateOthelloBoardStatusToGameOver();
        }

    }

    public static class LegalMoveExistsFlagAtFirstTimeIsFalseAndAtSecondTimeIsFalse {

        OthelloRule othelloRuleStub;
        OthelloBoardStatusObserver othelloBoardStatusObserverStub;

        OthelloGameUpdater instance;

        private UpdatableBoardIndex boardIndex;

        @Before
        public void setUp() {

            othelloRuleStub = mock(OthelloRule.class);
            doNothing()
                    .when(othelloRuleStub)
                    .turnOverStones(any());
            when(othelloRuleStub.legalMoveExists())
                    .thenReturn(false)
                    .thenReturn(false);

            othelloBoardStatusObserverStub = mock(OthelloBoardStatusObserver.class);
            doNothing()
                    .when(othelloBoardStatusObserverStub)
                    .updateOthelloBoardStatus();
            doNothing()
                    .when(othelloBoardStatusObserverStub)
                    .updatePlayerStatus();
            doNothing()
                    .when(othelloBoardStatusObserverStub)
                    .updateOthelloBoardStatusToGameOver();

            instance = spy(new OthelloGameUpdater(
                    othelloRuleStub,
                    othelloBoardStatusObserverStub
            ));
        }

        @Test
        public void updateGame() {
            boardIndex = new UpdatableBoardIndex(2,2);
            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

            instance.updateGame(boardIndex);

            verify(othelloRuleStub, times(1))
                    .turnOverStones(boardIndexArgumentCaptor.capture());
            UpdatableBoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();
            assertEquals(boardIndex, actualBoardIndex);

            verify(instance, times(1))
                    .notifyOthelloBoardForOthelloBoardStatusObserver();

            verify(othelloBoardStatusObserverStub, times(1))
                    .updateOthelloBoardStatus();

            verify(othelloRuleStub, times(2))
                    .legalMoveExists();

            verify(instance, times(1))
                    .notifyPlayerSwappingForOthelloBoardStatusObserver();

            verify(othelloBoardStatusObserverStub, times(1))
                    .updatePlayerStatus();

            verify(instance, times(1))
                    .notifyGameOverForOthelloBoardStatusObserver();

            verify(othelloBoardStatusObserverStub, times(1))
                    .updateOthelloBoardStatusToGameOver();
        }

    }
}