package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStoneNum;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.BLACK;
import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.PUTABLE;
import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.WHITE;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class OthelloBoardStoneCounterTest {

    public static class AllBoardIndexAreNoStone {

        OthelloBoard othelloBoardStub;
        OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGeneratorStub;

        OthelloBoardStoneCounter instance;

        private List<UpdatableBoardIndex> boardIndexList;

        @Before
        public void setUp(){

            boardIndexList = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloAllUpdatableBoardIndexListGeneratorStub = mock(OthelloAllUpdatableBoardIndexListGenerator.class);
            when(othelloAllUpdatableBoardIndexListGeneratorStub.getAllUpdatableBoardIndexList())
                    .thenReturn(boardIndexList);

            othelloBoardStub = mock(OthelloBoard.class);
            when(othelloBoardStub.getBoardStone(any()))
                    .thenReturn(PUTABLE);

            instance = new OthelloBoardStoneCounter(
                    othelloBoardStub,
                    othelloAllUpdatableBoardIndexListGeneratorStub
            );
        }

        @Test
        public void countStoneNum() {

            OthelloBoardStoneNum actual = instance.countStoneNum();

            verify(othelloAllUpdatableBoardIndexListGeneratorStub, times(1))
                    .getAllUpdatableBoardIndexList();

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            verify(othelloBoardStub, times(boardIndexList.size()))
                    .getBoardStone(boardIndexArgumentCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
            assertEquals(boardIndexList, actualBoardIndexList);

            OthelloBoardStoneNum expected = new OthelloBoardStoneNum();
            expected.setBlackStoneNum(0);
            expected.setWhiteStoneNum(0);

            assertEquals(expected, actual);
        }
    }

    public static class FirstBoardIndexIsBlack {

        OthelloBoard othelloBoardStub;
        OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGeneratorStub;

        OthelloBoardStoneCounter instance;

        private List<UpdatableBoardIndex> boardIndexList;

        @Before
        public void setUp(){

            boardIndexList = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloAllUpdatableBoardIndexListGeneratorStub = mock(OthelloAllUpdatableBoardIndexListGenerator.class);
            when(othelloAllUpdatableBoardIndexListGeneratorStub.getAllUpdatableBoardIndexList())
                    .thenReturn(boardIndexList);

            othelloBoardStub = mock(OthelloBoard.class);
            when(othelloBoardStub.getBoardStone(any()))
                    .thenReturn(PUTABLE);
            when(othelloBoardStub.getBoardStone(eq(new UpdatableBoardIndex(2,2))))
                    .thenReturn(BLACK);

            instance = new OthelloBoardStoneCounter(
                    othelloBoardStub,
                    othelloAllUpdatableBoardIndexListGeneratorStub
            );
        }

        @Test
        public void countStoneNum() {

            OthelloBoardStoneNum actual = instance.countStoneNum();

            verify(othelloAllUpdatableBoardIndexListGeneratorStub, times(1))
                    .getAllUpdatableBoardIndexList();

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            verify(othelloBoardStub, times(boardIndexList.size()))
                    .getBoardStone(boardIndexArgumentCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
            assertEquals(boardIndexList, actualBoardIndexList);

            OthelloBoardStoneNum expected = new OthelloBoardStoneNum();
            expected.setBlackStoneNum(1);
            expected.setWhiteStoneNum(0);

            assertEquals(expected, actual);
//            assertEquals(expected.getBlackStoneNum(), actual.getBlackStoneNum());
//            assertEquals(expected.getWhiteStoneNum(), actual.getWhiteStoneNum());
        }
    }

    public static class SecondBoardIndexIsBlack {

        OthelloBoard othelloBoardStub;
        OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGeneratorStub;

        OthelloBoardStoneCounter instance;

        private List<UpdatableBoardIndex> boardIndexList;

        @Before
        public void setUp(){

            boardIndexList = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloAllUpdatableBoardIndexListGeneratorStub = mock(OthelloAllUpdatableBoardIndexListGenerator.class);
            when(othelloAllUpdatableBoardIndexListGeneratorStub.getAllUpdatableBoardIndexList())
                    .thenReturn(boardIndexList);

            othelloBoardStub = mock(OthelloBoard.class);
            when(othelloBoardStub.getBoardStone(any()))
                    .thenReturn(PUTABLE);
            when(othelloBoardStub.getBoardStone(eq(new UpdatableBoardIndex(2,3))))
                    .thenReturn(BLACK);

            instance = new OthelloBoardStoneCounter(
                    othelloBoardStub,
                    othelloAllUpdatableBoardIndexListGeneratorStub
            );
        }

        @Test
        public void countStoneNum() {

            OthelloBoardStoneNum actual = instance.countStoneNum();

            verify(othelloAllUpdatableBoardIndexListGeneratorStub, times(1))
                    .getAllUpdatableBoardIndexList();

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            verify(othelloBoardStub, times(boardIndexList.size()))
                    .getBoardStone(boardIndexArgumentCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
            assertEquals(boardIndexList, actualBoardIndexList);

            OthelloBoardStoneNum expected = new OthelloBoardStoneNum();
            expected.setBlackStoneNum(1);
            expected.setWhiteStoneNum(0);

            assertEquals(expected, actual);
        }
    }

    public static class BothBoardIndexIsBlack {

        OthelloBoard othelloBoardStub;
        OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGeneratorStub;

        OthelloBoardStoneCounter instance;

        private List<UpdatableBoardIndex> boardIndexList;

        @Before
        public void setUp(){

            boardIndexList = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloAllUpdatableBoardIndexListGeneratorStub = mock(OthelloAllUpdatableBoardIndexListGenerator.class);
            when(othelloAllUpdatableBoardIndexListGeneratorStub.getAllUpdatableBoardIndexList())
                    .thenReturn(boardIndexList);

            othelloBoardStub = mock(OthelloBoard.class);
            when(othelloBoardStub.getBoardStone(any()))
                    .thenReturn(BLACK);

            instance = new OthelloBoardStoneCounter(
                    othelloBoardStub,
                    othelloAllUpdatableBoardIndexListGeneratorStub
            );
        }

        @Test
        public void countStoneNum() {

            OthelloBoardStoneNum actual = instance.countStoneNum();

            verify(othelloAllUpdatableBoardIndexListGeneratorStub, times(1))
                    .getAllUpdatableBoardIndexList();

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            verify(othelloBoardStub, times(boardIndexList.size()))
                    .getBoardStone(boardIndexArgumentCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
            assertEquals(boardIndexList, actualBoardIndexList);

            OthelloBoardStoneNum expected = new OthelloBoardStoneNum();
            expected.setBlackStoneNum(2);
            expected.setWhiteStoneNum(0);

            assertEquals(expected, actual);
        }
    }

    public static class FirstBoardIndexIsWhite {

        OthelloBoard othelloBoardStub;
        OthelloAllUpdatableBoardIndexListGenerator othelloAllUpdatableBoardIndexListGeneratorStub;

        OthelloBoardStoneCounter instance;

        private List<UpdatableBoardIndex> boardIndexList;

        @Before
        public void setUp(){

            boardIndexList = new ArrayList<>(
                    Arrays.asList(
                            new UpdatableBoardIndex(2,2),
                            new UpdatableBoardIndex(2,3)
                    )
            );
            othelloAllUpdatableBoardIndexListGeneratorStub = mock(OthelloAllUpdatableBoardIndexListGenerator.class);
            when(othelloAllUpdatableBoardIndexListGeneratorStub.getAllUpdatableBoardIndexList())
                    .thenReturn(boardIndexList);

            othelloBoardStub = mock(OthelloBoard.class);
            when(othelloBoardStub.getBoardStone(any()))
                    .thenReturn(PUTABLE);
            when(othelloBoardStub.getBoardStone(eq(new UpdatableBoardIndex(2,2))))
                    .thenReturn(WHITE);

            instance = new OthelloBoardStoneCounter(
                    othelloBoardStub,
                    othelloAllUpdatableBoardIndexListGeneratorStub
            );
        }

        @Test
        public void countStoneNum() {

            OthelloBoardStoneNum actual = instance.countStoneNum();

            verify(othelloAllUpdatableBoardIndexListGeneratorStub, times(1))
                    .getAllUpdatableBoardIndexList();

            ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
            verify(othelloBoardStub, times(boardIndexList.size()))
                    .getBoardStone(boardIndexArgumentCaptor.capture());
            List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
            assertEquals(boardIndexList, actualBoardIndexList);

            OthelloBoardStoneNum expected = new OthelloBoardStoneNum();
            expected.setBlackStoneNum(0);
            expected.setWhiteStoneNum(1);

            assertEquals(expected, actual);
        }
    }

}