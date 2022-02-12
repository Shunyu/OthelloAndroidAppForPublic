package com.beautifulsetouchi.simpleothello.utilities;

import com.beautifulsetouchi.simpleothello.utilities.MinListLengthCalculator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MinListLengthCalculatorTest {

    MinListLengthCalculator instance;

    @Before
    public void setUp() {
        instance = new MinListLengthCalculator();
    }

    @Test
    public void getMinListLength_givenValidTwoList() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(2,1,3,5));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(2,4,5,6,7));

        int expected = 4;
        int actual = instance.getMinListLength(list1, list2);

        assertThat(actual, is(expected));
    }
}
