package com.ruiriot.deepur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;

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

        intent = getIntent();

        String userEmail = intent.getStringExtra(Intent.EXTRA_EMAIL);
        if (userEmail!= null){
            userEmailText.setText(userEmail);
        }

        String userName = intent.getStringExtra(Intent.EXTRA_USER);
        if (userName!= null){
            userNameText.setText(userName);
        }
    }
}
