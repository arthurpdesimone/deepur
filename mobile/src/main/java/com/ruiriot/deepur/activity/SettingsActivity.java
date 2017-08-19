package com.ruiriot.deepur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruiriot.deepur.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.activity_settings_name)
    TextView userName;

    @BindView(R.id.activity_settings_account_card)
    CardView accountButton;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        String newNameString;
        String newEmailString;

        if (savedInstanceState == null) {

            if(extras == null) {
                newNameString= null;
                newEmailString= null;
            } else {
                newNameString= extras.getString("name");
                newEmailString = extras.getString("email");
                userName.setText(newNameString);
            }
        } else {
            newNameString= (String) savedInstanceState.getSerializable("name");
            newEmailString= (String) savedInstanceState.getSerializable("email");
            userName.setText(newNameString);
        }

        accountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int i = v.getId();
        if (i == R.id.activity_settings_account_card){
            Log.i("Account", "CLICOU");
            Intent intent = new Intent(SettingsActivity.this, AccountActivity.class);
            Bundle extrasBundle = new Bundle();
            String userNameText = extras.getString("name");
            String userEmailText = extras.getString("email");
            extrasBundle.putString("activity", "settings");
            extrasBundle.putString("name", userNameText);
            extrasBundle.putString("email", userEmailText);
            intent.putExtras(extrasBundle);
            startActivity(intent);
        }
    }

}
