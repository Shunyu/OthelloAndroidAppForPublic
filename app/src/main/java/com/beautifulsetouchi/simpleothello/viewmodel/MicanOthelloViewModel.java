package com.beautifulsetouchi.simpleothello.viewmodel;

import android.app.Application;
import android.util.Log;

/**
 * MicanOthelloActivity用のViewModelクラス
 */
public class MicanOthelloViewModel extends OthelloViewModel {

    public MicanOthelloViewModel(Application application){

        super(application);

        // gameModeとして"Mican"を設定して、activity_othello.xmlのgameMode属性に反映させる。
        // 石の画像を盤面に配置する際には、
        // まず、DataBindingAdapterクラスで、
        // activity_othello.xmlのImageViewのgameMode属性とplayer属性を確認し、
        // 2属性から画像の種類を決めて、
        // ImageViewのImageResourceをセッティングしている。
        gameMode.set("Mican");

        // 画面への反映を行うために、フィールドを更新
        generateView();

        Log.i("vm", "MicanOthelloViewModelConstructor finish");

    }
}
