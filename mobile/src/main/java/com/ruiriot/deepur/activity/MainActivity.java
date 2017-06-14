package com.ruiriot.deepur.activity;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.utils.BlurEffectUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

/**Receive email > Set email on TextView > getUserName > setUserName*/

public class MainActivity extends BaseActivity {


    @BindView(R.id.activity_main_header_user_image)
    ImageView userImage;

    @BindView(R.id.activity_main_header_user_name)
    TextView userName;

    @BindView(R.id.activity_main_header_user_email)
    TextView userEmailText;

    @BindView(R.id.activity_main_header_blur)
    View userBlur;

    @BindView(R.id.activity_main_header_bg)
    View userBg;

    @BindView(R.id.activity_main_header_settings_icon)
    ImageView settingsButton;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        intent = getIntent();

        if (savedInstanceState == null) {
            // Set the local night mode to some value
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
            // Now recreate for it to take effect
            recreate();
        }

        final String userEmail = intent.getStringExtra(Intent.EXTRA_EMAIL);
        if (userEmail!= null){
            userEmailText.setText(userEmail);
        }

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.rui);
        Palette.from(image).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch = palette.getDominantSwatch();
                if (swatch != null) {
                    int color = swatch.getRgb();
                    int nameTextColor = swatch.getTitleTextColor();
                    int emailTextColor = swatch.getBodyTextColor();
                    int settingsColor = swatch.getTitleTextColor();
                    int h = userBg.getWidth();
                    ShapeDrawable mDrawable = new ShapeDrawable(new RectShape());
                    mDrawable.getPaint().setShader(new LinearGradient(h, 1, 1, 0, color, getResources().getColor(R.color.transparent), Shader.TileMode.REPEAT));
                    userBg.setBackground(mDrawable);
                    userName.setTextColor(nameTextColor);
                    userEmailText.setTextColor(emailTextColor);
                    settingsButton.setColorFilter(settingsColor);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callActivity(getApplicationContext(), SettingsActivity.class);
            }
        });
    }

    private void blurView(){
        final Application activity = getApplication();
        final View content = findViewById(android.R.id.content).getRootView();
        if (content.getWidth() > 0) {
            Bitmap image = BlurEffectUtils.blur(content);
            userBg.setBackground(new BitmapDrawable(activity.getResources(), image));
        } else {
            content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    Bitmap image = BlurEffectUtils.blur(content);
                    userBlur.setBackground(new BitmapDrawable(activity.getResources(), image));
                }
            });
        }
    }
}
