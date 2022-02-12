package com.beautifulsetouchi.simpleothello.viewmodel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;
import android.util.Log;

import com.beautifulsetouchi.simpleothello.di.DaggerViewModelComponent;
import com.beautifulsetouchi.simpleothello.model.entity.OthelloResultStatus;
import com.beautifulsetouchi.simpleothello.model.usecase.OthelloService;
import com.beautifulsetouchi.simpleothello.model.constant.Winner;

import javax.inject.Inject;

import static com.beautifulsetouchi.simpleothello.utilities.StringUtility.stringFromNumbers;

/**
 * OthelloActivity用のViewModelクラス
 * タップされたボタンに応じた処理を記載。
 * タップされた際には、モデルを更新する。
 * モデルの更新完了後に、フィールドを更新する。
 */
public class OthelloViewModel extends AndroidViewModel {


    // Viewに通知するもの
    // 現在のオセロ盤の盤面の石の情報
    public ObservableArrayMap<String, String> boardMap;
    // 現在のオセロ盤の黒石・白石の数
    public ObservableArrayMap<String, String> stoneNumMap;
    // 現在の手番（黒番か白番か）
    public ObservableArrayMap<String, String> activePlayerMap;
    // オセロ盤で、次に石がおける場所の情報
    public ObservableArrayMap<String, String> semiTransparentPlayerMap;
    // オセロゲームのモード（通常・みきゃん・AIオセロ）。ただしAIオセロには、ログイン版・未ログイン版があり。
    public ObservableField<String> gameMode;
    // オセロの結果（勝者・ゲームを初期状態に戻すか否か・ゲームが終了したか否か）に関する情報
    private MutableLiveData<OthelloResultStatus> othelloResultStatus;

    // Modelの参照
    // オセロの盤面情報、およびロジックを実装したクラスのインスタンスを注入する。
    // オセロの盤面情報の取得やロジックを利用する際のファサードとなる。
    @Inject
    public OthelloService othelloService;

    public OthelloViewModel(Application application){
        super(application);

        // 依存性の注入
        DaggerViewModelComponent.create().inject(this);

        boardMap = new ObservableArrayMap<>();
        stoneNumMap = new ObservableArrayMap<>();
        activePlayerMap = new ObservableArrayMap<>();
        semiTransparentPlayerMap = new ObservableArrayMap<>();
        othelloResultStatus = new MutableLiveData<>();
        gameMode = new ObservableField<>();

        Log.i("vm", "OthelloViewModelConstructor finish");
    }


    /**
     * Viewのために、現在のオセロ盤の盤面の石の情報を初期化する。
     * 次に石が置ける場所（ヒント）の情報を表示した際には、
     * boardMapにはヒント石の場所の情報も格納されているので、一旦初期化している。
     */
    private void clearBoardMap(){
        boardMap.clear();
    }

    /**
     * Viewのために、オセロ盤で、次に石がおける場所（ヒント）の情報を初期化する。
     */
    private void clearSemiTransparentPlayerMap(){
        semiTransparentPlayerMap.clear();
    }

    private void clearView(){
        clearBoardMap();
        clearSemiTransparentPlayerMap();
    }

