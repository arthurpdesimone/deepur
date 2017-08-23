package com.ruiriot.deepur.fragment;

/**
 * Created by ruiri on 03-Jul-17.
 */

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ruiriot.deepur.R;
import com.ruiriot.deepur.model.Messenger;
import com.ruiriot.deepur.model.Notification;


public class NotificationsFragment extends BaseFragment{

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    private String id;
    Context context;

    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("notification");

        mAuth = FirebaseAuth.getInstance();

        //userName = holder.getUserName().toString();
        //userText = holder.getUserText().toString();
        //userImage = holder.getUserImageView().toString();
        //timeStamp = holder.getTimeStamp().toString();
        //unreadMessages = holder.getUnreadMessages().toString();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Notification notification = dataSnapshot.getValue(Notification.class);
                id = dataSnapshot.getRef().getKey();

                if (notification == null){
                    Toast.makeText(context,
                            "Error: could not fetch user.",
                            Toast.LENGTH_SHORT).show();
                }else {
                    //writeNewNotification();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void writeNewNotification(String userId, String whom, String action, String category, String date, String image) {

        String key = myRef.child("notification").push().getKey();
        Notification notification = new Notification(userId, whom, action, category, date, image);
        myRef.child("notification").child(userId).setValue(notification);
    }
}
