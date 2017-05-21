package com.ruiriot.deepur.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ruiriot.deepur.exception.BaseException;

import static com.ruiriot.deepur.Constants.PERMISSIONS_REQUEST_CAMERA;
import static com.ruiriot.deepur.Event.post;
import static com.ruiriot.deepur.Event.register;

/**
 * Created by ruiriot on 09-May-17.
 */

public class BaseActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback, View.OnClickListener{

    int resourceLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        register(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){

        if (grantResults.length > 0){

            switch (requestCode){
                case (PERMISSIONS_REQUEST_CAMERA): {

                    break;
                }

            }

        }else {
            post(new BaseException());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }
}
