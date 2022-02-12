package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloGameUpdater;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloRule;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloTaskManager;

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

public class OthelloTaskManagerTest {

    public static class LegalMoveFlagIsTrue {

        OthelloRule othelloRuleStub;
        OthelloGameUpdater othelloGameUpdaterStub;

        OthelloTaskManager instance;

        private UpdatableBoardIndex boardIndex;

        @Before
        public void setUp() {

            othelloRuleStub = mock(OthelloRule.class);
            when(othelloRuleStub.isLegalMove(any()))
                    .thenReturn(true);

            othelloGameUpdaterStub = mock(OthelloGameUpdater.class);
            doNothing()
                    .when(othelloGameUpdaterStub)
                    .updateGame(any());

            instance = new OthelloTaskManager(
                    othelloRuleStub,
                    othelloGameUpdaterStub
            );

        }

        @Test
        public void isGameUpdate_returnTrue() {

            boardIndex = new UpdatableBoardIndex(2,2);
            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

            boolean actualFlag = instance.isGameUpdate(boardIndex);

            verify(othelloRuleStub, times(1))
                    .isLegalMove(boardIndexArgumentCaptor.capture());
            UpdatableBoardIndex actualBoardIndexAtIsLegalMove = boardIndexArgumentCaptor.getValue();
            assertEquals(boardIndex, actualBoardIndexAtIsLegalMove);

            verify(othelloGameUpdaterStub, times(1))
                    .updateGame(boardIndexArgumentCaptor.capture());
            UpdatableBoardIndex actualBoardIndexAtUpdateGame = boardIndexArgumentCaptor.getValue();
            assertEquals(boardIndex, actualBoardIndexAtUpdateGame);

            boolean expectedFlag = true;
            assertThat(actualFlag, is(expectedFlag));
        }

    }

    public static class LegalMoveFlagIsFalse {

        OthelloRule othelloRuleStub;
        OthelloGameUpdater othelloGameUpdaterStub;

        OthelloTaskManager instance;

        private UpdatableBoardIndex boardIndex;

        @Before
        public void setUp() {

            othelloRuleStub = mock(OthelloRule.class);
            when(othelloRuleStub.isLegalMove(any()))
                    .thenReturn(false);

            othelloGameUpdaterStub = mock(OthelloGameUpdater.class);
            doNothing()
                    .when(othelloGameUpdaterStub)
                    .updateGame(any());

            instance = new OthelloTaskManager(
                    othelloRuleStub,
                    othelloGameUpdaterStub
            );

        }

        @Test
        public void isGameUpdate_returnTrue() {

            boardIndex = new UpdatableBoardIndex(2,2);
            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

            boolean actualFlag = instance.isGameUpdate(boardIndex);

            verify(othelloRuleStub, times(1))
                    .isLegalMove(boardIndexArgumentCaptor.capture());
            UpdatableBoardIndex actualBoardIndexAtIsLegalMove = boardIndexArgumentCaptor.getValue();
            assertEquals(boardIndex, actualBoardIndexAtIsLegalMove);

            verify(othelloGameUpdaterStub, times(0))
                    .updateGame(any());

            boolean expectedFlag = false;
            assertThat(actualFlag, is(expectedFlag));
        }

    }


}