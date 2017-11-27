package com.ruiriot.deepur.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
        SharedPreferences settings=getSharedPreferences("prefs",0);
        boolean firstRun=settings.getBoolean("firstRun",false);
        if(!firstRun)//if running for first time
        //Splash will load for first time
        {
            SharedPreferences.Editor editor=settings.edit();
            editor.putBoolean("firstRun",true);
            editor.apply();
            Intent i=new Intent(SplashScreenActivity.this, OnBoardingActivity.class);
            startActivity(i);
            finish();
        }
        else
        {
            Intent a = new Intent(SplashScreenActivity.this, HomeActivity.class);
            startActivity(a);
            finish();
        }
    }
}
