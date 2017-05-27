package com.ruiriot.deepur.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruiriot.deepur.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**Receive email > Set email on TextView > getUserName > setUserName*/

public class MainActivity extends BaseActivity {


    @BindView(R.id.activity_main_header_user_image)
    ImageView userImage;

    @BindView(R.id.activity_main_header_user_email)
    TextView userEmailText;

    @BindView(R.id.activity_main_header_rl)
    RelativeLayout userBg;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        intent = getIntent();

        if (savedInstanceState == null) {
            // Set the local night mode to some value
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            // Now recreate for it to take effect
            recreate();
        }

        String userEmail = intent.getStringExtra(Intent.EXTRA_EMAIL);
        if (userEmail!= null){
            userEmailText.setText(userEmail);
        }

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_person_black_48dp);
        Palette.from(image).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                if (vibrantSwatch != null) {
                    userBg.setBackgroundColor(vibrantSwatch.getRgb());
                }
            }
        });
    }
}
