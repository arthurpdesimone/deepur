package com.ruiriot.deepur.activity;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruiriot.deepur.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

public class ProfileActivity extends Activity {

    @BindView(R.id.activity_profile_edit)
    ImageView editNameButton;

    @BindView(R.id.activity_profile_name)
    EditText editNameText;

    @BindView(R.id.activity_profile_account_card)
    CardView signOutButton;

    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        editNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicked = true;
                editNameText.setEnabled(true);
                imm.showSoftInput(editNameText, InputMethodManager.SHOW_IMPLICIT);
                editNameText.selectAll();
                editNameButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_white_24dp, null));
                editNameButton.setColorFilter(getResources().getColor(R.color.active_icon), PorterDuff.Mode.MULTIPLY);

            }
        });

        editNameText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    String userName = editNameText.getText().toString();
                    editNameText.setText(userName);
                    imm.hideSoftInputFromWindow(editNameText.getWindowToken(), 0);
                    handled = true;
                    editNameText.setEnabled(false);
                    editNameButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_white_24dp, null));
                    editNameButton.setColorFilter(getResources().getColor(R.color.active_icon), PorterDuff.Mode.MULTIPLY);
                }
                return handled;
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callActivity(ProfileActivity.this, LoginActivity.class);
            }
        });
    }
}
