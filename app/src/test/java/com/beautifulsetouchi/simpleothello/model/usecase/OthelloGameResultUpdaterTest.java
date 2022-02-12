package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Winner;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloGameResult;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloGameResultUpdater;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

//import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.BLACK;
//import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.DRAW;
//import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.WHITE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class OthelloGameResultUpdaterTest {

    public static class WinnerIsBlack{

        OthelloBoardStatus othelloBoardStatusStub;
        OthelloGameResult othelloGameResultStub;

        OthelloGameResultUpdater instance;

        private int blackStoneNum;
        private int whiteStoneNum;

        @Before
        public void setUp() {

            blackStoneNum = 10;
            whiteStoneNum = 9;

            othelloBoardStatusStub = mock(OthelloBoardStatus.class);
            when(othelloBoardStatusStub.getBlackStoneNum())
                    .thenReturn(blackStoneNum);
            when(othelloBoardStatusStub.getWhiteStoneNum())
                    .thenReturn(whiteStoneNum);

            othelloGameResultStub = mock(OthelloGameResult.class);
            doNothing()
                    .when(othelloGameResultStub)
                    .setBlackStoneNum(anyInt());
            doNothing()
                    .when(othelloGameResultStub)
                    .setWhiteStoneNum(anyInt());
            doNothing()
                    .when(othelloGameResultStub)
                    .setWinner(any());

            instance = new OthelloGameResultUpdater(
                    othelloBoardStatusStub,
                    othelloGameResultStub
            );

        }

        @Test
        public void updateOthelloGameResult() {

            ArgumentCaptor<Integer> blackStoneNumArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Integer> whiteStoneNumArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Winner> winnerArgumentCaptor = ArgumentCaptor.forClass(Winner.class);

            instance.updateOthelloGameResult();

            verify(othelloBoardStatusStub, times(1))
                    .getBlackStoneNum();
            verify(othelloBoardStatusStub, times(1))
                    .getWhiteStoneNum();

            verify(othelloGameResultStub, times(1))
                    .setBlackStoneNum(blackStoneNumArgumentCaptor.capture());
            int actualBlackStoneNum = blackStoneNumArgumentCaptor.getValue();
            assertThat(actualBlackStoneNum, is(blackStoneNum));

            verify(othelloGameResultStub, times(1))
                    .setWhiteStoneNum(whiteStoneNumArgumentCaptor.capture());
            int actualWhiteStoneNum = whiteStoneNumArgumentCaptor.getValue();
            assertThat(actualWhiteStoneNum, is(whiteStoneNum));

            Winner expectedWinner = Winner.BLACK;
            verify(othelloGameResultStub, times(1))
                    .setWinner(winnerArgumentCaptor.capture());
            Winner actualWinner = winnerArgumentCaptor.getValue();
            assertThat(actualWinner, is(expectedWinner));

        }

    }

    public static class WinnerIsWhite{

        OthelloBoardStatus othelloBoardStatusStub;
        OthelloGameResult othelloGameResultStub;

        OthelloGameResultUpdater instance;

        private int blackStoneNum;
        private int whiteStoneNum;

        @Before
        public void setUp() {

            blackStoneNum = 9;
            whiteStoneNum = 10;

            othelloBoardStatusStub = mock(OthelloBoardStatus.class);
            when(othelloBoardStatusStub.getBlackStoneNum())
                    .thenReturn(blackStoneNum);
            when(othelloBoardStatusStub.getWhiteStoneNum())
                    .thenReturn(whiteStoneNum);

            othelloGameResultStub = mock(OthelloGameResult.class);
            doNothing()
                    .when(othelloGameResultStub)
                    .setBlackStoneNum(anyInt());
            doNothing()
                    .when(othelloGameResultStub)
                    .setWhiteStoneNum(anyInt());
            doNothing()
                    .when(othelloGameResultStub)
                    .setWinner(any());

            instance = new OthelloGameResultUpdater(
                    othelloBoardStatusStub,
                    othelloGameResultStub
            );

        }

        @Test
        public void updateOthelloGameResult() {

            ArgumentCaptor<Integer> blackStoneNumArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Integer> whiteStoneNumArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Winner> winnerArgumentCaptor = ArgumentCaptor.forClass(Winner.class);

            instance.updateOthelloGameResult();

            verify(othelloBoardStatusStub, times(1))
                    .getBlackStoneNum();
            verify(othelloBoardStatusStub, times(1))
                    .getWhiteStoneNum();

            verify(othelloGameResultStub, times(1))
                    .setBlackStoneNum(blackStoneNumArgumentCaptor.capture());
            int actualBlackStoneNum = blackStoneNumArgumentCaptor.getValue();
            assertThat(actualBlackStoneNum, is(blackStoneNum));

            verify(othelloGameResultStub, times(1))
                    .setWhiteStoneNum(whiteStoneNumArgumentCaptor.capture());
            int actualWhiteStoneNum = whiteStoneNumArgumentCaptor.getValue();
            assertThat(actualWhiteStoneNum, is(whiteStoneNum));

            Winner expectedWinner = Winner.WHITE;
            verify(othelloGameResultStub, times(1))
                    .setWinner(winnerArgumentCaptor.capture());
            Winner actualWinner = winnerArgumentCaptor.getValue();
            assertThat(actualWinner, is(expectedWinner));

        }

    }

    public static class WinnerIsDraw {

        OthelloBoardStatus othelloBoardStatusStub;
        OthelloGameResult othelloGameResultStub;

        OthelloGameResultUpdater instance;

        private int blackStoneNum;
        private int whiteStoneNum;

        @Before
        public void setUp() {

            blackStoneNum = 10;
            whiteStoneNum = 10;

            othelloBoardStatusStub = mock(OthelloBoardStatus.class);
            when(othelloBoardStatusStub.getBlackStoneNum())
                    .thenReturn(blackStoneNum);
            when(othelloBoardStatusStub.getWhiteStoneNum())
                    .thenReturn(whiteStoneNum);

            othelloGameResultStub = mock(OthelloGameResult.class);
            doNothing()
                    .when(othelloGameResultStub)
                    .setBlackStoneNum(anyInt());
            doNothing()
                    .when(othelloGameResultStub)
                    .setWhiteStoneNum(anyInt());
            doNothing()
                    .when(othelloGameResultStub)
                    .setWinner(any());

            instance = new OthelloGameResultUpdater(
                    othelloBoardStatusStub,
                    othelloGameResultStub
            );

        }

        @Test
        public void updateOthelloGameResult() {

            ArgumentCaptor<Integer> blackStoneNumArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Integer> whiteStoneNumArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            ArgumentCaptor<Winner> winnerArgumentCaptor = ArgumentCaptor.forClass(Winner.class);

            instance.updateOthelloGameResult();

            verify(othelloBoardStatusStub, times(1))
                    .getBlackStoneNum();
            verify(othelloBoardStatusStub, times(1))
                    .getWhiteStoneNum();

            verify(othelloGameResultStub, times(1))
                    .setBlackStoneNum(blackStoneNumArgumentCaptor.capture());
            int actualBlackStoneNum = blackStoneNumArgumentCaptor.getValue();
            assertThat(actualBlackStoneNum, is(blackStoneNum));

            verify(othelloGameResultStub, times(1))
                    .setWhiteStoneNum(whiteStoneNumArgumentCaptor.capture());
            int actualWhiteStoneNum = whiteStoneNumArgumentCaptor.getValue();
            assertThat(actualWhiteStoneNum, is(whiteStoneNum));

            Winner expectedWinner = Winner.DRAW;
            verify(othelloGameResultStub, times(1))
                    .setWinner(winnerArgumentCaptor.capture());
            Winner actualWinner = winnerArgumentCaptor.getValue();
            assertThat(actualWinner, is(expectedWinner));

        }

    }

}