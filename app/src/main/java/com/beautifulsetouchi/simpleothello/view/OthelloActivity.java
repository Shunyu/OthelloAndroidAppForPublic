package com.beautifulsetouchi.simpleothello.view;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.beautifulsetouchi.simpleothello.model.entity.OthelloResultStatus;
import com.beautifulsetouchi.simpleothello.model.constant.Winner;
import com.beautifulsetouchi.simpleothello.viewmodel.OthelloViewModel;

/**
 * 具象クラスであるXXOthelloActivityクラスに共通する処理を抽出した抽象クラス
 * フィールドにはothelloViewModelを保持する
 * othelloViewModelのフィールドothelloResultStatus（勝者・ゲームを初期状態に戻すか否か・ゲームが終了したか否か）を監視する。
 * その状態の変化に応じて、ダイアログを表示する。
 */
public abstract class OthelloActivity extends AppCompatActivity {



    private static final String GAME_END_DIALOG_TAG = "game_end_dialog_tag";
    private static final String BLACK_WINNER = "黒";
    private static final String WHITE_WINNER = "白";
    private static final String NO_WINNER = "なし";
    OthelloViewModel othelloViewModel;

    public abstract void initializeActivityBinding();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("view ","OthelloActivity onCreate Start!");

        // 具象クラスごとの個別処理を記載
        initializeActivityBinding();

        // othelloViewモデルのフィールドothelloResultStatusの状態の変化を監視
        setUpOnOthelloResultListener();
    }


    void setUpOnOthelloResultListener(){
        Log.i("view","setUpOnOthelloResultListener start");
        othelloViewModel.getOthelloResultStatus().observe(this, this::onOthelloStatusChanged);
    }

    /**
     * othelloResultStatusの状態の変化に応じて、ダイアログを表示する。
     */
    @VisibleForTesting
    private void onOthelloStatusChanged(OthelloResultStatus othelloResultStatus){

        Log.i("view","onOthelloStatusChanged start");

        Winner winner = othelloResultStatus.getWinner();
        boolean gameOverFlag = othelloResultStatus.isGameOverFlag();
        boolean restartFlag = othelloResultStatus.isRestartFlag();

        if (restartFlag) {

            Log.i("view", "restartFlag on onOthelloStatusChanged true");
            String winnerName = NO_WINNER;

            GameEndDialog dialog = GameEndDialog.newInstance(this, winnerName);
            dialog.setCancelable(false);
            dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);

        }

        if (gameOverFlag){

            Log.i("view", "gameOverFlag on onOthelloStatusChanged true");

            String winnerName = NO_WINNER;

            if (winner==Winner.BLACK){
                winnerName = BLACK_WINNER;
            } else if (winner==Winner.WHITE){
                winnerName = WHITE_WINNER;
            }

            GameEndDialog dialog = GameEndDialog.newInstance(this, winnerName);
            dialog.setCancelable(false);
            dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);
        }

    }

}
