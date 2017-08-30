package com.ruiriot.deepur.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ruiriot.deepur.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiriot on 30/08/17.
 */

public class PeopleRepository {

    DatabaseReference databaseReference;

    public PeopleRepository() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child
                ("friend-list");
    }

    public DatabaseReference getReferenceByUser(String uid) {
        return databaseReference.child(uid);
    }

    public void getFriendsIdFromUser(String stringUid, final OnRequestFriendsId onRequestFriendsId) {
        databaseReference.child(stringUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> contactsIds = new ArrayList<>();
                // Recover all the items from the database that match with the filter
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    contactsIds.add(dsp.getKey());
                }
                onRequestFriendsId.onRequestFriendsIdSuccess(contactsIds);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("getFriendsIdFromUser", databaseError.getMessage());
            }
        });
    }

    public void saveNewFriend(User user, final OnSaveNewFriend onSaveNewFriend) {
        DatabaseReference referenceByUser = getReferenceByUser(FirebaseAuth.getInstance().getCurrentUser().getUid());
        referenceByUser.child(user.getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    onSaveNewFriend.onSaveNewFriendSuccess();
                } else
                    Log.e("saveNewFriend", task.getException().getMessage());
            }
        });

    }


    public interface OnRequestFriendsId {
        void onRequestFriendsIdSuccess(List<String> strings);
    }

    public interface OnSaveNewFriend {
        void onSaveNewFriendSuccess();
    }


}