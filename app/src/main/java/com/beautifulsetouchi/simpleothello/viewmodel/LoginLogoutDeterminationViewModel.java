package com.beautifulsetouchi.simpleothello.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.beautifulsetouchi.simpleothello.model.entity.OAuthKeyForPkce;
import com.beautifulsetouchi.simpleothello.model.oauth.OAuthKeyForPkceGenerator;
import com.beautifulsetouchi.simpleothello.model.oauth.OAuthLoginUriGenerator;
import com.beautifulsetouchi.simpleothello.model.service.OAuthServerApiService;
import com.beautifulsetouchi.simpleothello.model.entity.OAuthToken;
import com.beautifulsetouchi.simpleothello.utilities.SharedPreferencesHelper;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * LoginLogoutDeterminationActivity用のViewModelクラス
 */
public class LoginLogoutDeterminationViewModel extends AndroidViewModel {

    // Viewに通知するもの
    // ログイン時に利用する認可サーバーのURI
    public MutableLiveData<String> loginUriStringLiveData = new MutableLiveData<String>();
    // AccessTokenがエラーであるか否か（RefreshTokenを利用して更新してもエラーが続く場合）
    public MutableLiveData<Boolean> accessTokenLoadError = new MutableLiveData<Boolean>();
    // ログオフの失敗の通知
    public MutableLiveData<Boolean> logoffError = new MutableLiveData<Boolean>();
    // インターネットに通信中であるか
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();
    // AccessTokenを取得して、SharedPreferencesに保存したか否か
    public MutableLiveData<Boolean> accessTokenSavedFlag = new MutableLiveData<Boolean>();
    // ログオフ時に不要になったAccessTokenを、SharedPreferencesから削除したか否か
    public MutableLiveData<Boolean> accessTokenDeletedFlag = new MutableLiveData<Boolean>();

    // 非同期処理の途中でonClearedが呼ばれた際に、非同期処理のスレッドも終了させるためのインスタンス
    private CompositeDisposable disposable = new CompositeDisposable();

    // 生成したcodeVerifierとcodeChallengeの組（認可コードフロー+PKCEで利用）
    private OAuthKeyForPkce oAuthKeyForPkce;

    // 認可サーバーへの通信を実施するModel
    OAuthServerApiService oAuthServerApiService;
    // AccessToken等の情報をSharedPreferencesに保存するのをサポートするインスタンス
    SharedPreferencesHelper sharedPreferencesHelper;
    // codeVerifierとcodeChallengeの組を生成するインスタンス
    OAuthKeyForPkceGenerator oAuthKeyForPkceGenerator;
    // ログイン時に利用する認可サーバーのURIを生成するインスタンス
    OAuthLoginUriGenerator oAuthLoginUriGenerator;

    public LoginLogoutDeterminationViewModel(Application application){
        super(application);
        sharedPreferencesHelper = new SharedPreferencesHelper(application);

        oAuthServerApiService = new OAuthServerApiService();
        oAuthKeyForPkceGenerator = new OAuthKeyForPkceGenerator();
        oAuthLoginUriGenerator = new OAuthLoginUriGenerator();

        loading.setValue(false);
    }

    /**
     * ログイン処理を開始する。
     */
    public void startLogin() {

        // codeVerifierとcodeChallengeの組を生成
        oAuthKeyForPkce = oAuthKeyForPkceGenerator.generateOAuthKeyForPkce();
        // codeChallengeの情報を踏まえて、ログイン時の認可サーバーのURIを生成
        String loginUriString = oAuthLoginUriGenerator.generateLoginUri(oAuthKeyForPkce.getCodeChallenge());
        // Viewに通知すべく、ログイン時の認可サーバーのURIをフィールドに格納
        loginUriStringLiveData.setValue(loginUriString);
        // 後ほど使用するcodeVerifierをSharedPreferencesに格納
        sharedPreferencesHelper.saveCodeVerifier(oAuthKeyForPkce.getCodeVerifier());

    }

