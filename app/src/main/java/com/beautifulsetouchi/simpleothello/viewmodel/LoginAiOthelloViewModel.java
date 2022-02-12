package com.beautifulsetouchi.simpleothello.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.beautifulsetouchi.simpleothello.model.entity.AiOthelloApiRequestBody;
import com.beautifulsetouchi.simpleothello.model.entity.GameResultRequestResource;
import com.beautifulsetouchi.simpleothello.model.entity.GameSituation;
import com.beautifulsetouchi.simpleothello.model.entity.LoginUserAiOthelloApiBestMove;
import com.beautifulsetouchi.simpleothello.model.service.LoginUserAiOthelloApiService;
import com.beautifulsetouchi.simpleothello.model.service.LoginUserRecordApiService;
import com.beautifulsetouchi.simpleothello.model.service.OAuthServerApiService;
import com.beautifulsetouchi.simpleothello.model.entity.OAuthToken;
import com.beautifulsetouchi.simpleothello.model.constant.Winner;
import com.beautifulsetouchi.simpleothello.utilities.SharedPreferencesHelper;
import com.google.gson.Gson;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * LoginAiOthelloActivity用のViewModelクラス
 */
public class LoginAiOthelloViewModel extends OthelloViewModel {

    // AIオセロにログインして通信を行うModelの参照
    private LoginUserAiOthelloApiService loginUserAiOthelloApiService;
    // 非同期処理の途中でonClearedが呼ばれた際に、非同期処理のスレッドも終了させるためのインスタンス
    private CompositeDisposable disposable;
    // ログインして、対戦成績を参照・更新するために通信を行うModelの参照
    private LoginUserRecordApiService loginUserRecordApiService;
    // 対戦成績の更新では、チートを防ぐために、対戦時の盤面・Player・手の情報の推移の情報を全てサーバーに送って、検証するため
    private List<GameSituation> gameSituationList;
    // ログインして、通信を行う際に必要なAccessToken, RefreshTokenを格納
    private SharedPreferencesHelper sharedPreferencesHelper;
    // ログインするために、認可サーバーに通信を行うModelの参照
    private OAuthServerApiService oAuthServerApiService;

    // Viewに通知するもの
    // 対戦成績
    public ObservableField<String> winNum;
    public ObservableField<String> drawNum;
    public ObservableField<String> loseNum;

    // AccessTokenが有効期限切れの場合、RefreshTokenを利用してAccessTokenを更新する
    // AccessToken更新のための認可サーバーへの通信を2回以上連続して実施しないためのフラグ
    private Boolean invalidAccessTokenFlag;

    // Viewに通知するもの
    // AccessTokenがエラーであるか否か（RefreshTokenを利用して更新してもエラーが続く場合）
    public MutableLiveData<Boolean> accessTokenErrorFlag = new MutableLiveData<Boolean>();
    // インターネットへの通信がエラーであるか否か
    public MutableLiveData<Boolean> internetAccessErrorFlag = new MutableLiveData<Boolean>();
    // インターネットに通信中であるか
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public LoginAiOthelloViewModel(Application application){

        super(application);

        // gameModeとして"AI"を設定して、activity_othello.xmlのgameMode属性に反映させる。
        // 石の画像を盤面に配置する際には、
        // まず、DataBindingAdapterクラスで、
        // activity_othello.xmlのImageViewのgameMode属性とplayer属性を確認し、
        // 2属性から画像の種類を決めて、
        // ImageViewのImageResourceをセッティングしている。
        gameMode.set("AI");

        Log.i("vm", "start LoginAiOthelloViewModelConstructor");

        disposable = new CompositeDisposable();
        loginUserRecordApiService = new LoginUserRecordApiService();
        loginUserAiOthelloApiService = new LoginUserAiOthelloApiService();
        gameSituationList = new ArrayList<>();
        sharedPreferencesHelper = new SharedPreferencesHelper(application);

        // SharedPreferencesに保存してあるAccessTokenを取得。
        String accessTokenFromSharedPreferencesHelper = sharedPreferencesHelper.getAccessTokenKey();
        Log.i("vm","setAccessTokenFromSharedPreferencesHelper");
        // アクセストークンを利用して通信を行うモデルのフィールドにアクセストークンをセット
        loginUserRecordApiService.setAccessToken(accessTokenFromSharedPreferencesHelper);
        loginUserAiOthelloApiService.setAccessToken(accessTokenFromSharedPreferencesHelper);
        // アクセストークンは無効ではない（実際の通信で無効と判明したタイミングでtrueにする。）
        invalidAccessTokenFlag = false;

        oAuthServerApiService = new OAuthServerApiService();

        winNum = new ObservableField<>();
        drawNum = new ObservableField<>();
        loseNum = new ObservableField<>();

        // 画面への反映を行うために、フィールドを更新
        generateView();

        // インターネットへの通信中ではない
        loading.setValue(false);

        Log.i("vm", "finish LoginAiOthelloViewModelConstructor");

    }

