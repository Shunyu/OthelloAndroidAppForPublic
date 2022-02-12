package com.beautifulsetouchi.simpleothello.view;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.beautifulsetouchi.simpleothello.R;

public class GameEndDialog extends DialogFragment {

    private View rootView;
    private OthelloActivity activity;
    private String winnerName;

    public static GameEndDialog newInstance(OthelloActivity activity, String winnerName) {
        GameEndDialog dialog = new GameEndDialog();
        dialog.activity = activity;
        dialog.winnerName = winnerName;
        return dialog;
    }

    // 描画終了後にダイアログがポップアップするようにする。
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.done, ((dialog, which) -> onNewGame()))
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_end_dialog, null, false);
        ((TextView) rootView.findViewById(R.id.tv_winner)).setText(winnerName);
    }

    private void onNewGame() {
        // 意味は？
        // DialogFragmentに定義されているメソッド
        dismiss();
        // アクティビティを閉じるメソッド
        activity.finish();

        //!!AIモードの場合には、2画面前に戻るようにしないといけない。
    }
}
