package com.beautifulsetouchi.simpleothello.model.entity;

/**
 * 認可コードフロー+PECEで利用するcodeVerifierとcodeChallengeを生成した際に、
 * その組を格納しておくクラス
 */
public class OAuthKeyForPkce {

    private String codeVerifier;
    private String codeChallenge;

    public OAuthKeyForPkce(String codeVerifier, String codeChallenge){
        this.codeVerifier = codeVerifier;
        this.codeChallenge = codeChallenge;
    }

    public String getCodeVerifier() {
        return codeVerifier;
    }

    public String getCodeChallenge() {
        return codeChallenge;
    }

}