    /**
     * AIオセロを開始する
     */
    public void startAiOthelloGame(){

        Log.i("vm","startAiOthelloGame");
        // このメソッドは、LoginAiOthelloActivityから呼び出している。
        // 対戦成績を反映し、準備完了後のタイミングでAIオセロの実行を開始する。
        updateGameByAiOthelloConsecutively();
    }

    /**
     * RefreshTokenをもとにAccessTokenを再取得した際に、
     * 再取得した情報をSharedPreferencesに反映し、AccessTokenを利用して通信を行うModelにも反映させる。
     * @param oAuthToken 再取得したAccessToken, 同時に受領するRefreshTokenや有効期限の情報
     */
    private void updateAccessTokenInformation(OAuthToken oAuthToken) {

        sharedPreferencesHelper.saveAccessTokenKey(oAuthToken.getAccessToken());
        sharedPreferencesHelper.saveRefreshTokenKey(oAuthToken.getRefreshToken());

        Long expiresIn = oAuthToken.getExpiresIn();
        Long expiryTime = System.currentTimeMillis() + (expiresIn * 1000);
        sharedPreferencesHelper.saveExpiryTime(expiryTime);
        Log.i("vm", "accessToken expiresIn on ViewModel: " + expiresIn);

        Log.i("vm","setAccessToken of loginUserRecordApiService and loginUserAiOthelloApiService");
        loginUserRecordApiService.setAccessToken(oAuthToken.getAccessToken());
        loginUserAiOthelloApiService.setAccessToken(oAuthToken.getAccessToken());

    }

