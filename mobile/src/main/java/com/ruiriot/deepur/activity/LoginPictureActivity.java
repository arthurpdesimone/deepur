package com.ruiriot.deepur.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ruiriot.deepur.ChooseImage;
import com.ruiriot.deepur.R;
import com.ruiriot.deepur.fragment.ChooseImageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;
import static com.ruiriot.deepur.utils.ActivityUtils.hideProgressDialog;
import static com.ruiriot.deepur.utils.ActivityUtils.showProgressDialog;

public class LoginPictureActivity extends BaseActivity implements ChooseImage{

    private StorageReference mStorageRef;

    @BindView(R.id.activity_login_picture_user_email)
    TextView userEmailText;

    @BindView(R.id.activity_login_picture_user_image)
    CircleImageView userImageView;

    @BindView(R.id.activity_login_picture_add)
    FloatingActionButton addPicture;

    @BindView(R.id.activity_login_picture_user_name)
    TextView userName;

    @BindView(R.id.activity_login_picture_done_button)
    TextView nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_picture);

        ButterKnife.bind(this);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        addPicture.setOnClickListener(this);
        nextButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if(i == R.id.activity_login_picture_add){
            BottomSheetDialogFragment bottomSheetDialogFragment = new ChooseImageFragment();
            bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
        }
        if (i == R.id.activity_login_picture_done_button){
            showProgressDialog(this);
            callActivity(LoginPictureActivity.this, HomeActivity.class);
            hideProgressDialog(this);
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
//                    ImageView imageView = (ImageView) findViewById(R.fragment_categories_name.activity_login_picture_user_image);
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

    @Override
    public void OnChooseImage() {

    }
}

