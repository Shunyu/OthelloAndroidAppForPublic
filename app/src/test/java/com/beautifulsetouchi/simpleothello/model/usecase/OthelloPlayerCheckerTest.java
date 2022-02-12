package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.Player;
import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoardStatus;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;

import org.junit.Before;
import org.junit.Test;

import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.BLACK;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OthelloPlayerCheckerTest {

    OthelloBoard othelloBoardStub;
    OthelloBoardStatus othelloBoardStatusStub;
    OthelloBoardIndexAdder othelloBoardIndexAdderStub;
    OthelloPlayerChecker instance;

    @Before
    public void setUp() {
        othelloBoardIndexAdderStub = mock(OthelloBoardIndexAdder.class);
        BoardIndex targetIndex = new BoardIndex(2,2);
        when(othelloBoardIndexAdderStub.addBoardIndex(any(), any(), anyInt()))
                .thenReturn(targetIndex);
    }

    @Test
    public void isMyself_givenSamePlayer_returnTrue() {
        othelloBoardStub = mock(OthelloBoard.class);
        when(othelloBoardStub.getBoardStone(any()))
                .thenReturn(BLACK);

        othelloBoardStatusStub = mock(OthelloBoardStatus.class);
        when(othelloBoardStatusStub.getPlayer())
                .thenReturn(Player.BLACK);

        instance = new OthelloPlayerChecker(
                othelloBoardStub,
                othelloBoardStatusStub,
                othelloBoardIndexAdderStub
        );

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,1);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
        int targetOffset = 3;
        boolean actual = instance.isMyself(
                boardIndex,
                boardDirection,
                targetOffset
        );

        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void isMyself_givenDifferentPlayer_returnFalse() {
        othelloBoardStub = mock(OthelloBoard.class);
        when(othelloBoardStub.getBoardStone(any()))
                .thenReturn(BLACK);

        othelloBoardStatusStub = mock(OthelloBoardStatus.class);
        when(othelloBoardStatusStub.getPlayer())
                .thenReturn(Player.WHITE);

        instance = new OthelloPlayerChecker(
                othelloBoardStub,
                othelloBoardStatusStub,
                othelloBoardIndexAdderStub
        );

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(1,1);
        BoardDirection boardDirection = new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION);
        int targetOffset = 3;
        boolean actual = instance.isMyself(
                boardIndex,
                boardDirection,
                targetOffset
        );

        boolean expected = false;

        assertThat(actual, is(expected));
    }
}