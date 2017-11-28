package com.ruiriot.deepur.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import com.ruiriot.deepur.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DayNightModeActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.activity_day_night_mode_status)
    TextView dayNightStatus;

    @BindView(R.id.activity_day_night_mode_switch)
    Switch dayNightSwitch;

    @BindView(R.id.activity_day_night_mode_back)
    ImageView dayNightBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_night_mode);

        ButterKnife.bind(this);

        dayNightBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if(i == R.id.activity_day_night_mode_back){
            finish();
        }
    }
}
