package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.entity.GameResultRequestResource;
import com.beautifulsetouchi.simpleothello.model.entity.GameSituation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GameResultRequestResourceTest {

    @Test
    public void setGameresult_givenBlack() {
        GameResultRequestResource instance = new GameResultRequestResource();
        String expectedGameresult = "black";
        instance.setGameresult(expectedGameresult);
        String actualGameresult = instance.getGameresult();

        assertThat(actualGameresult, is(expectedGameresult));
    }

    @Test
    public void setGameresult_givenWhite() {
        GameResultRequestResource instance = new GameResultRequestResource();
        String expectedGameresult = "white";
        instance.setGameresult(expectedGameresult);
        String actualGameresult = instance.getGameresult();

        assertThat(actualGameresult, is(expectedGameresult));
    }

    @Test
    public void setGameresult_givenDraw() {
        GameResultRequestResource instance = new GameResultRequestResource();
        String expectedGameresult = "draw";
        instance.setGameresult(expectedGameresult);
        String actualGameresult = instance.getGameresult();

        assertThat(actualGameresult, is(expectedGameresult));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setGameresult_givenIllegalValue() {
        GameResultRequestResource instance = new GameResultRequestResource();
        instance.setGameresult("");
    }

    @Test
    public void setGameSituationList() {
        GameResultRequestResource instance = new GameResultRequestResource();
        List<GameSituation> expected = new ArrayList<>(Arrays.asList(
                new GameSituation()
        ));
        instance.setGameSituationList(expected);
        List<GameSituation> actual = instance.getGameSituationList();

        assertEquals(expected, actual);

    }
}