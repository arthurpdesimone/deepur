package com.ruiriot.deepur.activity;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.ruiriot.deepur.R;
import com.ruiriot.deepur.fragment.ChooseImageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;
import static com.ruiriot.deepur.utils.ActivityUtils.hideProgressDialog;
import static com.ruiriot.deepur.utils.ActivityUtils.showProgressDialog;

public class AccountActivity extends BaseActivity {

    @BindView(R.id.activity_account_edit)
    ImageView editNameButton;

    @BindView(R.id.activity_account_name)
    EditText editNameText;

    @BindView(R.id.activity_account_sign_out_rl)
    RelativeLayout signOutButton;

    @BindView(R.id.activity_account_add_picture_fab)
    FloatingActionButton addPictureFab;

    FirebaseAuth mAuth;
    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        editNameButton.setOnClickListener(this);
        signOutButton.setOnClickListener(this);
        addPictureFab.setOnClickListener(this);

        editNameText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
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
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.activity_account_sign_out_rl){
            Log.i("CONTA", "CLICADO");
            callActivity(AccountActivity.this, LoginActivity.class);
            signOut();

        } else if (i == R.id.activity_account_edit) {

            clicked = true;
            final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            editNameText.setEnabled(true);
            imm.showSoftInput(editNameText, InputMethodManager.SHOW_IMPLICIT);
            editNameText.selectAll();
            editNameButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_white_24dp, null));
            editNameButton.setColorFilter(getResources().getColor(R.color.active_icon), PorterDuff.Mode.MULTIPLY);

        } else if(i == R.id.activity_account_add_picture_fab){

            BottomSheetDialogFragment bottomSheetDialogFragment = new ChooseImageFragment();
            bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
        }
    }

    private void signOut() {
        mAuth.signOut();
    }
}
