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
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.HomeAdapter;
import com.ruiriot.deepur.utils.BlurEffectUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

/**Receive email > Set email on TextView > getUserName > setUserName*/

public class HomeActivity extends BaseActivity {


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

    @BindView(R.id.activity_home_recycler_view)
    RecyclerView mRecyclerView;

    Intent intent;

    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
                    int h = userBg.getHeight();
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

        setupRecycler();
    }

    private void setupRecycler() {

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        mAdapter = new HomeAdapter(new ArrayList<>(0));
        mRecyclerView.setAdapter(mAdapter);
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