    /**
     * Activity起動時に、ユーザー対戦情報を取得する。
     *
     * このメソッドはLoginAiOthelloActivityから呼び出している。
     * 起動のタイミングで現在の対戦成績を表示するために、
     * リソースサーバーにアクセスして、対戦成績取得APIを呼び出す。
     */
    public void setLoginUserRecord(){

        Log.i("vm","setLoginUserRecord");
        // インターネットとの通信の開始をViewに伝達するため
        loading.setValue(true);

        // 非同期処理実行時に呼び出し元のスレッドが終了した際に、非同期処理のスレッドも終了させるため
        disposable.add(
                // インターネットと通信し、対戦成績を受け取る
                loginUserRecordApiService.getLoginUserRecord()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Map<String, Object>>(){
                            @Override
                            public void onSuccess(Map<String, Object> loginUserRecord){

                                Log.i("vm","onSuccess in LoginAiOthelloViewModel");

                                invalidAccessTokenFlag = false;

                                Log.i("vm","invalidAccessTokenFlag is "+invalidAccessTokenFlag);
                                Log.i("vm",""+loginUserRecord);
                                Log.i("vm",""+loginUserRecord.get("winNum"));
                                Log.i("vm",""+loginUserRecord.get("drawNum"));
                                Log.i("vm",""+loginUserRecord.get("loseNum"));

                                // 冗長な記述
                                Float winNumFloat = Float.parseFloat(loginUserRecord.get("winNum").toString());
                                Float drawNumFloat = Float.parseFloat(loginUserRecord.get("drawNum").toString());
                                Float loseNumFloat = Float.parseFloat(loginUserRecord.get("loseNum").toString());

                                Log.i("vm","winNumFloat: "+winNumFloat);
                                Log.i("vm","drawNumFloat: "+drawNumFloat);
                                Log.i("vm","loseNumFloat: "+loseNumFloat);

                                Integer winNumInteger = winNumFloat.intValue();
                                Integer drawNumInteger = drawNumFloat.intValue();
                                Integer loseNumInteger = loseNumFloat.intValue();

                                Log.i("vm","winNumInteger: "+winNumInteger);
                                Log.i("vm","drawNumInteger: "+drawNumInteger);
                                Log.i("vm","loseNumInteger: "+loseNumInteger);

                                // 対戦成績をViewに伝達するため
                                winNum.set(String.valueOf(winNumInteger)+"勝");
                                drawNum.set(String.valueOf(drawNumInteger)+"分");
                                loseNum.set(String.valueOf(loseNumInteger)+"負");

                                // インターネットとの通信を終了したことをViewに伝達するため
                                loading.setValue(false);
                            }
                            @Override
                            public void onError(Throwable e){
                                Log.i("vm","onError in LoginAiOthelloViewModel");
                                Log.i("vm","invalidAccessTokenFlag is "+invalidAccessTokenFlag);
                                e.printStackTrace();

                                if (e instanceof HttpException && ((HttpException)e).code()==401) {
                                    // アクセストークンが有効期限切れだった場合
                                    if (!invalidAccessTokenFlag) {
                                        // 1回目
                                        // trueにしておくことで、2回目にはelse句に処理が進む
                                        invalidAccessTokenFlag = true;
                                        Log.i("vm","invalidAccessTokenFlag is "+invalidAccessTokenFlag);

                                        // アクセストークン有効期限切れでエラーになりうる。
                                        // アクセストークン更新後、自分自身をもう一度実行する。
                                        refreshAccessTokenAndSetLoginUserRecord();
                                    } else {
                                        accessTokenErrorFlag.setValue(true);
                                    }
                                } else {
                                    Log.i("vm", "internetRelatedException");
                                    internetAccessErrorFlag.setValue(true);
                                }

                            }
                        })

        );
    }

    /**
     * 対戦成績APIとの通信時、初回がAccessTokenの有効期限切れだったときに、
     * RefreshTokenをもとにAccessTokenを更新して、もう一度対戦成績APIと通信する。
     */
    private void refreshAccessTokenAndSetLoginUserRecord() {

        Log.i("vm","start in refreshAccessToken");

        // AccessToken再取得に必要なRefreshToken
        String refreshToken = sharedPreferencesHelper.getRefreshTokenKey();
        disposable.add(
                oAuthServerApiService.refreshAccessToken(refreshToken)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<OAuthToken>(){

                            @Override
                            public void onSuccess(@NonNull OAuthToken oAuthToken) {

                                // RefreshTokenをもとにAccessTokenなどの情報を再取得できた場合
                                Log.i("vm","onSuccess in refreshAccessToken");

                                updateAccessTokenInformation(oAuthToken);

                                setLoginUserRecord();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.i("vm","onError in refreshAccessToken");
                                e.printStackTrace();

                                if (e instanceof HttpException && ((HttpException)e).code()==401) {
                                    accessTokenErrorFlag.setValue(true);
                                } else {
                                    Log.i("vm", "internetRelatedException");
                                    internetAccessErrorFlag.setValue(true);
                                }

                            }
                        })
        );

    }

    /**
     * AIとの対戦終了後（ゲームオーバーした後）に、どちらが勝利したかの対戦成績を更新する。
     * その際、勝利したのがどちらであったのかのデータをリソースサーバーに投げて、結果を更新する。
     * チートを防ぐために、盤面と手の推移の情報も同時に付け加える。
     * @param gameResultRequestResource 勝利したPlayerや、勝利に至るまでの状態の推移の情報
     */
    private void updateLoginUserRecord(GameResultRequestResource gameResultRequestResource) {

        // 通信中であることを伝達するため
        loading.setValue(true);

        disposable.add(
                loginUserRecordApiService.updateLoginUserRecord(gameResultRequestResource)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Map<String, Object>>(){
                            @Override
                            public void onSuccess(Map<String, Object> loginUserRecord){

                                Log.i("vm","onSuccess in LoginAiOthelloViewModel");
                                // AccessTokenは有効だった
                                invalidAccessTokenFlag = false;
                                Log.i("vm","invalidAccessTokenFlag is "+invalidAccessTokenFlag);

                                Log.i("vm",""+loginUserRecord);
                                Log.i("vm",""+loginUserRecord.get("winNum"));
                                Log.i("vm",""+loginUserRecord.get("drawNum"));
                                Log.i("vm",""+loginUserRecord.get("loseNum"));

                                // 冗長
                                Float winNumFloat = Float.parseFloat(loginUserRecord.get("winNum").toString());
                                Float drawNumFloat = Float.parseFloat(loginUserRecord.get("drawNum").toString());
                                Float loseNumFloat = Float.parseFloat(loginUserRecord.get("loseNum").toString());

                                Log.i("vm","winNumFloat: "+winNumFloat);
                                Log.i("vm","drawNumFloat: "+drawNumFloat);
                                Log.i("vm","loseNumFloat: "+loseNumFloat);

                                Integer winNumInteger = winNumFloat.intValue();
                                Integer drawNumInteger = drawNumFloat.intValue();
                                Integer loseNumInteger = loseNumFloat.intValue();

                                Log.i("vm","winNumInteger: "+winNumInteger);
                                Log.i("vm","drawNumInteger: "+drawNumInteger);
                                Log.i("vm","loseNumInteger: "+loseNumInteger);

                                // 対戦成績更新後の情報をフィールドに反映させる
                                winNum.set(String.valueOf(winNumInteger)+"勝");
                                drawNum.set(String.valueOf(drawNumInteger)+"分");
                                loseNum.set(String.valueOf(loseNumInteger)+"負");

                                // 通信が完了したことを伝達するため
                                loading.setValue(false);

                            }
                            @Override
                            public void onError(Throwable e){
                                Log.i("vm","onError in LoginAiOthelloViewModel");
                                Log.i("vm","invalidAccessTokenFlag is "+invalidAccessTokenFlag);
                                e.printStackTrace();

                                if (e instanceof HttpException && ((HttpException)e).code()==401) {
                                    // AccessTokenが有効期限切れだった場合
                                    if (!invalidAccessTokenFlag) {
                                        // 1回目
                                        // ここでフラグをtrueにしておくことで、2回目はelse句に入る
                                        invalidAccessTokenFlag = true;
                                        Log.i("vm","invalidAccessTokenFlag is "+invalidAccessTokenFlag);

                                        // アクセストークン有効期限切れでエラーになりうる。
                                        // アクセストークン更新後、自分自身をもう一度実行する。
                                        refreshAccessTokenAndUpdateLoginUserRecord(gameResultRequestResource);
                                    } else {
                                        accessTokenErrorFlag.setValue(true);
                                    }
                                } else {
                                    Log.i("vm", "internetRelatedException");
                                    internetAccessErrorFlag.setValue(true);
                                }

                            }
                        })
        );
    }

    /**
     * 対戦成績更新APIとの通信時、初回がAccessTokenの有効期限切れだったときに、
     * RefreshTokenをもとにAccessTokenを更新して、もう一度対戦成績更新APIと通信する。
     */
    private void refreshAccessTokenAndUpdateLoginUserRecord(GameResultRequestResource gameResultRequestResource) {

        Log.i("vm","start in refreshAccessToken");

        // AccessToken更新に使用するRefreshTokenを取得
        String refreshToken = sharedPreferencesHelper.getRefreshTokenKey();
        disposable.add(
                oAuthServerApiService.refreshAccessToken(refreshToken)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<OAuthToken>(){

                            @Override
                            public void onSuccess(@NonNull OAuthToken oAuthToken) {

                                Log.i("vm","onSuccess in refreshAccessToken");

                                // AccessToken等の情報について最新の情報を反映
                                updateAccessTokenInformation(oAuthToken);

                                // 再度実行
                                updateLoginUserRecord(gameResultRequestResource);

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.i("vm","onError in refreshAccessToken");
                                e.printStackTrace();

                                if (e instanceof HttpException && ((HttpException)e).code()==401) {
                                    accessTokenErrorFlag.setValue(true);
                                } else {
                                    Log.i("vm", "internetRelatedException");
                                    internetAccessErrorFlag.setValue(true);
                                }

                            }
                        })
        );

    }

    /**
     * タップされた場所の情報を受けとり、Model側でオセロを実行する。
     * 石を置けるか否かの判定もModel側で実施している点に注意。
     * また、毎回、盤面とPlayerや手の情報もgameSituationとして保存している点に注意。
     * @param verticalIndex タップされた場所（縦方向）
     * @param horizontalIndex タップされた場所（横方向）
     */
    @Override
    public void updateGameAndViewIfUpdatable(int verticalIndex, int horizontalIndex){

        // GameSituationの更新ここから
        Gson gson = new Gson();
        int[][] beforeBoardArray = gson.fromJson(gson.toJson(othelloService.getBoardArray()), int[][].class);
        int player = othelloService.getPlayer();
        // GameSituationの更新ここまで

        // OthelloViewModelの実装と同一ここから
        boolean gameUpdateFlag = othelloService.isGameUpdate(verticalIndex, horizontalIndex);

        Log.i("vm", "flag in updateGameAndViewIfUpdatable:"+gameUpdateFlag);
        if (gameUpdateFlag){
            updateView();
            Log.i("vm", "finish updateView");
        }
        // OthelloViewModelの実装と同一ここまで

        // GameSituationの更新ここから
        if (gameUpdateFlag && player==2){
            // GameSituationの更新ここから（白番だった場合）
            Log.i("vm", "player is white. now, we register gameSituation.");
            String verticalIndexString = String.valueOf(verticalIndex);
            String horizontalIndexString = String.valueOf(horizontalIndex);
            String action = verticalIndexString + horizontalIndexString;
            int[][] afterBoardArray = gson.fromJson(gson.toJson(othelloService.getBoardArray()), int[][].class);

            GameSituation gameSituation = new GameSituation();
            gameSituation.setBeforeBoard(beforeBoardArray);
            gameSituation.setPlayer(player);
            gameSituation.setAction(action);
            gameSituation.setAfterBoard(afterBoardArray);
            Log.i("vm", "beforeBoardArray: "+ Arrays.toString(beforeBoardArray));
            Log.i("vm","player: "+player);
            Log.i("vm", "action: "+action);
            Log.i("vm", "afterBoardArray: "+Arrays.toString(afterBoardArray));

            gameSituationList.add(gameSituation);
            Log.i("vm", "gameSituationList: "+gameSituationList);
            Log.i("vm", "gameSituationList.size(): "+gameSituationList.size());
            // GameSituationの更新ここまで（白番だった場合）
        } else if (gameUpdateFlag && player==1){
            // GameSituationの更新ここから（黒番だった場合）
            Log.i("vm", "player is black. now, we register gameSituation.");
            int[][] afterBoardArray = gson.fromJson(gson.toJson(othelloService.getBoardArray()), int[][].class);

            GameSituation gameSituation = gameSituationList.get(gameSituationList.size()-1);
            Log.i("vm", "already registered gameSituation:"+gameSituation);
            gameSituation.setBeforeBoard(beforeBoardArray);
            gameSituation.setPlayer(player);
            gameSituation.setAfterBoard(afterBoardArray);
            Log.i("vm", "beforeBoardArray: "+Arrays.toString(beforeBoardArray));
            Log.i("vm", "player: "+player);
            Log.i("vm","afterBoardPlayer: "+Arrays.toString(afterBoardArray));
            Log.i("vm", "gameSituationList: "+gameSituationList);
            Log.i("vm", "gameSituationList.size(): "+gameSituationList.size());
            // GameSituationの更新ここまで（黒番だった場合）
        }
        // GameSituationの更新ここまで


    }

    /**
     * 白番にて、タップされた場所が石が置ける場合には、石を置き、盤面を更新する。
     * 引き続き黒番にて、AIオセロの処理を開始する。
     * @param verticalIndex 縦方向のタップされた場所
     * @param horizontalIndex 横方向のタップされた場所
     */
    @Override
    public void updateGameSequenceIfUpdatable(int verticalIndex, int horizontalIndex) {

        Log.i("vm verticalIndex", String.valueOf(verticalIndex));
        Log.i("vm horizontalIndex", String.valueOf(horizontalIndex));
        Log.i("vm player", String.valueOf(othelloService.getPlayer()));

        // OthelloViewModelに定義されている
        // もし可能なら、盤面のアップデートを実施する。アップデートした場合には、ビュー用のデータも更新する。
        // もし可能でなかった場合は、盤面のアップデートを実施せずに、処理を終える。
        updateGameAndViewIfUpdatable(verticalIndex, horizontalIndex);
        Log.i("vm", "finish updateGameAndViewIfUpdatable");

        // AIオセロによる更新を実施する。
        // ゲームオーバーであるか否かの判定を実施して、ゲームオーバーでない場合はプレーヤーの判定をする。
        // プレーヤーが黒の場合には、AIオセロによる更新を実施するが、白の場合にはAIオセロによる更新を実施しない。
        updateGameByAiOthelloConsecutively();

    }

    /**
     * 黒番にてAIオセロの処理を実施する。
     * ゲームオーバーではなく、かつ黒番の場合のみ、AIオセロから最良の手を得て、盤面を更新する。
     * ゲームオーバーの場合には、対戦成績更新APIを呼び出す。
     */
    private void updateGameByAiOthelloConsecutively(){

        boolean gameOverFlag = othelloService.isGameOverFlag();

        if(!gameOverFlag){
            // ゲームオーバーでない場合
            int nextPlayer = othelloService.getPlayer();
            int[][] boardArray = othelloService.getBoardArray();

            Log.i("vm","nextPlayer on isGameUpdateByAiOthello "+nextPlayer);
            if(nextPlayer == 1){
                // プレーヤーが1の場合が続くときは、アップデートをしつづける。
                receiveBestMoveAndUpdate(nextPlayer, boardArray);
            }

        } else {
            // ゲームオーバーの場合
            Winner winner = othelloService.getWinner();

            String winnerString;
            if (winner == Winner.BLACK){
                winnerString = "black";
            } else if (winner == Winner.WHITE) {
                winnerString = "white";
            } else {
                winnerString = "draw";
            }

            GameResultRequestResource gameResultRequestResource = new GameResultRequestResource();
            gameResultRequestResource.setGameresult(winnerString);
            gameResultRequestResource.setGameSituationList(gameSituationList);
            updateLoginUserRecord(gameResultRequestResource);

        }
    }

    /**
     * インターネットと通信して。AIオセロから最良の手を取得する。
     * @param nextPlayer 次の石を置くプレーヤー
     * @param boardArray 現在の盤面の情報
     */
    private void receiveBestMoveAndUpdate(int nextPlayer, int[][] boardArray){

        // インターネットとの通信を開始することをViewに伝達するために、フィールドを更新
        loading.setValue(true);

        Log.i("vm","start receiveBestMoveAndUpdate.");
        Gson gson = new Gson();
        String boardArrayString = gson.toJson(boardArray);
        AiOthelloApiRequestBody aiOthelloApiRequestBody = new AiOthelloApiRequestBody(nextPlayer, boardArrayString);

        // 非同期処理実行時に呼び出し元のスレッドが終了した際に、非同期処理のスレッドも終了させるため
        disposable.add(
                // インターネットと通信し、AIオセロから最良手を受け取る（AccessTokenも同時に与える）
                loginUserAiOthelloApiService.getBestMove(aiOthelloApiRequestBody)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<LoginUserAiOthelloApiBestMove>(){
                            @Override
                            public void onSuccess(LoginUserAiOthelloApiBestMove loginUserAiOthelloApiBestMove){

                                invalidAccessTokenFlag = false;

                                Log.i("vm","onSuccess in receiveBestMoveAndUpdate method.");
                                Log.i("vm","invalidAccessTokenFlag is "+invalidAccessTokenFlag);

                                //ここで、receiveBestMoveAndUpdateとして結果を返すのみ。

                                Log.i("vm"," vertical on onSuccess "+String.valueOf(loginUserAiOthelloApiBestMove.bestMove.charAt(0)));
                                Log.i("vm"," horizontal on onSuccess "+String.valueOf(loginUserAiOthelloApiBestMove.bestMove.charAt(1)));
                                Log.i("vm"," bestMoveId on onSuccess "+String.valueOf(loginUserAiOthelloApiBestMove.bestMoveId));

                                // GameSituationの更新ここから（黒番だった場合）
                                GameSituation gameSituation = new GameSituation();
                                String bestMoveId = loginUserAiOthelloApiBestMove.bestMoveId;
                                gameSituation.setAction(bestMoveId);
                                gameSituationList.add(gameSituation);
                                Log.i("vm", "player is black. now, we register gameSituation.");
                                Log.i("vm", "action: "+bestMoveId);
                                Log.i("vm", "gameSituationList: "+gameSituationList);
                                Log.i("vm", "gameSituationList.size(): "+gameSituationList.size());
                                // GameSituationの更新ここまで（黒番だった場合）

                                int verticalIndex = Integer.parseInt(String.valueOf(loginUserAiOthelloApiBestMove.bestMove.charAt(0)));
                                int horizontalIndex = Integer.parseInt(String.valueOf(loginUserAiOthelloApiBestMove.bestMove.charAt(1)));

                                // インターネットとの通信を終了したことをViewに伝達するために、フィールドを更新
                                loading.setValue(false);

                                // 最良手をもとにオセロの盤面を更新
                                updateGameSequenceIfUpdatable(verticalIndex, horizontalIndex);

                            }
                            @Override
                            public void onError(Throwable e){
                                Log.i("vm","onError in receiveBestMoveAndUpdate method.");
                                e.printStackTrace();

                                if (e instanceof HttpException && ((HttpException)e).code()==401) {
                                    // AccessTokenが有効期限切れだった場合
                                    if (!invalidAccessTokenFlag) {
                                        // 1回目
                                        // ここでフラグをtrueにしておくことで、2回目はelse句に入る
                                        invalidAccessTokenFlag = true;
                                        Log.i("vm","invalidAccessTokenFlag is "+invalidAccessTokenFlag);

                                        // アクセストークン有効期限切れでエラーになりうる。
                                        // アクセストークン更新後、自分自身をもう一度実行する。
                                        refreshAccessTokenAndReceiveBestMoveAndUpdate(nextPlayer, boardArray);
                                    } else {
                                        accessTokenErrorFlag.setValue(true);
                                    }
                                } else {
                                    Log.i("vm", "internetRelatedException");
                                    internetAccessErrorFlag.setValue(true);
                                }

                            }
                        })


        );

    }

    /**
     * AIオセロAPIとの通信時、初回がAccessTokenの有効期限切れだったときに、
     * RefreshTokenをもとにAccessTokenを更新して、もう一度AIオセロAPIと通信する。
     */
    private void refreshAccessTokenAndReceiveBestMoveAndUpdate(int nextPlayer, int[][] boardArray) {

        Log.i("vm","start in refreshAccessToken");

        // AccessToken更新に使用するRefreshTokenを取得
        String refreshToken = sharedPreferencesHelper.getRefreshTokenKey();
        disposable.add(
                oAuthServerApiService.refreshAccessToken(refreshToken)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<OAuthToken>(){

                            @Override
                            public void onSuccess(@NonNull OAuthToken oAuthToken) {

                                Log.i("vm","onSuccess in refreshAccessToken");

                                // AccessToken等の情報について最新の情報に更新
                                updateAccessTokenInformation(oAuthToken);

                                // もう一度AIオセロAPIと通信
                                receiveBestMoveAndUpdate(nextPlayer, boardArray);

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.i("vm","onError in refreshAccessToken");
                                e.printStackTrace();

                                if (e instanceof HttpException && ((HttpException)e).code()==401) {
                                    accessTokenErrorFlag.setValue(true);
                                } else {
                                    Log.i("vm", "internetRelatedException");
                                    internetAccessErrorFlag.setValue(true);
                                }

                            }
                        })
        );

    }

    /**
     * onClearedが呼ばれた際に、非同期処理もクリアする
     */
    @Override
    protected void onCleared(){
        super.onCleared();
        disposable.clear();
    }
}
