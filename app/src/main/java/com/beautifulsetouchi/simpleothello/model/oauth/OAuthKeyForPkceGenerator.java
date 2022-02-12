package com.beautifulsetouchi.simpleothello.model.oauth;

import android.util.Base64;
import android.util.Log;

import com.beautifulsetouchi.simpleothello.model.entity.OAuthKeyForPkce;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * S256のアルゴリズムに従って、codeVerifierとcodeChallengeの組を生成するクラス
 */
public class OAuthKeyForPkceGenerator {

    public OAuthKeyForPkce generateOAuthKeyForPkce(){

        // codeVerifierとcodeChallengeの作成
        String codeVerifier = "";
        String codeChallenge = "";
        try{
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

            byte[] bytes = new byte[64];
            secureRandom.nextBytes(bytes);
            int encoding = Base64.URL_SAFE | Base64.NO_PADDING | Base64.NO_WRAP;
            codeVerifier = Base64.encodeToString(bytes, encoding);

            Log.i("model", "codeVerifier:"+codeVerifier);

            byte[] codeVerifierBytes = codeVerifier.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(codeVerifierBytes);
            byte[] digest = messageDigest.digest();
            codeChallenge = Base64.encodeToString(digest, encoding);
            Log.i("model", "codeChallenge:"+codeChallenge);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        OAuthKeyForPkce oAuthKeyForPkce = new OAuthKeyForPkce(codeVerifier, codeChallenge);

        return oAuthKeyForPkce;

    }
}
