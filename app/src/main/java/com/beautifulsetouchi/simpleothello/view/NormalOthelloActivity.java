package com.beautifulsetouchi.simpleothello.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beautifulsetouchi.simpleothello.R;
import com.beautifulsetouchi.simpleothello.databinding.ActivityOthelloBinding;
import com.beautifulsetouchi.simpleothello.viewmodel.NormalOthelloViewModel;

/**
 * 通常のオセロ対戦（黒対白は人間どうし）を実行する際のActivityクラス
 */
public class NormalOthelloActivity extends OthelloActivity {

    /**
     * 通常のオセロ対戦時に必要な初期化を実施する。
     * onCreateの中で実行される。
     */
    @Override
    public void initializeActivityBinding() {

        Log.i("view", "initDataBinding start on NormalOthelloActivity");

        // activity_othello.xmlで変数と式を使用できるようにするため。
        ActivityOthelloBinding activityOthelloBinding = DataBindingUtil.setContentView(this, R.layout.activity_othello);

        // フィールドにViewModelを格納する。
        othelloViewModel = ViewModelProviders.of(this).get(NormalOthelloViewModel.class);

        activityOthelloBinding.setOthelloViewModel(othelloViewModel);

        // 共通のactivity_othello.xmlには、不要なUIコンポーネントが存在。
        TextView micanDescription = findViewById(R.id.micanDescription);
        micanDescription.setVisibility(View.GONE);

        // 共通のactivity_othello.xmlには、不要なUIコンポーネントが存在。
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

    }

}
