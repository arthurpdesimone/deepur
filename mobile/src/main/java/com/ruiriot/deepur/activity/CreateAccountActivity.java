package com.ruiriot.deepur.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ruiriot.deepur.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

public class CreateAccountActivity extends BaseActivity {

    @BindView(R.id.activity_create_account_done_button)
    TextView doneButton;
    @BindView(R.id.activity_create_account_cancel_button)
    TextView cancelButton;
    @BindView(R.id.activity_create_account_email_edit_text)
    EditText userEmail;
    @BindView(R.id.activity_create_account_password_edit_text)
    EditText userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);
        doneButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.activity_create_account_done_button){
            callActivity(CreateAccountActivity.this, HomeActivity.class);
        }
    }
}
