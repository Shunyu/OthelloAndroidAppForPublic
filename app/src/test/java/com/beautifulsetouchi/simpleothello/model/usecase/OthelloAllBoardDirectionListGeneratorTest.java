package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.constant.SingleBoardDirection;
import com.beautifulsetouchi.simpleothello.model.entity.BoardDirection;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloAllBoardDirectionListGenerator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OthelloAllBoardDirectionListGeneratorTest {

    @Test
    public void testDefaultConstructor() {
        OthelloAllBoardDirectionListGenerator instance = new OthelloAllBoardDirectionListGenerator();
        List<BoardDirection> actual = instance.getAllBoardDirectionList();

        List<BoardDirection> expected = new ArrayList<>(
                Arrays.asList(
                        new BoardDirection(SingleBoardDirection.DECREMENTAL_DIRECTION,SingleBoardDirection.DECREMENTAL_DIRECTION),
                        new BoardDirection(SingleBoardDirection.DECREMENTAL_DIRECTION,SingleBoardDirection.NO_DIRECTION),
                        new BoardDirection(SingleBoardDirection.DECREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION),
                        new BoardDirection(SingleBoardDirection.NO_DIRECTION,SingleBoardDirection.DECREMENTAL_DIRECTION),
                        new BoardDirection(SingleBoardDirection.NO_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION),
                        new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.DECREMENTAL_DIRECTION),
                        new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.NO_DIRECTION),
                        new BoardDirection(SingleBoardDirection.INCREMENTAL_DIRECTION,SingleBoardDirection.INCREMENTAL_DIRECTION)
                )
        );

        assertEquals(expected, actual);

    }

}