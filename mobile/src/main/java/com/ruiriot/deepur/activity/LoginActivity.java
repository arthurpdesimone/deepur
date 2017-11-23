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

    private static final String TAG = "Firebase Login";
    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    private FirebaseAuth mAuth;
    String providerId;
    String uid;
    String uName;
    String uEmail;
    Uri uPhotoUrl;

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

    AnimationDrawable animationDrawable;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {

                providerId = profile.getProviderId();
                uid = profile.getUid();
                uName = profile.getDisplayName();
                uEmail = profile.getEmail();
                uPhotoUrl = profile.getPhotoUrl();
            }
        } else {
            Snackbar mySnackBar = Snackbar.make(findViewById(R.id.activity_login_coordinator),
                    R.string.activity_login_user_not_found, Snackbar.LENGTH_SHORT);
            mySnackBar.show();
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

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
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
        } else if (i == R.id.activity_login_sign_in_google) {
            signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            //updateUI(account); TODO
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    /*private void signIn(String email, String password) {
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

    }*/

    private boolean validateForm() {
        boolean valid = true;

        String email = userEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            userEmail.setError(getResources().getString(R.string.activity_login_email_text_input));
            valid = false;
        } else {
            userEmail.setError(null);
        }

        String password = userPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            userPassword.setError(getResources().getString(R.string.activity_login_password_text_input));
            valid = false;
        } else {
            userPassword.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {

        hideProgressDialog(LoginActivity.this);

        if (user != null) {

            Intent i = new Intent(LoginActivity.this, AccountActivity.class);
            startActivity(i);

        } else {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, getResources().getString(R.string.error), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
