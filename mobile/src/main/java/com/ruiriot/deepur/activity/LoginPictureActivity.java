package com.ruiriot.deepur.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import com.ruiriot.deepur.Constants;
import com.ruiriot.deepur.R;
import com.ruiriot.deepur.fragment.ChooseImageFragment;

import java.io.File;

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
    TextView nextButton;

    private static final int TAKE_PICTURE = 1;
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
            requestPermission(this, Constants.PERMISSIONS_REQUEST_CAMERA , Manifest.permission.CAMERA);
        }
        if (i == R.id.activity_login_picture_next_button){
            showProgressDialog(this);
            callActivity(LoginPictureActivity.this, HomeActivity.class);
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

                if (permission.equals(Manifest.permission.CAMERA)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
//                        takePhoto(userImageView);
                        BottomSheetDialogFragment bottomSheetDialogFragment = new ChooseImageFragment();
                        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                    } else {
                        requestPermission(this, Constants.PERMISSIONS_REQUEST_CAMERA , Manifest.permission.CAMERA);
                    }
                }
            }
        }

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case TAKE_PICTURE:
//                if (resultCode == Activity.RESULT_OK) {
//                    Uri selectedImage = imageUri;
//                    getContentResolver().notifyChange(selectedImage, null);
//                    ImageView imageView = (ImageView) findViewById(R.id.activity_login_picture_user_image);
//                    ContentResolver cr = getContentResolver();
//                    Bitmap bitmap;
//                    try {
//                        bitmap = android.provider.MediaStore.Images.Media
//                                .getBitmap(cr, selectedImage);
//
//                        imageView.setImageBitmap(bitmap);
//                        Toast.makeText(this, selectedImage.toString(),
//                                Toast.LENGTH_LONG).show();
//                    } catch (Exception e) {
//                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
//                                .show();
//                        Log.e("Camera", e.toString());
//                    }
//                }
//        }
//    }

    public Uri takePhoto(View view) {
        Uri imageUri;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);
        return imageUri;
    }
}

