package com.ruiriot.deepur.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseUser;
import com.ruiriot.deepur.R;
import com.ruiriot.deepur.view.ViewEvents;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import org.w3c.dom.Text;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

/**
 * Created by Rui on 08/05/2017.
 */

public class LoginActivity extends BaseActivity{

    public ProgressDialog mProgressDialog;
    CoordinatorLayout coordinatorLayout;
    public static final String PREF_USER_FIRST_TIME = "user_first_time";

    @ViewEvents(clickable = true)
    @BindView(R.id.activity_login_facebook)
    LoginButton mSignInButton;

    @ViewEvents(clickable = true)
    @BindView(R.id.activity_login_sign_out)
    TextView mSignOutButton;

    @BindView(R.id.activity_login_relative)
    RelativeLayout blurryBg;

    @BindView(R.id.activity_login_skip)
    TextView skipToHome;

    @BindView(R.id.activity_login_create_account)
    TextView createAccountButton;

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        ButterKnife.bind(this);

        findViewById(R.id.activity_login_facebook).setOnClickListener(this);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_login_coordinator);

        skipToHome.setOnClickListener(this);
        createAccountButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.activity_login_create_account){
            callActivity(LoginActivity.this, CreateAccountActivity.class);
        }

        if (i == R.id.activity_login_skip){
            callActivity(LoginActivity.this, LoginPictureActivity.class);
        }
    }
}
