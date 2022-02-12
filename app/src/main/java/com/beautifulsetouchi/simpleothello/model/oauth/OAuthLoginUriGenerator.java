package com.beautifulsetouchi.simpleothello.model.oauth;

/**
 * 認可サーバーのログイン画面にWebブラウザにてアクセスする際に必要なURIを生成するクラス
 */
public class OAuthLoginUriGenerator {

    private static final String LOGIN_URI = "https://beautiful-setouchi.com/auth/realms/othellogamerealm/protocol/openid-connect/auth";
    // URIの各パラメータに設定する値
    // 求めるスコープ
    private static final String OAUTH_SCOPE = "xxxx";
    // 認可コードを取得
    private static final String CODE = "code";
    // 認可サーバーで登録したクライアントID
    private static final String CLIENT_ID = "xxxx";
    // 認可コード取得時に、認可サーバーからリダイレクトする際のURI（認可サーバー側でも指定する必要）
    private static final String REDIRECT_URI = "xxxx";
    // STATE
    private static final String STATE = "xxxx";
    // codeVerifierとcodeChallengeの組を生成する際に使用したアルゴリズム（認可サーバー側でも指定する必要）
    private static final String CODE_CHALLENGE_METHOD = "S256";

    public String generateLoginUri(String codeChallenge) {
        String uriString = LOGIN_URI
                + "?client_id=" + CLIENT_ID
                + "&response_type=" + CODE
                + "&redirect_uri=" + REDIRECT_URI
                + "&scope=" + OAUTH_SCOPE
                + "&state=" + STATE
                + "&code_challenge=" + codeChallenge // PKCE対応
                + "&code_challenge_method=" + CODE_CHALLENGE_METHOD; // PKCE対応
        return uriString;
    }
}
