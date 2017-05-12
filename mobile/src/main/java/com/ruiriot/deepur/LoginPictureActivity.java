package com.ruiriot.deepur;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;
import static com.ruiriot.deepur.utils.ActivityUtils.hideProgressDialog;
import static com.ruiriot.deepur.utils.ActivityUtils.requestPermission;
import static com.ruiriot.deepur.utils.ActivityUtils.showProgressDialog;

public class LoginPictureActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    Intent intent;
    String userEmail;
    Context context;

    @BindView(R.id.activity_login_picture_user_email)
    TextView userEmailText;

    @BindView(R.id.activity_login_picture_user_image)
    CircleImageView userImageView;

    @BindView(R.id.activity_login_picture_add)
    FloatingActionButton addPicture;

    @BindView(R.id.activity_login_picture_user_name)
    TextView userName;

    @BindView(R.id.activity_login_picture_next_button)
    FloatingActionButton nextButton;

    int PERMISSIONS_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_picture);

        ButterKnife.bind(this);

        intent = getIntent();

        userEmail = intent.getStringExtra(Intent.EXTRA_EMAIL);
        if (userEmail!= null){
            userEmailText.setText(userEmail);
        }

        addPicture.setOnClickListener(this);
        nextButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if(i == R.id.activity_login_picture_add){
            requestPermission(this, Constants.PERMISSIONS_REQUEST_CAMERA , Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (i == R.id.activity_login_picture_next_button){
            showProgressDialog(this);
            callActivity(LoginPictureActivity.this, MainActivity.class);
            hideProgressDialog(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.CAMERA) && permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission granted.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        requestPermission(this, Constants.PERMISSIONS_REQUEST_CAMERA , Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    }
                }
            }
        }

    }
}

