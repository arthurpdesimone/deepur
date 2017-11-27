package com.ruiriot.deepur.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ruiriot.deepur.R;
import com.ruiriot.deepur.fragment.ChooseImageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

public class AccountActivity extends BaseActivity {

    @BindView(R.id.activity_account_edit)
    ImageView editNameButton;

    @BindView(R.id.activity_account_edit_done)
    ImageView editNameTextDone;

    @BindView(R.id.activity_account_name)
    EditText editNameText;

    @BindView(R.id.activity_account_sign_out_text)
    TextView signOutButton;

    @BindView(R.id.activity_account_image)
    CircleImageView userImageProfile;

    @BindView(R.id.activity_account_back_icon)
    ImageView arrowBackButton;

    @BindView(R.id.activity_account_email)
    TextView userEmail;

    boolean clicked = false;
    DatabaseReference myUserRef;
    FirebaseDatabase database;
    public GoogleSignInClient mGoogleSignInClient;
    public GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        myUserRef = database.getReference("users/");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null) {
            arrowBackButton.setVisibility(View.VISIBLE);
            arrowBackButton.setOnClickListener(this);
            signOutButton.setVisibility(View.VISIBLE);

            editNameText.setText(account.getDisplayName());
            userEmail.setText(account.getEmail());
            Uri imageUri = account.getPhotoUrl();
            Glide
                    .with(this)
                    .load(imageUri)
                    .into(userImageProfile);
        }

        editNameButton.setOnClickListener(this);
        signOutButton.setOnClickListener(this);
        userImageProfile.setOnClickListener(this);
        arrowBackButton.setOnClickListener(this);
        editNameTextDone.setOnClickListener(this);

        editNameText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    String userName = editNameText.getText().toString();
                    editNameText.setText(userName);
                    imm.hideSoftInputFromWindow(editNameText.getWindowToken(), 0);
                    handled = true;
                    editNameButton.setVisibility(View.VISIBLE);
                    editNameTextDone.setVisibility(View.GONE);
                    editNameText.setEnabled(false);

                }
                return handled;
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        /*if (i == R.id.activity_account_sign_out_card){

            callActivity(AccountActivity.this, LoginActivity.class);
            mGoogleSignInClient.signOut();

        }*/
        if (i == R.id.activity_account_sign_out_text){

            callActivity(AccountActivity.this, HomeActivity.class);

        }else if (i == R.id.activity_account_edit) {

            clicked = true;
            final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            editNameText.setEnabled(true);
            imm.showSoftInput(editNameText, InputMethodManager.SHOW_IMPLICIT);
            editNameText.selectAll();
            editNameButton.setVisibility(View.GONE);
            editNameTextDone.setVisibility(View.VISIBLE);

        } else if(i == R.id.activity_account_image){

            BottomSheetDialogFragment bottomSheetDialogFragment = new ChooseImageFragment();
            bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());

        } else if (i == R.id.activity_account_back_icon){

            finish();

        } else if(i == R.id.activity_account_edit_done){

            editNameText.setText(editNameText.getText().toString());
            editNameText.setEnabled(false);
            editNameButton.setVisibility(View.VISIBLE);
            editNameTextDone.setVisibility(View.GONE);

        }
    }
}
