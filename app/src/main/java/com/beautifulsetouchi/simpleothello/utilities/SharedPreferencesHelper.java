package com.beautifulsetouchi.simpleothello.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * sharedPreferencesへのaccessTokenなどの認可関連の情報を格納するのをサポートするクラス
 */
public class SharedPreferencesHelper {

    private static final String ACCESS_TOKEN_KEY = "Access token key";
    private static final String REFRESH_TOKEN_KEY = "Refresh token key";
    private static final String EXPIRY_TIME = "Expiry time";
    private static final String CODE_VERIFIER = "Code verifier";

    private SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveCodeVerifier(String codeVerifier) {
        sharedPreferences.edit().putString(CODE_VERIFIER, codeVerifier).commit();
    }

    public String getCodeVerifier() {
        return sharedPreferences.getString(CODE_VERIFIER, null);
    }

    public void refreshCodeVerifier() {
        sharedPreferences.edit().putString(CODE_VERIFIER, null).commit();
    }

    public void saveAccessTokenKey(String accessTokenKey) {
        sharedPreferences.edit().putString(ACCESS_TOKEN_KEY, accessTokenKey).commit();
    }

    public String getAccessTokenKey() {
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null);
    }

    public void refreshAccessTokenKey() {
        sharedPreferences.edit().putString(ACCESS_TOKEN_KEY, null).commit();
    }

    public void saveRefreshTokenKey(String refreshTokenKey){
        sharedPreferences.edit().putString(REFRESH_TOKEN_KEY, refreshTokenKey).commit();
    }

    public String getRefreshTokenKey() {
        return sharedPreferences.getString(REFRESH_TOKEN_KEY, null);
    }

    public void refreshRefreshTokenKey() {
        sharedPreferences.edit().putString(REFRESH_TOKEN_KEY, null).commit();
    }

    public void saveExpiryTime(Long expiryTime){
        sharedPreferences.edit().putLong(EXPIRY_TIME, expiryTime).commit();
    }

    public Long getExpiryTime() {
        return sharedPreferences.getLong(EXPIRY_TIME, 0);
    }

    public void refreshExpiryTime() {
        sharedPreferences.edit().putLong(EXPIRY_TIME, 0).commit();
    }
}
