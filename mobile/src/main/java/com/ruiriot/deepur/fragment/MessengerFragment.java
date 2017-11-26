package com.ruiriot.deepur.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import com.ruiriot.deepur.adapter.MessengerAdapter;
import com.ruiriot.deepur.adapter.holder.MessengerHolder;
import com.ruiriot.deepur.model.Messenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruiri on 12-Jul-17.
 */

public class MessengerFragment extends BaseFragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private List<MessengerHolder> messengerUser = new ArrayList<>();
    Context context;
    RecyclerView.LayoutManager mCurrentLayoutManager;
    private static final String TAG = "MessengerFragment";
    MessengerAdapter mAdapter;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    private String id;
    private String userName;
    private String userText;
    private String userImage;
    private String timeStamp;
    private String unreadMessages;
    MessengerHolder holder;

    private static final int DATASET_COUNT = 60;

    public MessengerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("messenger");

        mAuth = FirebaseAuth.getInstance();

        //userName = holder.getUserName().toString();
        //userText = holder.getUserText().toString();
        //userImage = holder.getUserImageView().toString();
        //timeStamp = holder.getTimeStamp().toString();
        //unreadMessages = holder.getUnreadMessages().toString();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Messenger messenger = dataSnapshot.getValue(Messenger.class);
                id = dataSnapshot.getRef().getKey();

                if (messenger == null){
                    Toast.makeText(context,
                            "Error: could not fetch user.",
                            Toast.LENGTH_SHORT).show();
                }else {
                    writeNewMessenger(id, userName, userText, userImage, timeStamp, unreadMessages);
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
        View rootView = inflater.inflate(R.layout.fragment_messenger, container, false);
        rootView.setTag(TAG);

        context = getActivity();
        recyclerView = rootView.findViewById(R.id.fragment_messenger_recycler_view);

        mCurrentLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton newMessageFAB = rootView.findViewById(R.id.fragment_messenger_fab_new_message);
        newMessageFAB.setOnClickListener(this);

        mAdapter = new MessengerAdapter(messengerUser, context);
        // Set CustomAdapter as the adapter for RecyclerView.
        recyclerView.setAdapter(mAdapter);
        setRecyclerViewLayoutManager(mCurrentLayoutManager);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
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

    public void setRecyclerViewLayoutManager(RecyclerView.LayoutManager layoutManager) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.scrollToPosition(scrollPosition);
    }

    private void writeNewMessenger(String id, String userName, String userText, String userImage, String timeStamp, String unreadMessages) {
        Messenger messengerUsers = new Messenger(id, userName, userText, userImage, timeStamp, unreadMessages);
        myRef.child(id).setValue(messengerUsers);

        Map<String, Object> messengerValues = messengerUsers.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(myRef.toString(), messengerValues);

        myRef.updateChildren(childUpdates);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if(i == R.id.fragment_messenger_fab_new_message){
            NewMessageFragment dialog = new NewMessageFragment();
            dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
            dialog.show(getActivity().getFragmentManager(), "");
            //writeNewMessenger(id, userName, userText, userImage, timeStamp, unreadMessages);
        }
    }
}
