package com.beautifulsetouchi.simpleothello.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beautifulsetouchi.simpleothello.R;
import com.beautifulsetouchi.simpleothello.databinding.ActivityOthelloBinding;
import com.beautifulsetouchi.simpleothello.viewmodel.AiOthelloViewModel;

/**
 * AIモードのオセロ対戦（黒がAIオセロ）を実施するActivityクラス
 * ただし、ログインはしない場合。
 */
public class AiOthelloActivity extends OthelloActivity {

    AiOthelloViewModel aiOthelloViewModel;

    /**
     * AIモードのオセロ対戦時（ログインしない場合）に必要な初期化を実施する。
     * onCreateで呼ばれるメソッド。
     */
    @Override
    public void initializeActivityBinding() {

        Log.i("view", "initDataBinding start on AiOthelloActivity");

        // activity_othello.xmlで変数と式を使用できるようにするため。
        ActivityOthelloBinding activityOthelloBinding = DataBindingUtil.setContentView(this, R.layout.activity_othello);

        // フィールドにViewModelを格納する。
        aiOthelloViewModel = ViewModelProviders.of(this).get(AiOthelloViewModel.class);
        othelloViewModel = aiOthelloViewModel;

        activityOthelloBinding.setOthelloViewModel(othelloViewModel);

        // 共通のactivity_othello.xmlには不要なUIコンポーネントが存在。
        TextView micanDescription = findViewById(R.id.micanDescription);
        micanDescription.setVisibility(View.GONE);

        // WebAPIと通信をしているタイミングのみ表示するprogressBarを見えない状態にする。
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        // internetAccessErrorが起こった際は、終了し、オセロモード選択Activityに戻る。
        aiOthelloViewModel.internetAccessErrorFlag.observe(this, internetAccessErrorFlag ->{
           if (internetAccessErrorFlag) {
               Toast.makeText(this, R.string.internet_access_error, Toast.LENGTH_LONG).show();
               finish();
           }
        });

        // WebAPIとの通信状況に応じて、progressBarの状態を変更する。
        aiOthelloViewModel.loading.observe(
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
