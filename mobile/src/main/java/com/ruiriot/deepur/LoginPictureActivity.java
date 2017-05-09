package com.ruiriot.deepur;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginPictureActivity extends AppCompatActivity {

    Intent intent;
    String userEmail;
    String userName;
    ImageView userImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_picture);

        intent = getIntent();

        userEmail = intent.getStringExtra(Intent.EXTRA_EMAIL);
        TextView userEmailText = (TextView) findViewById(R.id.activity_login_user_email);
        userEmailText.setText(userEmail);

        userImageView = (ImageView) findViewById(R.id.activity_login_picture_user_image);

    }
}