    /**
     * 認可サーバーからのリダイレクトで受領した認可コードをもとにaccessTokenなどの情報を受領して、
     * sharedPreferencesに格納する。
     * @param authorizationCode 受領した認可コード
     */
    public void getAccessToken(String authorizationCode) {

        // インターネット通信の開始をviewに伝達するため
        loading.setValue(true);
        // accessTokenなどの情報の取得に必要となるcodeVerifier
        String codeVerifier = sharedPreferencesHelper.getCodeVerifier();
        Log.i("vm", "accessToken on ViewModel: " + codeVerifier);

        // 非同期処理実行時に呼び出し元のスレッドが終了した際に、非同期処理のスレッドも終了させるため
        disposable.add(
                oAuthServerApiService.getAccessToken(authorizationCode, codeVerifier)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<OAuthToken>(){

                    @Override
                    public void onSuccess(@NonNull OAuthToken oAuthToken) {

                        // accessTokenなどのロード成功
                        accessTokenLoadError.setValue(false);

                        // sharedPreferencesへ格納
                        sharedPreferencesHelper.saveAccessTokenKey(oAuthToken.getAccessToken());
                        sharedPreferencesHelper.saveRefreshTokenKey(oAuthToken.getRefreshToken());

                        Long expiresIn = oAuthToken.getExpiresIn();
                        Long expiryTime = System.currentTimeMillis() + (expiresIn * 1000);
                        sharedPreferencesHelper.saveExpiryTime(expiryTime);
                        Log.i("vm", "accessToken expiresIn on ViewModel: " + expiresIn);

                        // sharedPreferencesへの格納完了を伝達
                        accessTokenSavedFlag.setValue(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        accessTokenLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );

    }

    /**
     * RefreshTokenをもとに、AccessTokenなどの情報を更新する。
     * ここでは利用していない。
     */
    public void refreshAccessToken() {
        loading.setValue(true);

        String refreshToken = sharedPreferencesHelper.getRefreshTokenKey();
        disposable.add(
                oAuthServerApiService.refreshAccessToken(refreshToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<OAuthToken>(){

                    @Override
                    public void onSuccess(@NonNull OAuthToken oAuthToken) {
                        accessTokenLoadError.setValue(false);

                        sharedPreferencesHelper.saveAccessTokenKey(oAuthToken.getAccessToken());
                        sharedPreferencesHelper.saveRefreshTokenKey(oAuthToken.getRefreshToken());

                        Long expiresIn = oAuthToken.getExpiresIn();
                        Long expiryTime = System.currentTimeMillis() + (expiresIn * 1000);
                        sharedPreferencesHelper.saveExpiryTime(expiryTime);

                        accessTokenSavedFlag.setValue(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        accessTokenLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );

    }


    /**
     * ログオフでは、認可サーバーのEndSessionEndpointにリクエストを送る。
     * その際には、RefreshTokenの情報を付加する。
     */
    public void startLogoff() {

        String refreshToken = sharedPreferencesHelper.getRefreshTokenKey();
        if (refreshToken != null) {
            loading.setValue(true);
            disposable.add(
                    oAuthServerApiService.invalidateAccessToken(refreshToken)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(() -> {
                                logoffError.setValue(false);
                                sharedPreferencesHelper.refreshAccessTokenKey();
                                sharedPreferencesHelper.refreshRefreshTokenKey();
                                sharedPreferencesHelper.refreshExpiryTime();

                                accessTokenSavedFlag.setValue(false);
                                accessTokenDeletedFlag.setValue(true);
                            }, throwable -> {
                                logoffError.setValue(true);
                                loading.setValue(false);
                            })
            );
        } else {
            logoffError.setValue(false);
            loading.setValue(false);
        }
    }

    /**
     * onClearedが呼ばれた際に、非同期処理もクリアする
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
