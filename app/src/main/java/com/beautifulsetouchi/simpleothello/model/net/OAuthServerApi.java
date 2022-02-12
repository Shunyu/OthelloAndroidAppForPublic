package com.beautifulsetouchi.simpleothello.model.net;

import com.beautifulsetouchi.simpleothello.model.entity.OAuthToken;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * インターネット経由で、認可サーバーにアクセスするために定義したインターフェース
 */
public interface OAuthServerApi {

    /**
     * 認可コードを用いて、accessTokenなどの情報を取得する
     * @param authorizationCode 認可コード
     * @param clientId 認可サーバーで登録したクライアントID
     * @param redirectUri 認可コード取得時に、認可サーバーからリダイレクトする際のURI（認可サーバー側でも指定する必要）
     * @param grantType 認可コードを利用して、accessTokenなどを取得する旨を伝える
     * @param codeVerifier 認可コードフロー+PKCEでは、事前に伝えたcodeChallengeに対応するcodeVerifierを送付する必要がある
     * @return
     */
    @POST("protocol/openid-connect/token")
    @FormUrlEncoded
    Single<OAuthToken> getAccessToken(
            @Field("code") String authorizationCode,
            @Field("client_id") String clientId,
            @Field("redirect_uri") String redirectUri,
            @Field("grant_type") String grantType,
            @Field("code_verifier") String codeVerifier
    );

    /**
     * refreshTokenを用いて、accessTokenなどの情報を再取得する
     * @param clientId 認可サーバーで登録したクライアントID
     * @param refreshToken
     * @param grantType refreshTokenを利用して、accessTokenなどを取得する旨を伝える
     * @return
     */
    @POST("protocol/openid-connect/token")
    @FormUrlEncoded
    Single<OAuthToken> refreshAccessToken(
            @Field("client_id") String clientId,
            @Field("refresh_token") String refreshToken,
            @Field("grant_type") String grantType
    );

    /**
     * 認可サーバーのend session endpointにリクエストを送り、認可サーバーでログアウト処理を行う
     * @param clientId 認可サーバーで登録したクライアントID
     * @param refreshToken
     * @return
     */
    @POST("protocol/openid-connect/logout")
    @FormUrlEncoded
    Completable invalidateAccessToken(
            @Field("client_id") String clientId,
            @Field("refresh_token") String refreshToken
    );

}
