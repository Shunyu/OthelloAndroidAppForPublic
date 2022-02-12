package com.beautifulsetouchi.simpleothello.model.usecase;

import com.beautifulsetouchi.simpleothello.model.entity.UpdatableBoardIndex;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloAllUpdatableBoardIndexListGenerator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OthelloAllUpdatableBoardIndexListGeneratorTest {

    @Test
    public void testDefaultConstructor() {
        OthelloAllUpdatableBoardIndexListGenerator instance = new OthelloAllUpdatableBoardIndexListGenerator();
        List<UpdatableBoardIndex> actual = instance.getAllUpdatableBoardIndexList();

        List<UpdatableBoardIndex> expected = new ArrayList<>(
                Arrays.asList(
                        new UpdatableBoardIndex(1,1),
                        new UpdatableBoardIndex(1,2),
                        new UpdatableBoardIndex(1,3),
                        new UpdatableBoardIndex(1,4),
                        new UpdatableBoardIndex(1,5),
                        new UpdatableBoardIndex(1,6),
                        new UpdatableBoardIndex(1,7),
                        new UpdatableBoardIndex(1,8),
                        new UpdatableBoardIndex(2,1),
                        new UpdatableBoardIndex(2,2),
                        new UpdatableBoardIndex(2,3),
                        new UpdatableBoardIndex(2,4),
                        new UpdatableBoardIndex(2,5),
                        new UpdatableBoardIndex(2,6),
                        new UpdatableBoardIndex(2,7),
                        new UpdatableBoardIndex(2,8),
                        new UpdatableBoardIndex(3,1),
                        new UpdatableBoardIndex(3,2),
                        new UpdatableBoardIndex(3,3),
                        new UpdatableBoardIndex(3,4),
                        new UpdatableBoardIndex(3,5),
                        new UpdatableBoardIndex(3,6),
                        new UpdatableBoardIndex(3,7),
                        new UpdatableBoardIndex(3,8),
                        new UpdatableBoardIndex(4,1),
                        new UpdatableBoardIndex(4,2),
                        new UpdatableBoardIndex(4,3),
                        new UpdatableBoardIndex(4,4),
                        new UpdatableBoardIndex(4,5),
                        new UpdatableBoardIndex(4,6),
                        new UpdatableBoardIndex(4,7),
                        new UpdatableBoardIndex(4,8),
                        new UpdatableBoardIndex(5,1),
                        new UpdatableBoardIndex(5,2),
                        new UpdatableBoardIndex(5,3),
                        new UpdatableBoardIndex(5,4),
                        new UpdatableBoardIndex(5,5),
                        new UpdatableBoardIndex(5,6),
                        new UpdatableBoardIndex(5,7),
                        new UpdatableBoardIndex(5,8),
                        new UpdatableBoardIndex(6,1),
                        new UpdatableBoardIndex(6,2),
                        new UpdatableBoardIndex(6,3),
                        new UpdatableBoardIndex(6,4),
                        new UpdatableBoardIndex(6,5),
                        new UpdatableBoardIndex(6,6),
                        new UpdatableBoardIndex(6,7),
                        new UpdatableBoardIndex(6,8),
                        new UpdatableBoardIndex(7,1),
                        new UpdatableBoardIndex(7,2),
                        new UpdatableBoardIndex(7,3),
                        new UpdatableBoardIndex(7,4),
                        new UpdatableBoardIndex(7,5),
                        new UpdatableBoardIndex(7,6),
                        new UpdatableBoardIndex(7,7),
                        new UpdatableBoardIndex(7,8),
                        new UpdatableBoardIndex(8,1),
                        new UpdatableBoardIndex(8,2),
                        new UpdatableBoardIndex(8,3),
                        new UpdatableBoardIndex(8,4),
                        new UpdatableBoardIndex(8,5),
                        new UpdatableBoardIndex(8,6),
                        new UpdatableBoardIndex(8,7),
                        new UpdatableBoardIndex(8,8)
                )
        );

        assertEquals(expected, actual);
    }

}