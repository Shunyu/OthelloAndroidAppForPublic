package com.beautifulsetouchi.simpleothello.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.beautifulsetouchi.simpleothello.R;
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * アプリ起動直後に立ち上がるActivityクラス（オセロモード選択Activity）
 * 画面上のボタンをタップした際に、startXXOthelloメソッド, startLicenseActivityメソッドが呼ばれ、別のActivityを起動する。
 * これらのメソッドはactivity_main.xmlにて、ButtonのonClick属性に登録済み。
 */
public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 通常のオセロ対戦（黒対白は人間どうし）を実行する際のActivityを起動する。
     */
    public void startNormalOthello(View v){

        Intent i = new Intent(this, com.beautifulsetouchi.simpleothello.view.NormalOthelloActivity.class);
        startActivity(i);

    }

    /**
     * 通常のオセロ対戦（黒対白は人間どうし）を実施するActivityを起動する。
     * ただし、石の表示が一部異なる。
     */
    public void startMicanOthello(View v){

        Intent i = new Intent(this, com.beautifulsetouchi.simpleothello.view.MicanOthelloActivity.class);
        startActivity(i);

    }

    /**
     * AIオセロにて、ログインして利用するか、ログインせずに利用するかを選択するActivityを起動する。
     */
    public void startAiOthello(View v){

        Toast.makeText(this, R.string.internet_alert_description, Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, LoginLogoutDeterminationActivity.class);
        startActivity(i);

    }

    /**
     * ライセンス表示を行うActivityを起動する。
     * ただし、全てを網羅しているわけではないので、修正が必要。
     */
    public void startLicenseActivity(View v) {

        Intent i = new Intent(this, OssLicensesMenuActivity.class);
        startActivity(i);
    }
}
