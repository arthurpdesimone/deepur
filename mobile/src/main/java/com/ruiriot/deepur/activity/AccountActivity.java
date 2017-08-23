package com.ruiriot.deepur.activity;

import android.content.Context;
import android.content.Intent;
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
import com.ruiriot.deepur.fragment.MessengerFragment;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;
import static com.ruiriot.deepur.utils.ActivityUtils.hideProgressDialog;
import static com.ruiriot.deepur.utils.ActivityUtils.showProgressDialog;

public class AccountActivity extends BaseActivity {

    @BindView(R.id.activity_account_edit)
    ImageView editNameButton;

    @BindView(R.id.activity_account_edit_done)
    ImageView editNameTextDone;

    @BindView(R.id.activity_account_name)
    EditText editNameText;

    @BindView(R.id.activity_account_sign_out_card)
    CardView signOutButton;

    @BindView(R.id.activity_account_image)
    CircleImageView addPictureFab;

    @BindView(R.id.activity_account_back_icon)
    ImageView arrowBackButton;

    @BindView(R.id.activity_account_email)
    TextView userEmail;

    @BindView(R.id.activity_account_finish)
    TextView finishButton;

    FirebaseAuth mAuth;
    boolean clicked = false;

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        String activity = intent.getStringExtra("activity");
        extras = getIntent().getExtras();

        if (activity.equals("settings")){
            arrowBackButton.setVisibility(View.VISIBLE);
            arrowBackButton.setOnClickListener(this);
            signOutButton.setVisibility(View.VISIBLE);

            String newStringName;
            String newStringEmail;

            if (savedInstanceState == null) {

                if(extras == null) {
                    newStringName= null;
                    newStringEmail = null;
                } else {
                    newStringName= extras.getString("name");
                    newStringEmail = extras.getString("email");
                    editNameText.setText(newStringName);
                    userEmail.setText(newStringEmail);
                }
            } else {
                newStringName= (String) savedInstanceState.getSerializable("name");
                newStringEmail= (String) savedInstanceState.getSerializable("email");
                editNameText.setText(newStringName);
                userEmail.setText(newStringEmail);
            }

        }else if (activity.equals("login")){
            arrowBackButton.setVisibility(View.GONE);
            signOutButton.setVisibility(View.GONE);
        }

        editNameButton.setOnClickListener(this);
        signOutButton.setOnClickListener(this);
        addPictureFab.setOnClickListener(this);
        arrowBackButton.setOnClickListener(this);
        editNameTextDone.setOnClickListener(this);
        finishButton.setOnClickListener(this);

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
                    editNameButton.setVisibility(View.VISIBLE);
                    editNameTextDone.setVisibility(View.GONE);
                    editNameText.setEnabled(false);

                }
                return handled;
            }
        });

        String newString;

        if (savedInstanceState == null) {

            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("email");
                userEmail.setText(newString);
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("email");
            userEmail.setText(newString);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.activity_account_sign_out_card){

            callActivity(AccountActivity.this, LoginActivity.class);
            signOut();

        } else if (i == R.id.activity_account_edit) {

            clicked = true;
            final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            editNameText.setEnabled(true);
            imm.showSoftInput(editNameText, InputMethodManager.SHOW_IMPLICIT);
            editNameText.selectAll();
            editNameButton.setVisibility(View.GONE);
            editNameTextDone.setVisibility(View.VISIBLE);

        } else if(i == R.id.activity_account_image){

            BottomSheetDialogFragment bottomSheetDialogFragment = new ChooseImageFragment();
            bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());

        } else if (i == R.id.activity_account_back_icon){

            finish();

        } else if(i == R.id.activity_account_edit_done){

            editNameText.setText(editNameText.getText().toString());
            editNameText.setEnabled(false);
            editNameButton.setVisibility(View.VISIBLE);
            editNameTextDone.setVisibility(View.GONE);

        }else if(i == R.id.activity_account_finish){
            Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
            Bundle extrasBundle = new Bundle();
            String userEmail = extras.getString("email");
            String userName = editNameText.getText().toString();
            extras.putString("activity", "account");
            extras.putString("email", userEmail);
            extras.putString("name", userName);
            intent.putExtras(extrasBundle);
            startActivity(intent);
        }
    }

    private void signOut() {
        mAuth.signOut();
    }

}
