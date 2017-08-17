package com.ruiriot.deepur.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.ruiriot.deepur.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.activity_settings_account)
    RelativeLayout accountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callActivity(SettingsActivity.this, AccountActivity.class);
            }
        });

    }

}
