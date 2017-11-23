package com.ruiriot.deepur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.ruiriot.deepur.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.activity_settings_name)
    TextView userName;

    @BindView(R.id.activity_settings_account)
    RelativeLayout accountButton;

    @BindView(R.id.activity_settings_back_icon)
    ImageView arrowBackButton;

    public GoogleSignInClient mGoogleSignInClient;
    public GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null){
            arrowBackButton.setVisibility(View.VISIBLE);
            arrowBackButton.setOnClickListener(this);

            userName.setText(account.getDisplayName());

        }

        accountButton.setOnClickListener(this);
        arrowBackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        if (i == R.id.activity_settings_account){
            Intent intent = new Intent(SettingsActivity.this, AccountActivity.class);
            startActivity(intent);

        }else if (i == R.id.activity_settings_back_icon){
            finish();
        }
    }

}
