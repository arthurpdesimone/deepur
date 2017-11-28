package com.ruiriot.deepur.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.ruiriot.deepur.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.activity_settings_name)
    TextView userName;

    @BindView(R.id.activity_settings_account_email)
    TextView userEmail;

    @BindView(R.id.activity_settings_account)
    RelativeLayout accountButton;

    @BindView(R.id.activity_settings_back_icon)
    ImageView arrowBackButton;

    @BindView(R.id.activity_settings_image)
    CircleImageView userImage;

    @BindView(R.id.activity_settings_day_night)
    RelativeLayout dayNight;

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
            userEmail.setText(account.getEmail());

            Uri imageUri = account.getPhotoUrl();
            Glide
                    .with(this)
                    .load(imageUri)
                    .into(userImage);
        }

        accountButton.setOnClickListener(this);
        arrowBackButton.setOnClickListener(this);
        dayNight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        if (i == R.id.activity_settings_account){
            Intent intent = new Intent(SettingsActivity.this, AccountActivity.class);
            startActivity(intent);

        }else if (i == R.id.activity_settings_back_icon){
            finish();
        }else if(i == R.id.activity_settings_day_night){
            Intent intent = new Intent(SettingsActivity.this, DayNightModeActivity.class);
            startActivity(intent);
        }
    }

}
