package com.ruiriot.deepur.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.iid.FirebaseInstanceId;
import com.ruiriot.deepur.R;
import com.ruiriot.deepur.model.User;
import com.ruiriot.deepur.view.ViewEvents;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;
import static com.ruiriot.deepur.utils.ActivityUtils.hideProgressDialog;
import static com.ruiriot.deepur.utils.ActivityUtils.showProgressDialog;

/**
 * Created by Rui on 08/05/2017.
 */

public class LoginActivity extends BaseActivity{

    private static final String TAG = "Firebase Login";
    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    private FirebaseAuth mAuth;

    @ViewEvents(clickable = true)
    @BindView(R.id.activity_login_facebook)
    LoginButton mSignInButton;

    @ViewEvents(clickable = true)
    @BindView(R.id.activity_login_sign_out)
    TextView mSignOutButton;

    @BindView(R.id.activity_login_relative)
    RelativeLayout blurryBg;

    @BindView(R.id.activity_login_sign_in)
    TextView loginButton;

    @BindView(R.id.activity_login_create_account)
    TextView createAccountButton;

    @BindView(R.id.activity_login_email_edit_text)
    TextInputEditText userEmail;

    @BindView(R.id.activity_login_password_edit_text)
    TextInputEditText userPassword;

    @BindView(R.id.activity_login_status)
    TextView authStatus;

    @BindView(R.id.activity_login_email_text_input)
    TextInputLayout emailTextInput;

    @BindView(R.id.activity_login_password_text_input)
    TextInputLayout passwordTextInput;

    @BindView(R.id.activity_login_coordinator)
    CoordinatorLayout coordinatorLayout;

    CallbackManager callbackManager;
    AnimationDrawable animationDrawable;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        ButterKnife.bind(this);

        findViewById(R.id.activity_login_facebook).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        animationDrawable = (AnimationDrawable) blurryBg.getBackground();
        animationDrawable.setEnterFadeDuration(300);
        animationDrawable.setExitFadeDuration(500);

        loginButton.setOnClickListener(this);
        createAccountButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

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
        int i = v.getId();

        if (i == R.id.activity_login_create_account){
            callActivity(LoginActivity.this, CreateAccountActivity.class);
        } else if (i == R.id.activity_login_sign_in) {
            signIn(userEmail.getText().toString(), userPassword.getText().toString());
        }
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog(this);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            hideProgressDialog(LoginActivity.this);
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, getResources().getString(R.string.error), Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            authStatus.setText(R.string.activity_login_auth_status);

                        }else hideProgressDialog(LoginActivity.this);
                        // [END_EXCLUDE]
                    }
                });

    }

    private boolean validateForm() {
        boolean valid = true;

        String email = userEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailTextInput.setError(getResources().getString(R.string.activity_login_email_text_input));
            valid = false;
        } else {
            emailTextInput.setError(null);
        }

        String password = userPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordTextInput.setError(getResources().getString(R.string.activity_login_password_text_input));
            valid = false;
        } else {
            passwordTextInput.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {

        hideProgressDialog(LoginActivity.this);

        if (user != null) {

            Intent i = new Intent(LoginActivity.this, AccountActivity.class);
            Bundle extras = new Bundle();
            extras.putString("activity", "login");
            extras.putString("email", user.getEmail());
            i.putExtras(extras);
            startActivity(i);

        } else {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, getResources().getString(R.string.error), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
