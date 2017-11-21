package com.ruiriot.deepur.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.ruiriot.deepur.R;
import com.ruiriot.deepur.fragment.ChooseImageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;
import static com.ruiriot.deepur.utils.ActivityUtils.hideProgressDialog;
import static com.ruiriot.deepur.utils.ActivityUtils.showProgressDialog;

public class CreateAccountActivity extends BaseActivity {

    @BindView(R.id.activity_create_account_done_button)
    TextView doneButton;
    @BindView(R.id.activity_create_account_cancel_button)
    TextView cancelButton;
    @BindView(R.id.activity_create_account_arrow_back)
    ImageView arrowBack;
    @BindView(R.id.activity_create_account_email_edit_text)
    EditText userEmail;
    @BindView(R.id.activity_create_account_password_edit_text)
    EditText userPassword;
    @BindView(R.id.activity_create_account_name_edit_text)
    EditText userName;
    @BindView(R.id.activity_create_account_image)
    CircleImageView userImage;
    private FirebaseAuth mAuth;
    FirebaseStorage storage;
    private DatabaseReference ref;
    private static final String TAG = "Firebase Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("users");
        storage = FirebaseStorage.getInstance();

        doneButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        arrowBack.setOnClickListener(this);

    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog(this);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            if (user != null){
                                ref.child(user.getUid()).setValue(String.class);
                            }
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
            userEmail.setError("Required.");
            valid = false;
        } else {
            userEmail.setError(null);
        }

        String password = userPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            userPassword.setError("Required.");
            valid = false;
        } else {
            userPassword.setError(null);
        }

        String name = userName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            userName.setError("Required.");
            valid = false;
        } else {
            userName.setError(null);
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
        }
        if (i == R.id.activity_create_account_cancel_button){
            finish();
        }
        if (i == R.id.activity_create_account_arrow_back){
            finish();
        }
        if (i == R.id.activity_create_account_image) {
            Log.i("CREATEACCOUNTIMAGE", "CLICOU");
        }
    }
}
