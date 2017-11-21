package com.ruiriot.deepur.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ruiriot.deepur.R;

/**
 * Created by ruiriot on 29/08/17.
 */

public class NewMessageFragment extends DialogFragment implements View.OnClickListener{

    private AutoCompleteTextView autoCompleteTextView;
    DatabaseReference database;

    public NewMessageFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView = inflater.inflate(R.layout.fragment_new_message, container, false);
       autoCompleteTextView = rootView.findViewById(R.id.fragment_new_message_auto_complete);
       ImageView arrowBack = rootView.findViewById(R.id.fragment_new_message_arrow_back);
       ImageView clearText = rootView.findViewById(R.id.fragment_new_message_clear_input);

       database = FirebaseDatabase.getInstance().getReference();
       populateAutoCompleteTextView();

       clearText.setOnClickListener(this);
       arrowBack.setOnClickListener(this);
       return rootView;
    }

    public void populateAutoCompleteTextView(){

        final ArrayAdapter<String> autoComplete = new ArrayAdapter<>(getActivity(), R.layout.item_new_message_users);

        database.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Basically, this says "For each DataSnapshot *Data* in dataSnapshot, do what's inside the method.
                for (DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()){
                    //Get the suggestion by childing the key of the string you want to get.
                    String suggestion = suggestionSnapshot.child("name").getValue(String.class);
                    //Add the retrieved string to the list
                    autoComplete.add(suggestion);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        autoCompleteTextView.setAdapter(autoComplete);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void onClick(View v) {
        int i = v.getId();

        if(i == R.id.fragment_new_message_arrow_back){
            getDialog().dismiss();
        }else if(i == R.id.fragment_new_message_clear_input){
            autoCompleteTextView.setText("");
        }
    }
}
