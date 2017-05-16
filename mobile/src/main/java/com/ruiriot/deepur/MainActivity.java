package com.ruiriot.deepur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ruiriot.deepur.utils.ActivityUtils.getUserName;
import static com.ruiriot.deepur.utils.ImageUtils.extractColor;

/**Receive email > Set email on TextView > getUserName > setUserName*/

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_user_email)
    TextView userEmailText;

    @BindView(R.id.activity_main_user_name)
    TextView userNameText;

    @BindView(R.id.activity_main_header_user_image)
    CircleImageView userImage;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        intent = getIntent();

        String userEmail = intent.getStringExtra(Intent.EXTRA_EMAIL);
        if (userEmail!= null){
            userEmailText.setText(userEmail);
            String userName = getUserName(this);
            userNameText.setText(userName);
        }

        Palette.Swatch swatch = extractColor(getApplicationContext(), R.id.activity_main_header_user_image);
        Log.i("SWATCH" , "" + swatch.getRgb());
        userEmailText.setTextColor(swatch.getRgb());
    }
}
