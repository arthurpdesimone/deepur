package com.ruiriot.deepur.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ruiriot.deepur.model.MessageP2P;
import com.ruiriot.deepur.model.User;

import java.util.Calendar;

/**
 * Created by ruiriot on 30/08/17.
 */

public class MessageRepository {

    private FirebaseDatabase database;
    private DatabaseReference reference;

    public MessageRepository() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("messages/");
    }


    public void getReferenceP2P(final FirebaseUser currentUser, final User mReceiver, final OnReferenceFetch onReferenceFetch) {
        reference.child("p2p/").orderByKey().equalTo(currentUser.getUid() + "-" + mReceiver.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() != null)
                            onReferenceFetch.onGetReference(reference.child("p2p/" + currentUser.getUid() + "-" + mReceiver.getUid()));
                        else
                            onReferenceFetch.onGetReference(reference.child("p2p/" + mReceiver.getUid() + "-" + currentUser.getUid()));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public DatabaseReference getReference() {
        return reference;

    }

    public void sendMessagePerToPer(final MessageP2P messageP2P, final User mReceiver, DatabaseReference reference) {
        messageP2P.setTimestamp(Calendar.getInstance().getTimeInMillis());
        reference.push()
                .setValue(messageP2P).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    ConversationInfoRepository conversationInfoRepository = new ConversationInfoRepository();
                    conversationInfoRepository.saveInfoConversation(mReceiver);
                } else
                    Log.e("saveOnReceiver", task.getException().getMessage());
            }
        });
    }

    public interface OnReferenceFetch {
        void onGetReference(DatabaseReference ref);
    }
}
