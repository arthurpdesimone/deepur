package com.ruiriot.deepur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String userEmail;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = getIntent();

//        userEmail = intent.getStringExtra(Intent.EXTRA_EMAIL);
//        TextView userEmailText = (TextView) findViewById(R.id.activity_main_user_email);
//        userEmailText.setText(userEmail);

    }
}
