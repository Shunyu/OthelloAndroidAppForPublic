package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloHintBoard;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class OthelloNextMoveCalculatorTest {

    public static class AllLegalMoveFlagsAreFalse {

        OthelloBoardStatus othelloBoardStatusStub;
        OthelloRule othelloRuleStub;
        OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGeneratorStub;

        OthelloNextMoveCalculator instance;

        private List<UpdatableBoardIndex> boardIndexList;
        private Player player;

        @Before
        public void setUp() {

            boardIndexList = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloAllUpdatableBoardIndexListGeneratorStub = mock(OthelloAllUpdatableBoardIndexListGenerator.class);
            when(othelloAllUpdatableBoardIndexListGeneratorStub.getAllUpdatableBoardIndexList())
                    .thenReturn(boardIndexList);

            player = Player.BLACK;
            othelloBoardStatusStub = mock(OthelloBoardStatus.class);
            when(othelloBoardStatusStub.getPlayer())
                    .thenReturn(player);

            othelloRuleStub = mock(OthelloRule.class);
            when(othelloRuleStub.isLegalMove(any()))
                    .thenReturn(false);

            instance = new OthelloNextMoveCalculator(
                    othelloBoardStatusStub,
                    othelloRuleStub,
                    othelloAllUpdatableBoardIndexListGeneratorStub
            );
        }

        @Test
        public void getOthelloHintBoard() {

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

            OthelloHintBoard actual = instance.calculateOthelloHintBoard();

            verify(othelloAllUpdatableBoardIndexListGeneratorStub, times(1))
                    .getAllUpdatableBoardIndexList();

            verify(othelloBoardStatusStub, times(1))
                    .getPlayer();

            verify(othelloRuleStub, times(boardIndexList.size()))
                    .isLegalMove(boardIndexArgumentCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
            assertEquals(boardIndexList, actualBoardIndexList);

            OthelloHintBoard expected = new OthelloHintBoard();
            assertEquals(expected, actual);

        }

    }

    public static class LegalMoveFlagIsTrueInFirstTime {

        OthelloBoardStatus othelloBoardStatusStub;
        OthelloRule othelloRuleStub;
        OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGeneratorStub;

        OthelloNextMoveCalculator instance;

        private List<UpdatableBoardIndex> boardIndexList;
        private Player player;

        @Before
        public void setUp() {

            boardIndexList = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloAllUpdatableBoardIndexListGeneratorStub = mock(OthelloAllUpdatableBoardIndexListGenerator.class);
            when(othelloAllUpdatableBoardIndexListGeneratorStub.getAllUpdatableBoardIndexList())
                    .thenReturn(boardIndexList);

            player = Player.BLACK;
            othelloBoardStatusStub = mock(OthelloBoardStatus.class);
            when(othelloBoardStatusStub.getPlayer())
                    .thenReturn(player);

            othelloRuleStub = mock(OthelloRule.class);
            when(othelloRuleStub.isLegalMove(any()))
                    .thenReturn(false);
            when(othelloRuleStub.isLegalMove(eq(new UpdatableBoardIndex(2,3))))
                    .thenReturn(true);

            instance = new OthelloNextMoveCalculator(
                    othelloBoardStatusStub,
                    othelloRuleStub,
                    othelloAllUpdatableBoardIndexListGeneratorStub
            );
        }

        @Test
        public void getOthelloHintBoard() {

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

            OthelloHintBoard actual = instance.calculateOthelloHintBoard();

            verify(othelloAllUpdatableBoardIndexListGeneratorStub, times(1))
                    .getAllUpdatableBoardIndexList();

            verify(othelloBoardStatusStub, times(1))
                    .getPlayer();

            verify(othelloRuleStub, times(boardIndexList.size()))
                    .isLegalMove(boardIndexArgumentCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
            assertEquals(boardIndexList, actualBoardIndexList);

            OthelloHintBoard expected = new OthelloHintBoard();
//            expected.boardArray[2][3] = player.getPlayer();
            expected.setBoardArrayOneElement(new UpdatableBoardIndex(2,3), player);
            assertEquals(expected, actual);

        }

    }

    public static class AllLegalMoveFlagsAreTrue {

        OthelloBoardStatus othelloBoardStatusStub;
        OthelloRule othelloRuleStub;
        OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGeneratorStub;

        OthelloNextMoveCalculator instance;

        private List<UpdatableBoardIndex> boardIndexList;
        private Player player;

        @Before
        public void setUp() {

            boardIndexList = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloAllUpdatableBoardIndexListGeneratorStub = mock(OthelloAllUpdatableBoardIndexListGenerator.class);
            when(othelloAllUpdatableBoardIndexListGeneratorStub.getAllUpdatableBoardIndexList())
                    .thenReturn(boardIndexList);

            player = Player.BLACK;
            othelloBoardStatusStub = mock(OthelloBoardStatus.class);
            when(othelloBoardStatusStub.getPlayer())
                    .thenReturn(player);

            othelloRuleStub = mock(OthelloRule.class);
            when(othelloRuleStub.isLegalMove(any()))
                    .thenReturn(true);

            instance = new OthelloNextMoveCalculator(
                    othelloBoardStatusStub,
                    othelloRuleStub,
                    othelloAllUpdatableBoardIndexListGeneratorStub
            );
        }

        @Test
        public void getOthelloHintBoard() {

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);

            OthelloHintBoard actual = instance.calculateOthelloHintBoard();

            verify(othelloAllUpdatableBoardIndexListGeneratorStub, times(1))
                    .getAllUpdatableBoardIndexList();

            verify(othelloBoardStatusStub, times(1))
                    .getPlayer();

            verify(othelloRuleStub, times(boardIndexList.size()))
                    .isLegalMove(boardIndexArgumentCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
            assertEquals(boardIndexList, actualBoardIndexList);

            OthelloHintBoard expected = new OthelloHintBoard();
//            expected.boardArray[2][2] = player.getPlayer();
//            expected.boardArray[2][3] = player.getPlayer();
            expected.setBoardArrayOneElement(new UpdatableBoardIndex(2,2), player);
            expected.setBoardArrayOneElement(new UpdatableBoardIndex(2,3), player);
            assertEquals(expected, actual);

        }

    }
}