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

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        ButterKnife.bind(this);

        findViewById(R.id.activity_login_facebook).setOnClickListener(this);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_login_coordinator);
        //mSignInButton.setReadPermissions("email");

        skipToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callActivity(getApplicationContext(), LoginPictureActivity.class);
            }
        });

       /* mSignInButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {
                showErrorSnackBar();
            }

            @Override
            public void onError(FacebookException exception) {
                showErrorSnackBar();
            }
        });*/
//
//        Bitmap image = BitmapFactory.decodeResource(getResources(),
//                R.drawable.autumn);
//        Palette.from(image).generate(new Palette.PaletteAsyncListener() {
//            public void onGenerated(Palette palette) {
//                Palette.Swatch vibrantSwatch = palette.getLightVibrantSwatch();
//                if (vibrantSwatch != null) {
//                    Drawable drawable = getResources().getDrawable(R.drawable.bg_gradient_login);
//                    int color = vibrantSwatch.getRgb();
//                    int h = blurryBg.getHeight();
//                    ShapeDrawable mDrawable = new ShapeDrawable(new RectShape());
//                    mDrawable.getPaint().setShader(new LinearGradient(0, 0, 0, h, getResources().getColor(android.R.color.transparent), color, Shader.TileMode.REPEAT));
//                    blurryBg.setBackgroundDrawable(mDrawable);
//                }
//            }
//        });

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();

    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.login_loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showErrorSnackBar(){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, getResources().getString(R.string.error), Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
