package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.entity.GameSituation;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GameSituationTest {

    @Test
    public void setBeforeBoard() {
        GameSituation instance = new GameSituation();
        int[][] expected = {
                {1, 2},
                {3, 4}
        };
        instance.setBeforeBoard(expected);
        int[][] actual = instance.getBeforeBoard();

        Arrays.deepEquals(expected, actual);
    }

    @Test
    public void setPlayer_givenOne() {
        GameSituation instance = new GameSituation();
        int expected = 1;
        instance.setPlayer(expected);
        int actual = instance.getPlayer();

        assertThat(actual, is(expected));
    }

    @Test
    public void setPlayer_givenTwo() {
        GameSituation instance = new GameSituation();
        int expected = 2;
        instance.setPlayer(expected);
        int actual = instance.getPlayer();

        assertThat(actual, is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setPlayer_givenIllegalPlayer() {
        GameSituation instance = new GameSituation();
        instance.setPlayer(3);
    }

    @Test
    public void setAfterBoard() {
        GameSituation instance = new GameSituation();

        int[][] expected = {
                {1, 2},
                {3, 4}
        };
        instance.setAfterBoard(expected);
        int[][] actual = instance.getAfterBoard();

        Arrays.deepEquals(expected, actual);
    }

    @Test
    public void setAction() {

        GameSituation instance = new GameSituation();
        String expected = "12";
        instance.setAction(expected);
        String actual = instance.getAction();

        assertThat(actual, is(expected));
    }

}