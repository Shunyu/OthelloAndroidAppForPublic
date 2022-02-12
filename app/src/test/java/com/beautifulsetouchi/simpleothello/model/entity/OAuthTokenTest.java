package com.beautifulsetouchi.simpleothello.model.entity;

import com.beautifulsetouchi.simpleothello.model.entity.OAuthToken;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OAuthTokenTest {

    @Test
    public void setAccessToken() {

        OAuthToken instance = new OAuthToken();
        String expected = "accessToken";
        instance.setAccessToken(expected);
        String actual = instance.getAccessToken();

        assertThat(actual, is(expected));

    }

    @Test
    public void setExpiresIn() {

        OAuthToken instance = new OAuthToken();
        Long expected = Long.valueOf(100);
        instance.setExpiresIn(expected);
        Long actual = instance.getExpiresIn();

        assertThat(actual, is(expected));
    }

    @Test
    public void setTokenType() {

        OAuthToken instance = new OAuthToken();
        String expected = "tokenType";
        instance.setTokenType(expected);
        String actual = instance.getTokenType();

        assertThat(actual, is(expected));
    }

    @Test
    public void setRefreshToken() {
        OAuthToken instance = new OAuthToken();
        String expected = "refreshToken";
        instance.setRefreshToken(expected);
        String actual = instance.getRefreshToken();

        assertThat(actual, is(expected));
    }

}