    /**
     * Viewのために、現在のオセロ盤の盤面の石の情報を更新する。
     * @param boardArray ModelであるothelloServiceに格納されている現在のオセロ盤の状況を表すint[][]
     */
    private void updateBoardMap(int[][] boardArray){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if (boardArray[i][j] == 1){
                    boardMap.put(stringFromNumbers(i,j), "1");
                } else if(boardArray[i][j] == 2){
                    boardMap.put(stringFromNumbers(i,j), "2");
                }
            }
        }
    }

    /**
     * 現在の盤面の情報をModelであるothelloServiceから取得して、フィールドを更新する。
     */
    private void updateBoardStone(){
        int[][] boardArray = othelloService.getBoardArray();
        updateBoardMap(boardArray);
    }

    /**
     * 現在の手番の情報をモデルから取得して、フィールドを更新する。
     */
    private void updateOthelloStatus(){

        String textViewBlackText = "黒 : " + String.valueOf(othelloService.getBlackStoneNum());
        stoneNumMap.put("black", textViewBlackText);
        String textViewWhiteText = "白 : " + String.valueOf(othelloService.getWhiteStoneNum());
        stoneNumMap.put("white", textViewWhiteText);

        if(othelloService.getPlayer() == 1){
            Log.i("activePlayer_1", String.valueOf(othelloService.getPlayer()));
            activePlayerMap.put("black", "active");
            activePlayerMap.put("white", "non-active");
        } else {
            Log.i("activePlayer_2", String.valueOf(othelloService.getPlayer()));
            activePlayerMap.put("black", "non-active");
            activePlayerMap.put("white", "active");
        }
    }

    /**
     * Viewを更新すべきタイミングに呼ぶメソッド、モデルの情報を元に、フィールドを更新する。
     */
    public void generateView(){

        // オセロ盤の情報を更新
        updateBoardStone();
        Log.i("vm updateBoardStone", "finish");

        // オセロの状態の更新
        updateOthelloStatus();
        Log.i("vm updateOseroStatus", "finish");

    }

    /**
     * オセロの結果（勝者・ゲームを初期状態に戻すか否か・ゲームが終了したか否か）に関する情報を
     * モデルから取得して、フィールドに反映させる。
     */
    private void updateOthelloResultStatus(){

        Log.i("vm","updateOthelloResultStatus start!");

        Winner latestWinner = othelloService.getWinner();
        boolean latestGameOverFlag = othelloService.isGameOverFlag();
        boolean latestRestartFlag = othelloService.isRestartFlag();

        Log.i("vm","latest winner is "+latestWinner);
        Log.i("vm","latest gameOverFlag is "+latestGameOverFlag);
        Log.i("vm","latest restartFlag is "+latestRestartFlag);

        if (latestGameOverFlag|latestRestartFlag) {
            Log.i("vm","othelloResultStatus update");
            OthelloResultStatus latestOthelloResultStatus = new OthelloResultStatus(latestWinner, latestGameOverFlag, latestRestartFlag);
            othelloResultStatus.setValue(latestOthelloResultStatus);
        }
        Log.i("vm","updateOthelloResultStatus finish!");
    }

    /**
     * オセロの結果（勝者・ゲームを初期状態に戻すか否か・ゲームが終了したか否か）に関する情報を
     * View側で確認できるようにする。
     * @return othelloResultStatus オセロの結果（勝者・ゲームを初期状態に戻すか否か・ゲームが終了したか否か）に関する情報を格納
     */
    public LiveData<OthelloResultStatus> getOthelloResultStatus(){

        Log.i("vm","getOthelloResultStatus");

        return othelloResultStatus;
    }

    /**
     * フィールドに格納していた盤面の情報を初期化し、
     * モデルに基づいた最新の情報をフィールドに反映させ、
     * オセロの結果に関する情報もフィールドに反映させる。
     */
    public void updateView(){

        clearView();
        Log.i("vm", "clearView finish on updateView");

        generateView();
        Log.i("vm", "generateView finish on updateView");

        updateOthelloResultStatus();
        Log.i("vm", "updateOthelloResultStatus finish on updateView");
    }

    /**
     * タップされた場所の情報を受けとり、Model側でオセロを実行する。
     * 石を置けるか否かの判定もModel側で実施している点に注意。
     * @param verticalIndex タップされた場所（縦方向）
     * @param horizontalIndex タップされた場所（横方向）
     */
    public void updateGameAndViewIfUpdatable(int verticalIndex, int horizontalIndex){

        // Modelにて石が置ける場合には、石を置いて、盤面を変化させる。
        // そうでない場合には盤面を変化させない。
        // ViewModelでは、その変化の有無の情報を受け取って、変化があった場合には、フィールドを更新する。
        boolean gameUpdateFlag = othelloService.isGameUpdate(verticalIndex, horizontalIndex);

        Log.i("vm", "flag in updateGameAndViewIfUpdatable:"+gameUpdateFlag);
        if (gameUpdateFlag){
            updateView();
            Log.i("vm", "finish updateView");
        }

    }

    /**
     * updateGameAndViewIfUpdatableメソッドと同等の処理を実施する。
     * 継承先のクラスの一部では、オーバーライドして、処理内容を修正する。
     * @param verticalIndex
     * @param horizontalIndex
     */
    public void updateGameSequenceIfUpdatable(int verticalIndex, int horizontalIndex) {

        Log.i("vm verticalIndex", String.valueOf(verticalIndex));
        Log.i("vm horizontalIndex", String.valueOf(horizontalIndex));
        Log.i("vm player", String.valueOf(othelloService.getPlayer()));

        updateGameAndViewIfUpdatable(verticalIndex, horizontalIndex);
        Log.i("vm", "finish updateGameAndViewIfUpdatable");

    }

    /**
     * オセロ盤で、次に石がおける場所（ヒント）の情報として、半透明の石を表示させたい。
     * Viewのために、ヒントの石を表示すべき場所の情報をフィールドに反映させる。
     */
    private void updateBoardHintStone(){
        int[][] hintArray = othelloService.getHintArray();
        updateBoardMap(hintArray);
    }

    /**
     * オセロ盤で、次に石がおける場所（ヒント）の情報として、半透明の石を表示させたい。
     * Viewのために、石のうち半透明にすべき場所の情報をsemiTransparentMapに反映させる。
     */
    private void updateSemiTransparentPlayerMap(){
        int[][] alphaArray = othelloService.getHintArray();

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                Log.i("updateAlphaMap",String.valueOf(alphaArray[i][j])+" at ("+String.valueOf(i)+","+String.valueOf(j)+")");

                if (alphaArray[i][j] == 1){
                    semiTransparentPlayerMap.put(stringFromNumbers(i,j), "1");
                    Log.i("updateAlphaMap","1 at ("+String.valueOf(i)+","+String.valueOf(j)+") in if");
                } else if(alphaArray[i][j] == 2){
                    semiTransparentPlayerMap.put(stringFromNumbers(i,j), "2");
                    Log.i("updateAlphaMap","2 at ("+String.valueOf(i)+","+String.valueOf(j)+") in else if");
                } else {
                    semiTransparentPlayerMap.put(stringFromNumbers(i, j), "0");
                }
            }
        }

    }

    /**
     * Viewのために、ヒントの石を表示すべき場所の情報をフィールドに反映させる（ファサード）。
     */
    private void updateHintView(){
        updateBoardHintStone();
        updateSemiTransparentPlayerMap();
    }

    /**
     * Viewで、ヒントが欲しいというボタンがタップされた際に実行する。
     */
    public void getHint(){

        Log.i("vm","getHint is tapped");
        updateHintView();

    }

    /**
     * Viewで、ゲームを終了したいというボタンがタップされた際に実行する。
     */
    public void restartOthello(){

        Log.i("vm","start restartOthello");

        othelloService.restartGame();
        updateOthelloResultStatus();

        return;

    }

}
