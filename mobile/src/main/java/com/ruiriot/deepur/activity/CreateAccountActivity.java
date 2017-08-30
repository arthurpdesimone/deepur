package com.ruiriot.deepur.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ruiriot.deepur.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;
import static com.ruiriot.deepur.utils.ActivityUtils.hideProgressDialog;
import static com.ruiriot.deepur.utils.ActivityUtils.showProgressDialog;

public class CreateAccountActivity extends BaseActivity {

    @BindView(R.id.activity_create_account_done_button)
    TextView doneButton;
    @BindView(R.id.activity_create_account_cancel_button)
    TextView cancelButton;
    @BindView(R.id.activity_create_account_email_edit_text)
    EditText userEmail;
    @BindView(R.id.activity_create_account_password_edit_text)
    EditText userPassword;
    @BindView(R.id.activity_create_account_name_edit_text)
    EditText userName;
    @BindView(R.id.activity_create_account_email_text_input)
    TextInputLayout userEmailInput;
    @BindView(R.id.activity_create_account_password_text_input)
    TextInputLayout userPasswordInput;
    @BindView(R.id.activity_create_account_name_text_input)
    TextInputLayout userNameInput;
    @BindView(R.id.activity_create_account_image)
    ImageView userImage;
    @BindView(R.id.activity_create_account_status)
    TextView statusCreateAccount;
    private FirebaseAuth mAuth;
    private static final String TAG = "Firebase Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        doneButton.setOnClickListener(this);

    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog(this);

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        hideProgressDialog(CreateAccountActivity.this);
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = userEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            userEmailInput.setError("Required.");
            valid = false;
        } else {
            userEmailInput.setError(null);
        }

        String password = userPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            userPasswordInput.setError("Required.");
            valid = false;
        } else {
            userPasswordInput.setError(null);
        }

        String name = userName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            userNameInput.setError("Required.");
            valid = false;
        } else {
            userNameInput.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {

        hideProgressDialog(this);

        if (user != null) {

            Intent intent = new Intent(this, AccountActivity.class);
            startActivity(intent);

        } else {

            //findViewById(R.id.signed_in_buttons).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.activity_create_account_done_button){
            createAccount(userEmail.getText().toString(), userPassword.getText().toString());
        } else if (i == R.id.activity_create_account_cancel_button){
            finish();
        }else if (i == R.id.activity_create_account_arrow_back){
            finish();
        }
    }
}
