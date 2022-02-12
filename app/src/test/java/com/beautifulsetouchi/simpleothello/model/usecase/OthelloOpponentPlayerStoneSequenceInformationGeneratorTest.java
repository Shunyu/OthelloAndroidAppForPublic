package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloOpponentPlayerStoneSequenceInformation;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.BLACK;
import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.WHITE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class OthelloOpponentPlayerStoneSequenceInformationGeneratorTest {

    public static class DifferentPlayerAtTheFirstTime {

        OthelloBoard othelloBoardStub;
        OthelloBoardStatus othelloBoardStatusStub;
        OthelloOpponentPlayerStoneSequenceInformationGenerator instance;

        @Before
        public void setUp() {

            othelloBoardStub = mock(OthelloBoard.class);
            when(othelloBoardStub.getBoardStone(any()))
                    .thenReturn(BLACK);

            othelloBoardStatusStub = mock(OthelloBoardStatus.class);
            when(othelloBoardStatusStub.getOpponentPlayer())
                    .thenReturn(Player.WHITE);

            instance = new OthelloOpponentPlayerStoneSequenceInformationGenerator(
                    othelloBoardStub,
                    othelloBoardStatusStub
            );

        }

        @Test
        public void getOthelloOpponentPlayerStoneSequenceInformation_givenVaildBoardIndexRangeList() {

            List<BoardIndex> boardIndexRangeList = new ArrayList<>();
            boardIndexRangeList.add(new BoardIndex(1, 2));
            boardIndexRangeList.add(new BoardIndex(2, 2));

            OthelloOpponentPlayerStoneSequenceInformation expected = instance.getOthelloOpponentPlayerStoneSequenceInformation(boardIndexRangeList);
            int actualNum = expected.getOpponentPlayerStoneNum();
            int expectedNum = 0;
            assertThat(actualNum, is(expectedNum));

            boolean actualFlag = expected.isNonOpponentPlayerExistenceFlag();
            boolean expectedFlag = true;
            assertThat(actualFlag, is(expectedFlag));

            verify(othelloBoardStub, times(1)).getBoardStone(any());

        }

        @Test(expected = IllegalArgumentException.class)
        public void getOthelloOpponentPlayerStoneSequenceInformation_givenLowerBoundInvalidBoardIndexRangeList() {
            List<BoardIndex> boardIndexRangeList = new ArrayList<>();
            instance.getOthelloOpponentPlayerStoneSequenceInformation(boardIndexRangeList);
        }

        @Test(expected = IllegalArgumentException.class)
        public void getOthelloOpponentPlayerStoneSequenceInformation_givenUpperBoundInvalidBoardIndexRangeList() {
            List<BoardIndex> boardIndexRangeListStub = mock(ArrayList.class);
            Mockito.doReturn(9).when(boardIndexRangeListStub).size();
            instance.getOthelloOpponentPlayerStoneSequenceInformation(boardIndexRangeListStub);
        }
    }

    public static class DifferentPlayerAtTheSecondTime {

        OthelloBoard othelloBoardStub;
        OthelloBoardStatus othelloBoardStatusStub;
        OthelloOpponentPlayerStoneSequenceInformationGenerator instance;

        @Before
        public void setUp() {
            othelloBoardStub = mock(OthelloBoard.class);
            when(othelloBoardStub.getBoardStone(any()))
                    .thenReturn(WHITE)
                    .thenReturn(-1);

            othelloBoardStatusStub = mock(OthelloBoardStatus.class);
            when(othelloBoardStatusStub.getOpponentPlayer())
                    .thenReturn(Player.WHITE);

            instance = new OthelloOpponentPlayerStoneSequenceInformationGenerator(
                    othelloBoardStub,
                    othelloBoardStatusStub
            );
        }

        @Test
        public void getOthelloOpponentPlayerStoneSequenceInformation_givenValidList() {
            List<BoardIndex> boardIndexRangeList = new ArrayList<>();
            boardIndexRangeList.add(new BoardIndex(8,8));
            boardIndexRangeList.add(new BoardIndex(9,8));

            OthelloOpponentPlayerStoneSequenceInformation actualInfo = instance.getOthelloOpponentPlayerStoneSequenceInformation(boardIndexRangeList);

            int actualNum = actualInfo.getOpponentPlayerStoneNum();
            int expectedNum = 1;
            assertThat(actualNum, is(expectedNum));

            boolean actualFlag = actualInfo.isNonOpponentPlayerExistenceFlag();
            boolean expectedFlag = true;
            assertThat(actualFlag, is(expectedFlag));

            verify(othelloBoardStub, times(2)).getBoardStone(any());
        }


    }

    public static class TwiceSamePlayer {

        OthelloBoard othelloBoardStub;
        OthelloBoardStatus othelloBoardStatusStub;
        OthelloOpponentPlayerStoneSequenceInformationGenerator instance;

        @Before
        public void setUp() {
            othelloBoardStub = mock(OthelloBoard.class);
            when(othelloBoardStub.getBoardStone(any()))
                    .thenReturn(WHITE)
                    .thenReturn(WHITE);

            othelloBoardStatusStub = mock(OthelloBoardStatus.class);
            when(othelloBoardStatusStub.getOpponentPlayer())
                    .thenReturn(Player.WHITE);

            instance = new OthelloOpponentPlayerStoneSequenceInformationGenerator(
                    othelloBoardStub,
                    othelloBoardStatusStub
            );
        }

        @Test
        public void getOthelloOpponentPlayerStoneSequenceInformation_givenValidList() {
            List<BoardIndex> boardIndexRangeList = new ArrayList<>();
            boardIndexRangeList.add(new BoardIndex(8,8));
            boardIndexRangeList.add(new BoardIndex(9,8));

            OthelloOpponentPlayerStoneSequenceInformation actualInfo = instance.getOthelloOpponentPlayerStoneSequenceInformation(boardIndexRangeList);

            int actualNum = actualInfo.getOpponentPlayerStoneNum();
            int expectedNum = 2;
            assertThat(actualNum, is(expectedNum));

            boolean actualFlag = actualInfo.isNonOpponentPlayerExistenceFlag();
            boolean expectedFlag = false;
            assertThat(actualFlag, is(expectedFlag));

            verify(othelloBoardStub, times(2)).getBoardStone(any());
        }

    }

}
