package com.beautifulsetouchi.simpleothello.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
//import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.beautifulsetouchi.simpleothello.R;
import com.beautifulsetouchi.simpleothello.model.oauth.OAuthKeyForPkceGenerator;
import com.beautifulsetouchi.simpleothello.viewmodel.LoginLogoutDeterminationViewModel;

/**
 * AIモードをログインして実行するか、ログインせずに実行するかを決定する際に利用するActivityクラス
 */
public class LoginLogoutDeterminationActivity extends AppCompatActivity {

    // 認可コード取得では、
    // 認可サーバーにWebブラウザでアクセスした後に、認可処理を行い、
    // 認可された後に、アプリにリダイレクトしてくる。
    // アプリにリダイレクトしてきた際には、URLの情報が与えられる。
    // URLには認可コードの情報が"code=XX"という形式で格納されているため、この定数を導入した。
    private static final String AUTHORIZATION_CODE_QUERY_PARAMETER = "code";

    LoginLogoutDeterminationViewModel loginLogoutDeterminationViewModel;
    // 認可コードフロー+PKCEでは、codeVerifierとcodeChallengeの組が必要となるため、それを生成するためのインスタンス
    // Activityではなく、ViewModelで必要なので、削除すべき。
    OAuthKeyForPkceGenerator oAuthKeyForPkceGenerator;
    // ログインボタン・ログアウトボタンを含むLayout。WebAPIとの通信時には隠す。
    LinearLayout buttonLayout;
    // WebAPIとの通信時には表示する。
    ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_logout_determination);
        loginLogoutDeterminationViewModel = ViewModelProviders.of(this).get(LoginLogoutDeterminationViewModel.class);
        oAuthKeyForPkceGenerator = new OAuthKeyForPkceGenerator();
        buttonLayout = (LinearLayout) findViewById(R.id.determinationButtonList);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        observerViewModel();

    }

    /**
     * loginLogoutDeterminationViewModelのフィールドの変化を監視する。
     */
    private void observerViewModel() {

        // loginLogoutDeterminationViewModelで認可サーバーの認可エンドポイントが生成されたタイミングで、
        // ウェブブラウザ経由で人なサーバーにアクセスする。
        loginLogoutDeterminationViewModel.loginUriStringLiveData.observe(
                this, loginUriString -> {
                    if(loginUriString != null){
                        Log.i("activity", "loginUriString is not null.");

                        Uri loginUri = Uri.parse(loginUriString);
                        // 暗黙的インテント
                        // loginUriはアクセスすべき認可エンドポイント
                        Intent intent = new Intent(Intent.ACTION_VIEW, loginUri);
                        if (intent.resolveActivity(getPackageManager()) != null){
                            // Webブラウザにて認可サーバーにアクセスし認可をおこなう。
                            // アプリ側にリダイレクトしてくるようにしている。
                            Log.i("activity", "start startActivity(implicit intent)");
                            startActivity(intent);
                            Log.i("activity", "finish startActivity(implicit intent)");
                        }

                    }
                }
        );

        // accessTokenがsharedPreferencesに格納されたタイミングで、ログイン版AIオセロを起動する準備が完了する。
        // ログイン版のAIモードのオセロ対戦（黒がAIオセロ）を実施するActivityを起動する。
        loginLogoutDeterminationViewModel.accessTokenSavedFlag.observe(
                this, accessTokenSavedFlag -> {
                    if(accessTokenSavedFlag){
                        Log.i("activity", "accessToken is not null.");
                        Intent intent = new Intent(LoginLogoutDeterminationActivity.this, com.beautifulsetouchi.simpleothello.view.LoginAiOthelloActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );

        // WebAPIと通信を実施してるか否かに合わせて表示を変更させる。
        loginLogoutDeterminationViewModel.loading.observe(
                this, loadingFlag ->{

                    if(loadingFlag){
                        // APIとの通信をしているとき
                        buttonLayout.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                    } else {
                        // APIとの通信をしていないとき
                        buttonLayout.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }
        );

        // 認可サーバーにおいて、エラーなくログオフが実施できた際には、通常のAIオセロを起動する。
        // もともと、認可サーバーにログインしていなかった場合にも、logoffErrorはfalseとなる。
        loginLogoutDeterminationViewModel.logoffError.observe(
                this, logoffError -> {
                    if(!logoffError){
                        // 通常のAIオセロを呼び出す。
                        Log.i("activity", "logoffError is false.");
                        Log.i("activity", "start startActivity(i) on loginLogoutDeterminationViewModel.logoffError.observe");
                        Intent i = new Intent(LoginLogoutDeterminationActivity.this, com.beautifulsetouchi.simpleothello.view.AiOthelloActivity.class);
                        startActivity(i);
                        Log.i("activity", "finish startActivity(i) on loginLogoutDeterminationViewModel.logoffError.observe");
                        finish();
                    } else {
                        Log.i("activity", "logoffError is true.");
                    }
                }
        );

    }

    /**
     * loginLogoutDeterminationViewModelにて、ログイン処理を開始する。
     * 処理が進捗すれば、loginLogoutDeterminationViewModelのフィールドの情報が変更になる。
     * これらのメソッドはactivity_login_logout_determination.xmlにて、ButtonのonClick属性に登録済み。
     */
    public void startLogin(View view){

        Log.i("activity", "start startLogin");
        loginLogoutDeterminationViewModel.startLogin();
        Log.i("activity", "finish startLogin");

    }

    /**
     * loginLogoutDeterminationViewModelにて、ログアウト処理を開始する。
     * 処理が進捗すれば、loginLogoutDeterminationViewModelのフィールドの情報が変更になる。
     * これらのメソッドはactivity_login_logout_determination.xmlにて、ButtonのonClick属性に登録済み。
     */
    public void startLogoff(View view){

        Log.i("activity", "start startLogoff");
        loginLogoutDeterminationViewModel.startLogoff();
        Log.i("activity", "finish startLogoff");

    }

    /**
     * 通常のライフサイクルでのonResumeではifの条件がFalseとなり、なにも実施しない。
     * 認可サーバーからリダイレクトしてきた場合には、ifの条件がTrueとなる。
     * 認可コードを利用した、アクセストークンの取得をloginLogoutDeterminationViewModelに依頼する。
     */
    @Override
    protected void onResume() {

        Log.i("activity", "start onResume in LoginLogoutDeterminationActivity");
        super.onResume();

        Uri data = getIntent().getData();
        Log.i("activity", "data from getIntent().getData() onResume: "+data);

        if (data != null && !TextUtils.isEmpty(data.getScheme())){
            // 認可サーバーからのリダイレクトの場合

            // 認可コード
            String authorizationCode = data.getQueryParameter(AUTHORIZATION_CODE_QUERY_PARAMETER);
            Log.i("activity", "authorizationCode onResume: "+authorizationCode);

            if (!TextUtils.isEmpty(authorizationCode)) {

                // 認可コードを引数に与えて、loginLogoutDeterminationViewModelにアクセストークンの取得を依頼する。
                loginLogoutDeterminationViewModel.getAccessToken(authorizationCode);

            } else {
                Toast.makeText(this,
                        getString(R.string.code_error), Toast.LENGTH_LONG).show();
                finish();
            }
        }
        Log.i("model", "finish onResume in LoginLogoutDeterminationActivity");
    }

}
