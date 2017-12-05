package com.ruiriot.deepur.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ruiriot.deepur.R;
import com.ruiriot.deepur.model.MessageP2P;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rui Donizete on 05/12/17.
 */

public class ChatActivity extends BaseActivity {

    @BindView(R.id.activity_messenger_chat_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.activity_messenger_chat_send)
    ImageView sendButton;

    @BindView(R.id.activity_messenger_chat_edit_text)
    EditText inputMessageEditText;

    DatabaseReference myUserRef;
    FirebaseDatabase database;
    public GoogleSignInClient mGoogleSignInClient;
    public GoogleSignInAccount account;
    String userId;
    String userName;
    Uri userPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger_chat);

        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        myUserRef = database.getReference("users/");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null){
            userId = account.getId();
            userName = account.getDisplayName();
            userPhoto = account.getPhotoUrl();
        }

        sendButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        // Send Button
        if(i == R.id.activity_messenger_chat_send){
            FirebaseDatabase.getInstance()
                    .getReference()
                    .push()
                    .setValue(new MessageP2P(userId, userName, inputMessageEditText.getText().toString(), userPhoto));

            inputMessageEditText.setText("");
        }
    }
}
