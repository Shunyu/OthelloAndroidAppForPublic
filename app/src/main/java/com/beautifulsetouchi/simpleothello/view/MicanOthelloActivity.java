package com.beautifulsetouchi.simpleothello.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.beautifulsetouchi.simpleothello.R;
import com.beautifulsetouchi.simpleothello.databinding.ActivityOthelloBinding;
import com.beautifulsetouchi.simpleothello.viewmodel.MicanOthelloViewModel;

/**
 * 通常のオセロ対戦（黒対白は人間どうし）を実施するActivityクラス。
 * ただし、画面表示が一部異なる。
 */
public class MicanOthelloActivity extends OthelloActivity {

    /**
     * 画面表示が一部異なる通常のオセロ対戦を実施する際に必要な初期化を実施する。
     * onCreateで呼ばれるメソッド。
     */
    @Override
    public void initializeActivityBinding() {

        Log.i("view", "initDataBinding start on MicanOthelloActivity");

        ActivityOthelloBinding activityOthelloBinding = DataBindingUtil.setContentView(this, R.layout.activity_othello);

        othelloViewModel = ViewModelProviders.of(this).get(MicanOthelloViewModel.class);

        activityOthelloBinding.setOthelloViewModel(othelloViewModel);

        // 共通のactivity_othello.xmlには不要なUIコンポーネントが存在。
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);


    }

}
