package com.beautifulsetouchi.simpleothello.adapter;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.beautifulsetouchi.simpleothello.R;

/**
 * activity_othello.xmlおよびlogin_activity_othello.xmlのUIコンポーネントの属性値に着目して、
 * 属性値に応じて、表示を変更させるために使用するクラス。
 */
public class DataBindingAdapter {

    /**
     * semiTransparentPlayerに"1"または"2"が含まれていた場合、
     * そこにはヒントの石が配置されているので、半透明にする。
     * @param view
     * @param semiTransparentPlayer
     */
    @BindingAdapter("isSemiTransparent")
    public static void setIsSemiTransparent(ImageView view, String semiTransparentPlayer){

        if (semiTransparentPlayer=="1"){
            view.setAlpha(0.2f);
        } else if (semiTransparentPlayer=="2"){
            view.setAlpha(0.2f);
        } else {
            view.setAlpha(1.0f);
        }

    }

    /**
     * activeFlagが"active"という文字列が格納されている場合、
     * そのplayerの手番であることを示すために、背景表示色を黄色にする。
     * @param view
     * @param activeFlag
     */
    @BindingAdapter("textViewBackGround")
    public static void setTextViewBackGround(TextView view, String activeFlag){

        if (activeFlag.equals("active")) {
            view.setBackgroundColor(Color.parseColor("yellow"));
        } else {
            view.setBackgroundColor(Color.parseColor("white"));
        }

    }

    /**
     * 盤面の石の状況に応じて、表示する画像を変更する。
     * 同時に、gameModeに応じて、石の表示を、通常の石の画像か、みきゃんの画像かを変更する。
     * @param view
     * @param player
     * @param gameMode
     */
    @BindingAdapter({"player", "gameMode"})
    public static void setImageResource(ImageView view, String player, String gameMode){

        if (gameMode.equals("Normal") || gameMode.equals("AI")){
            if (player=="1"){
                view.setImageResource(R.drawable.koma_b);
            } else if (player=="2"){
                view.setImageResource(R.drawable.koma_w);
            } else {
                view.setImageResource(android.R.color.transparent);
            }
        } else if (gameMode.equals("Mican")){
            if (player=="1"){
                view.setImageResource(R.drawable.mican_b);
            } else if (player=="2"){
                view.setImageResource(R.drawable.mican_w);
            } else {
                view.setImageResource(android.R.color.transparent);
            }
        }

    }

}