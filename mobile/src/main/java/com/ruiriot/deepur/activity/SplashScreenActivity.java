package com.ruiriot.deepur.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ruiriot.deepur.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                callLoginActivity();
            }
        }, 2000);
    }

    private void callLoginActivity() {
        Intent intent = new Intent(SplashScreenActivity.this,
                OnBoardingActivity.class);
        startActivity(intent);
        finish();
    }
}
