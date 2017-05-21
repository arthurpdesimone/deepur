package com.ruiriot.deepur.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ruiriot.deepur.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**Receive email > Set email on TextView > getUserName > setUserName*/

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.activity_main_header_user_image)
    CircleImageView userImage;

    @BindView(R.id.activity_main_header_user_email)
    TextView userEmailText;

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
        }
    }
}
