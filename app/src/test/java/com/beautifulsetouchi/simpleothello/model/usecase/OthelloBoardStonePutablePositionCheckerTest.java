package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloBoard;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.BLACK;
import static com.beautifulsetouchi.simpleothello.model.constant.OthelloBoardStatusConstants.PUTABLE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloBoardStonePutablePositionCheckerTest {

    OthelloBoard othelloBoardStub;
    OthelloBoardStonePutablePositionChecker instance;

    @Before
    public void setUp() {
        othelloBoardStub = mock(OthelloBoard.class);
        when(othelloBoardStub.getBoardStone(any()))
                .thenReturn(BLACK);
        when(othelloBoardStub.getBoardStone(eq(new UpdatableBoardIndex(2,2))))
                .thenReturn(PUTABLE);

        instance = new OthelloBoardStonePutablePositionChecker(
                othelloBoardStub
        );
    }

    @Test
    public void canPutBoardStone_givenSpecificBoardIndex_returnTrue() {

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
        ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        boolean actualFlag = instance.canPutBoardStone(boardIndex);

        verify(othelloBoardStub, times(1))
                .getBoardStone(boardIndexArgumentCaptor.capture());
        UpdatableBoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();
        assertEquals(boardIndex, actualBoardIndex);

        boolean expectedFlag = true;
        assertThat(actualFlag, is(expectedFlag));

    }

    @Test
    public void canPutBoardStone_givenSpecificBoardIndex_returnFalse() {

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,3);
        ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        boolean actualFlag = instance.canPutBoardStone(boardIndex);

        verify(othelloBoardStub, times(1))
                .getBoardStone(boardIndexArgumentCaptor.capture());
        UpdatableBoardIndex actualBoardIndex = boardIndexArgumentCaptor.getValue();
        assertEquals(boardIndex, actualBoardIndex);

        boolean expectedFlag = false;
        assertThat(actualFlag, is(expectedFlag));

    }
}