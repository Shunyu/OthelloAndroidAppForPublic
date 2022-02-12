package com.beautifulsetouchi.simpleothello.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.beautifulsetouchi.simpleothello.model.entity.AiOthelloApiBestMove;
import com.beautifulsetouchi.simpleothello.model.entity.AiOthelloApiRequestBody;
import com.beautifulsetouchi.simpleothello.model.service.AiOthelloApiService;
import com.google.gson.Gson;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * AiOthelloActivity用のViewModelクラス
 */
public class AiOthelloViewModel extends OthelloViewModel {

    // AIオセロをログインせずに通信を行うModelの参照
    private AiOthelloApiService aiOthelloApiService;
    // 非同期処理の途中でonClearedが呼ばれた際に、非同期処理のスレッドも終了させるためのインスタンス
    private CompositeDisposable disposable;

    // Viewに通知するもの
    // インターネットへの通信がエラーであるか否か
    // エラーの場合は、Activityにて、自身を終了する
    public MutableLiveData<Boolean> internetAccessErrorFlag = new MutableLiveData<Boolean>();
    // インターネットへの通信中であるか否か
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public AiOthelloViewModel(Application application){

        super(application);

        // gameModeとして"AI"を設定して、activity_othello.xmlのgameMode属性に反映させる。
        // 石の画像を盤面に配置する際には、
        // まず、DataBindingAdapterクラスで、
        // activity_othello.xmlのImageViewのgameMode属性とplayer属性を確認し、
        // 2属性から画像の種類を決めて、
        // ImageViewのImageResourceをセッティングしている。
        gameMode.set("AI");

        aiOthelloApiService = new AiOthelloApiService();
        disposable = new CompositeDisposable();

        // 画面への反映を行うために、フィールドを更新
        generateView();

        // インターネットへの通信中ではない
        loading.setValue(false);

        // 黒番はAIオセロなので、AIオセロの処理を開始
        updateGameByAiOthelloConsecutively();

        Log.i("vm", "AiOthelloViewModelConstructor finish");

    }

    /**
     * 白番にて、タップされた場所が石が置ける場合には、石を置き、盤面を更新する。
     * 引き続き黒番にて、AIオセロの処理を開始する。
     * @param verticalIndex 縦方向のタップされた場所
     * @param horizontalIndex 横方向のタップされた場所
     */
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
     */
    private void updateGameByAiOthelloConsecutively(){

        boolean gameOverFlag = othelloService.isGameOverFlag();

        // ゲームオーバーでない場合のみ実施
        if(!gameOverFlag){
            int nextPlayer = othelloService.getPlayer();
            int[][] boardArray = othelloService.getBoardArray();

            // 黒番の場合のみ実施
            Log.i("vm","nextPlayer on isGameUpdateByAiOthello "+nextPlayer);
            if(nextPlayer == 1){
                receiveBestMoveAndUpdate(nextPlayer, boardArray);
            }
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

        Gson gson = new Gson();
        String boardArrayString = gson.toJson(boardArray);
        AiOthelloApiRequestBody aiOthelloApiRequestBody = new AiOthelloApiRequestBody(nextPlayer, boardArrayString);

        // 非同期処理実行時に呼び出し元のスレッドが終了した際に、非同期処理のスレッドも終了させるため
        disposable.add(
                // インターネットと通信し、AIオセロから最良手を受け取る
                aiOthelloApiService.getBestMove(aiOthelloApiRequestBody)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<AiOthelloApiBestMove>(){
                            @Override
                            public void onSuccess(AiOthelloApiBestMove aiOthelloApiBestMove){

                                Log.i("vm"," vertical on onSuccess "+String.valueOf(aiOthelloApiBestMove.bestMove.charAt(0)));
                                Log.i("vm"," horizontal on onSuccess "+String.valueOf(aiOthelloApiBestMove.bestMove.charAt(1)));

                                int verticalIndex = Integer.parseInt(String.valueOf(aiOthelloApiBestMove.bestMove.charAt(0)));
                                int horizontalIndex = Integer.parseInt(String.valueOf(aiOthelloApiBestMove.bestMove.charAt(1)));

                                // インターネットとの通信を終了したことをViewに伝達するために、フィールドを更新
                                loading.setValue(false);

                                // 最良手をもとにオセロの盤面を更新
                                updateGameSequenceIfUpdatable(verticalIndex, horizontalIndex);

                            }
                            @Override
                            public void onError(Throwable e){
                                e.printStackTrace();
                                Log.i("vm", "internetRelatedException");
                                internetAccessErrorFlag.setValue(true);
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
