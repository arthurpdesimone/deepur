package com.ruiriot.deepur.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ruiriot.deepur.model.User;

/**
 * Created by ruiriot on 30/08/17.
 */

public class ConversationInfoRepository {
    private final FirebaseDatabase database;
    private final DatabaseReference reference;

    public ConversationInfoRepository() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("info-conversation/");
    }

    public void saveInfoConversation(User receiver) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        User user = new User();
        user.setUid(currentUser.getUid());
        user.setName(currentUser.getDisplayName());
        if (currentUser.getPhotoUrl() != null)
            user.setPhotoUrl(currentUser.getPhotoUrl().toString());

        reference.child(user.getUid()).child(receiver.getUid()).setValue(receiver).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("saveInfoConversation", "success");
                } else Log.d("saveInfoConversation", "error");
            }
        });

        reference.child(receiver.getUid()).child(user.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("saveInfoConversation", "success");
                } else Log.d("saveInfoConversation", "error");
            }
        });
    }

    public DatabaseReference getReferenceByUser(String uidCurrentUser) {
        return reference.child(uidCurrentUser);
    }
}