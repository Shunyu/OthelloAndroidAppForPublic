package com.beautifulsetouchi.simpleothello.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beautifulsetouchi.simpleothello.R;
import com.beautifulsetouchi.simpleothello.databinding.ActivityOthelloBinding;
import com.beautifulsetouchi.simpleothello.databinding.LoginActivityOthelloBinding;
import com.beautifulsetouchi.simpleothello.viewmodel.LoginAiOthelloViewModel;

/**
 * AIモードのオセロ対戦（黒がAIオセロ）を実施するActivityクラス
 * ログインする場合。
 */
public class LoginAiOthelloActivity extends OthelloActivity {

    LoginAiOthelloViewModel loginAiOthelloViewModel;

    /**
     * AIモードのオセロ対戦時（ログインする場合）に必要な初期化を実施する。
     * onCreateで呼ばれるメソッド。
     */
    @Override
    public void initializeActivityBinding() {

        Log.i("view", "initDataBinding start in LoginAiOthelloActivity");

       // login_activity_othello.xmlで変数と式を利用できるようにするため。
        LoginActivityOthelloBinding loginActivityOthelloBinding = DataBindingUtil.setContentView(this, R.layout.login_activity_othello);

        // フィールドにViewModelを格納する。
        loginAiOthelloViewModel = ViewModelProviders.of(this).get(LoginAiOthelloViewModel.class);
        othelloViewModel = loginAiOthelloViewModel;

        loginActivityOthelloBinding.setLoginAiOthelloViewModel(loginAiOthelloViewModel);

        // WebAPIと通信しているタイミングのみに表示するprogressBarを見えない状態にする。
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        // ログイン済ユーザーの対戦成績をWebAPI経由で取得させる。
        Log.i("view", "start setLoginUserRecord in LoginAiOthelloActivity");
        loginAiOthelloViewModel.setLoginUserRecord();

        // AIオセロをスタートさせる。
        Log.i("view", "start startAiOthelloGame in LoginAiOthelloActivity");
        loginAiOthelloViewModel.startAiOthelloGame();

        // internetAccessErrorが起こった際は、終了し、オセロ選択Activityに戻る。
        loginAiOthelloViewModel.internetAccessErrorFlag.observe(this, internetAccessErrorFlag ->{
            if (internetAccessErrorFlag) {
                Toast.makeText(this, R.string.internet_access_error, Toast.LENGTH_LONG).show();
                finish();
            }
        });

        // accessTokenの有効期限が切れていた場合（refreshTokenを利用しても有効期限外の場合）は、終了し、オセロ選択Activityに戻る。
        // 再ログインが必要なので、その旨を表示する。
        loginAiOthelloViewModel.accessTokenErrorFlag.observe(this, accessTokenErrorFlag ->{
            if(accessTokenErrorFlag) {
                Toast.makeText(this, R.string.refresh_token_error, Toast.LENGTH_LONG).show();
                finish();
            }
        });

        // WebAPIとの通信状況に応じて、progressBarの状態を変更する。
        loginAiOthelloViewModel.loading.observe(
                this, loadingFlag ->{
                    if (loadingFlag) {
                        Log.i("view", "APIへのアクセス中。");
                        progressBar.setVisibility(View.VISIBLE);
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
        );
    }

}
