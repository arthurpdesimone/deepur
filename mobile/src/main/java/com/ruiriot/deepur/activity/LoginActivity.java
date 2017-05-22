package com.ruiriot.deepur.activity;

import android.app.Application;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ruiriot.deepur.R;
import com.ruiriot.deepur.view.ViewEvents;
import com.ruiriot.deepur.utils.BlurEffectUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

/**
 * Created by Rui on 08/05/2017.
 */

public class LoginActivity extends BaseActivity{

    private static final String TAG = "EmailPassword";
    FirebaseAuth mAuth;
    public ProgressDialog mProgressDialog;

    @BindView(R.id.activity_login_email)
    EditText mEmailField;

    @BindView(R.id.activity_login_password)
    EditText mPasswordField;

    @ViewEvents(clickable = true)
    @BindView(R.id.activity_login_sign_in)
    TextView mSignInButton;

    @ViewEvents(clickable = true)
    @BindView(R.id.activity_login_sign_out)
    TextView mSignOutButton;

    @ViewEvents(clickable = true)
    @BindView(R.id.activity_login_create_account)
    TextView mCreateAccountButton;

    @BindView(R.id.activity_login_relative)
    RelativeLayout blurryBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.activity_login_create_account).setOnClickListener(this);
        findViewById(R.id.activity_login_sign_in).setOnClickListener(this);

        Bitmap image = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_person_black_48dp);
        Palette.from(image).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                if (vibrantSwatch != null) {
                    Drawable drawable = getResources().getDrawable(R.drawable.bg_gradient_login);
                    int color = vibrantSwatch.getRgb();
                    int h = blurryBg.getHeight();
                    ShapeDrawable mDrawable = new ShapeDrawable(new RectShape());
                    mDrawable.getPaint().setShader(new LinearGradient(0, 0, 0, h, color, getResources().getColor(android.R.color.transparent), Shader.TileMode.REPEAT));
                    blurryBg.setBackgroundDrawable(mDrawable);
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        if(mAuth == null){
            mAuth = FirebaseAuth.getInstance();
        }else {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null){
                callActivity(this, LoginPictureActivity.class);
            }
            updateUI(currentUser);
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.activity_login_create_account) {

            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());

        } else if (i == R.id.activity_login_sign_in) {

            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());

        } else if (i == R.id.activity_login_sign_out) {

            signOut();

        } else if (i == R.id.activity_login_verify_email) {

            sendEmailVerification();
        }
    }

    private void blurView(){
        final Application activity = getApplication();
        final View content = findViewById(android.R.id.content).getRootView();
        if (content.getWidth() > 0) {
            Bitmap image = BlurEffectUtils.blur(content);
            blurryBg.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), image));
        } else {
            content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Bitmap image = BlurEffectUtils.blur(content);
                    blurryBg.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), image));
                }
            });
        }
    }

    private void createAccount(String email, String password) {
        findViewById(R.id.activity_login_verify_email).setEnabled(true);

        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();
//
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            callActivity(LoginActivity.this, LoginPictureActivity.class);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }

    private void signIn(String email, String password) {

        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            callActivity(LoginActivity.this, LoginPictureActivity.class);
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication succeed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]

    }

    private void signOut() {
        mAuth.signOut();
        mEmailField.setText(null);
        mPasswordField.setText(null);
        updateUI(null);
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    private void sendEmailVerification() {
        // Disable button
        findViewById(R.id.activity_login_verify_email).setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){

            user.sendEmailVerification()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // [START_EXCLUDE]
                            // Re-enable button
                            findViewById(R.id.activity_login_verify_email).setEnabled(true);

                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this,
                                        "Verification email sent to " + user.getEmail(),
                                        Toast.LENGTH_SHORT).show();
                                callActivity(LoginActivity.this, LoginPictureActivity.class);
                            } else {
                                Log.e(TAG, "sendEmailVerification", task.getException());
                                Toast.makeText(LoginActivity.this,
                                        "Failed to send verification email.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            // [END_EXCLUDE]
                        }
                    });
            // [END send_email_verification]

        }else {
            Toast.makeText(LoginActivity.this,
                    "Make sure you are logged in or have an account.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {


        }
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

}
