package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardIndex;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloAllBoardDirectionListGenerator;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardAllDirectionTurnOverExecutor;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardSpecificDirectionTurnOverExecutor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OthelloBoardAllDirectionTurnOverExecutorTest {

    OthelloAllBoardDirectionListGenerator othelloAllBoardDirectionListGeneratorStub;
    OthelloBoardSpecificDirectionTurnOverExecutor othelloBoardSpecificDirectionTurnOverExecutorStub;
    OthelloBoardAllDirectionTurnOverExecutor instance;

    private List<BoardDirection> boardDirectionListExpected;

    @Before
    public void setUp() {

        boardDirectionListExpected = new ArrayList<>(
                Arrays.asList(
                        new BoardDirection(SingleBoardDirection.NO_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION),
                        new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.NO_DIRECTION)
                )
        );
        othelloAllBoardDirectionListGeneratorStub = mock(OthelloAllBoardDirectionListGenerator.class);
        when(othelloAllBoardDirectionListGeneratorStub
                .getAllBoardDirectionList())
                .thenReturn(boardDirectionListExpected);

        othelloBoardSpecificDirectionTurnOverExecutorStub = mock(OthelloBoardSpecificDirectionTurnOverExecutor.class);
        doNothing()
                .when(othelloBoardSpecificDirectionTurnOverExecutorStub)
                .executeTurnOverStones(any(), any());

        instance = new OthelloBoardAllDirectionTurnOverExecutor(
                othelloAllBoardDirectionListGeneratorStub,
                othelloBoardSpecificDirectionTurnOverExecutorStub
        );
    }

    @Test
    public void executeTurnOverStones_givenSpecificBoardIndex() {

        UpdatableBoardIndex boardIndex = new UpdatableBoardIndex(2,2);
        instance.executeTurnOverStones(boardIndex);

        verify(othelloAllBoardDirectionListGeneratorStub, times(1))
                .getAllBoardDirectionList();

        ArgumentCaptor<UpdatableBoardIndex> boardIndexArgumentCaptor = ArgumentCaptor.forClass(UpdatableBoardIndex.class);
        ArgumentCaptor<BoardDirection> boardDirectionArgumentCaptor = ArgumentCaptor.forClass(BoardDirection.class);
        verify(othelloBoardSpecificDirectionTurnOverExecutorStub, times(boardDirectionListExpected.size()))
                .executeTurnOverStones(
                        boardIndexArgumentCaptor.capture(),
                        boardDirectionArgumentCaptor.capture()
                );
        List<UpdatableBoardIndex> actualBoardIndexList = boardIndexArgumentCaptor.getAllValues();
        List<BoardDirection> actualBoardDirectionList = boardDirectionArgumentCaptor.getAllValues();

        for (BoardIndex actualBoardIndex : actualBoardIndexList) {
            assertEquals(boardIndex, actualBoardIndex);
        }
        assertEquals(boardDirectionListExpected, actualBoardDirectionList);


    }

}