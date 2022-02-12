package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexHorizontalRangeGeneratorChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexRangeGeneratorChecker;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloBoardIndexVerticalRangeGeneratorChecker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OthelloBoardIndexRangeGeneratorCheckerTest {

    OthelloBoardIndexVerticalRangeGeneratorChecker othelloBoardIndexVerticalRangeGeneratorCheckerStub;
    OthelloBoardIndexHorizontalRangeGeneratorChecker othelloBoardIndexHorizontalRangeGeneratorCheckerStub;

    OthelloBoardIndexRangeGeneratorChecker instance;

    @Test
    public void isUpdatableBoardIndex_trueAndTrue_expectedTrue() {

        othelloBoardIndexVerticalRangeGeneratorCheckerStub = mock(OthelloBoardIndexVerticalRangeGeneratorChecker.class);
        when(othelloBoardIndexVerticalRangeGeneratorCheckerStub.isUpdatableBoardIndex(any(), any(), anyInt()))
                .thenReturn(true);

        othelloBoardIndexHorizontalRangeGeneratorCheckerStub = mock(OthelloBoardIndexHorizontalRangeGeneratorChecker.class);
        when(othelloBoardIndexHorizontalRangeGeneratorCheckerStub.isUpdatableBoardIndex(any(), any(), anyInt()))
                .thenReturn(true);

        instance = new OthelloBoardIndexRangeGeneratorChecker(
                othelloBoardIndexVerticalRangeGeneratorCheckerStub,
                othelloBoardIndexHorizontalRangeGeneratorCheckerStub
        );

        boolean actual = instance.isUpdatableBoardIndex(
                new UpdatableBoardIndex(1,1),
                new BoardDirection(SingleBoardDirection.NO_DIRECTION, SingleBoardDirection.INCREMENTAL_DIRECTION),
                2
        );
        boolean expected = true;

        assertThat(actual, is(expected));
    }

    @Test
    public void isUpdatableBoardIndex_trueAndFalse_expectedFalse() {

        othelloBoardIndexVerticalRangeGeneratorCheckerStub = mock(OthelloBoardIndexVerticalRangeGeneratorChecker.class);
        when(othelloBoardIndexVerticalRangeGeneratorCheckerStub.isUpdatableBoardIndex(any(), any(), anyInt()))
                .thenReturn(true);

        othelloBoardIndexHorizontalRangeGeneratorCheckerStub = mock(OthelloBoardIndexHorizontalRangeGeneratorChecker.class);
        when(othelloBoardIndexHorizontalRangeGeneratorCheckerStub.isUpdatableBoardIndex(any(), any(), anyInt()))
                .thenReturn(false);

        instance = new OthelloBoardIndexRangeGeneratorChecker(
                othelloBoardIndexVerticalRangeGeneratorCheckerStub,
                othelloBoardIndexHorizontalRangeGeneratorCheckerStub
        );

        boolean actual = instance.isUpdatableBoardIndex(
                new UpdatableBoardIndex(1,1),
                new BoardDirection(SingleBoardDirection.NO_DIRECTION, SingleBoardDirection.INCREMENTAL_DIRECTION),
                2
        );
        boolean expected = false;

        assertThat(actual, is(expected));
    }

    @Test
    public void isUpdatableBoardIndex_falseAndTrue_expectedFalse() {

        othelloBoardIndexVerticalRangeGeneratorCheckerStub = mock(OthelloBoardIndexVerticalRangeGeneratorChecker.class);
        when(othelloBoardIndexVerticalRangeGeneratorCheckerStub.isUpdatableBoardIndex(any(), any(), anyInt()))
                .thenReturn(false);

        othelloBoardIndexHorizontalRangeGeneratorCheckerStub = mock(OthelloBoardIndexHorizontalRangeGeneratorChecker.class);
        when(othelloBoardIndexHorizontalRangeGeneratorCheckerStub.isUpdatableBoardIndex(any(), any(), anyInt()))
                .thenReturn(true);

        instance = new OthelloBoardIndexRangeGeneratorChecker(
                othelloBoardIndexVerticalRangeGeneratorCheckerStub,
                othelloBoardIndexHorizontalRangeGeneratorCheckerStub
        );

        boolean actual = instance.isUpdatableBoardIndex(
                new UpdatableBoardIndex(1,1),
                new BoardDirection(SingleBoardDirection.NO_DIRECTION, SingleBoardDirection.INCREMENTAL_DIRECTION),
                2
        );
        boolean expected = false;

        assertThat(actual, is(expected));
    }

    @Test
    public void isUpdatableBoardIndex_falseAndFalse_expectedFalse() {

        othelloBoardIndexVerticalRangeGeneratorCheckerStub = mock(OthelloBoardIndexVerticalRangeGeneratorChecker.class);
        when(othelloBoardIndexVerticalRangeGeneratorCheckerStub.isUpdatableBoardIndex(any(), any(), anyInt()))
                .thenReturn(false);

        othelloBoardIndexHorizontalRangeGeneratorCheckerStub = mock(OthelloBoardIndexHorizontalRangeGeneratorChecker.class);
        when(othelloBoardIndexHorizontalRangeGeneratorCheckerStub.isUpdatableBoardIndex(any(), any(), anyInt()))
                .thenReturn(false);

        instance = new OthelloBoardIndexRangeGeneratorChecker(
                othelloBoardIndexVerticalRangeGeneratorCheckerStub,
                othelloBoardIndexHorizontalRangeGeneratorCheckerStub
        );

        boolean actual = instance.isUpdatableBoardIndex(
                new UpdatableBoardIndex(1,1),
                new BoardDirection(SingleBoardDirection.NO_DIRECTION, SingleBoardDirection.INCREMENTAL_DIRECTION),
                2
        );
        boolean expected = false;

        assertThat(actual, is(expected));
    }
}