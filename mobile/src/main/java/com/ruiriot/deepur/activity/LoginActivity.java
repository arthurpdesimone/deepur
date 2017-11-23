package com.ruiriot.deepur.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.ruiriot.deepur.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;
import static com.ruiriot.deepur.utils.ActivityUtils.hideProgressDialog;
import static com.ruiriot.deepur.utils.ActivityUtils.showProgressDialog;

/**
 * Created by Rui on 08/05/2017.
 */

public class LoginActivity extends BaseActivity{

    public static final String PREF_USER_FIRST_TIME = "user_first_time";

    private static final int RC_SIGN_IN = 9001;

    @BindView(R.id.activity_login_relative)
    RelativeLayout blurryBg;

    @BindView(R.id.activity_login_sign_in_google)
    TextView loginButton;

    @BindView(R.id.activity_login_create_account)
    TextView createAccountButton;

    @BindView(R.id.activity_login_email_edit_text)
    TextInputEditText userEmail;

    @BindView(R.id.activity_login_password_edit_text)
    TextInputEditText userPassword;

    @BindView(R.id.activity_login_status)
    TextView authStatus;

    @BindView(R.id.activity_login_coordinator)
    CoordinatorLayout coordinatorLayout;

    public GoogleSignInClient mGoogleSignInClient;
    public GoogleSignInAccount account;

    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        animationDrawable = (AnimationDrawable) blurryBg.getBackground();
        animationDrawable.setEnterFadeDuration(300);
        animationDrawable.setExitFadeDuration(500);

//        SharedPreferences.Editor editor = getSharedPreferences("unique_name", MODE_PRIVATE).edit();
//        editor.putInt("xp", 10);
//        editor.commit();

        loginButton.setOnClickListener(this);
        createAccountButton.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning())
            animationDrawable.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning())
            animationDrawable.stop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //Create account button
            case R.id.activity_login_create_account:
                callActivity(LoginActivity.this, CreateAccountActivity.class);
                break;

            // Google Sign in button
            case R.id.activity_login_sign_in_google:
                signIn();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            account = completedTask.getResult(ApiException.class);
            updateUI();
        } catch (ApiException e) {
            Log.w("Login", "signInResult:failed code=" + e.getStatusCode());
            updateUI();
        }
    }

    private void updateUI() {

        hideProgressDialog(LoginActivity.this);

        if (account != null) {

            Intent i = new Intent(LoginActivity.this, AccountActivity.class);
            startActivity(i);

        } else {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, getResources().getString(R.string.error), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
