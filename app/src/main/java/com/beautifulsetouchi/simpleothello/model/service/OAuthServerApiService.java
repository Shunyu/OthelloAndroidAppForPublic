package com.beautifulsetouchi.simpleothello.model.service;

import com.beautifulsetouchi.simpleothello.model.entity.OAuthToken;
import com.beautifulsetouchi.simpleothello.model.net.OAuthServerApi;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * oAuthServerApiを利用して、accessTokenなどの取得・更新、認可サーバーにログアウトを依頼するクラス
 */
public class OAuthServerApiService {

    private static final String BASE_URL = "https://beautiful-setouchi.com/auth/realms/othellogamerealm/";
    // 認可サーバーで登録したクライアントID
    private static final String CLIENT_ID = "xxxx";
    // 認可コード取得時に、認可サーバーからリダイレクトする際のURI（認可サーバー側でも指定する必要）
    private static final String REDIRECT_URI = "xxxx";
    // 認可コードを利用して、accessTokenなどを取得する際にヘッダーフィールドに指定する値
    private static final String AUTHORIZATION_CODE_GRANT_TYPE = "authorization_code";
    // refreshTokenを利用して、accessTokenなどを取得する際にヘッダーフィールドに指定する値
    private static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS);

    // 依存性の注入に変更予定
    OAuthServerApi api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
            .create(OAuthServerApi.class);

    /**
     * 認可サーバーに依頼して、認可コードをもとに、accessTokenなどの取得をおこなう
     * @param authorizationCode 認可コード
     * @param codeVerifier 認可コードフロー+PKCEでは、事前に伝えたcodeChallengeに対応するcodeVerifierを送付する必要がある
     * @return
     */
    public Single<OAuthToken> getAccessToken(
            String authorizationCode, String codeVerifier
    ){
        return api.getAccessToken(authorizationCode, CLIENT_ID, REDIRECT_URI, AUTHORIZATION_CODE_GRANT_TYPE, codeVerifier);
    }

    /**
     * 認可サーバーに依頼して、refreshTokenをもとに、accessTokenなどの取得をおこなう
     * @param refreshToken
     * @return
     */
    public Single<OAuthToken> refreshAccessToken(
            String refreshToken
    ){
        return api.refreshAccessToken(CLIENT_ID, refreshToken, REFRESH_TOKEN_GRANT_TYPE);
    }

    /**
     * 認可サーバーに、ログアウト処理を依頼する。
     * @param refreshToken
     * @return
     */
    public Completable invalidateAccessToken(String refreshToken) {
        return api.invalidateAccessToken(CLIENT_ID, refreshToken);
    }
}