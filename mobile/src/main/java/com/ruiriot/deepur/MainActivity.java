package com.ruiriot.deepur;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiriot.deepur.utils.ActivityUtils.extractColor;
import static com.ruiriot.deepur.utils.ActivityUtils.getUserName;

/**Receive email > Set email on TextView > getUserName > setUserName*/

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_user_email)
    TextView userEmailText;

    @BindView(R.id.activity_main_user_name)
    TextView userNameText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        intent = getIntent();

        String userEmail = intent.getStringExtra(Intent.EXTRA_EMAIL);
        if (userEmail!= null){
            userEmailText.setText(userEmail);
            String userName = getUserName(this);
            userNameText.setText(userName);
        }
    }
}
