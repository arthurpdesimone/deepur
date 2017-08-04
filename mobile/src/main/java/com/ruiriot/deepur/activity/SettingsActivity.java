package com.ruiriot.deepur.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.ruiriot.deepur.R;

import butterknife.BindView;

public class SettingsActivity extends Activity {

    @BindView(R.id.activity_settings_back_icon)
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
