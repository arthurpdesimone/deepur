package com.ruiriot.deepur.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.ruiriot.deepur.R;

/**
 * Created by ruiriot on 29/08/17.
 */

public class NewMessageFragment extends DialogFragment implements View.OnClickListener{

    private AutoCompleteTextView autoCompleteTextView;

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
       clearText.setOnClickListener(this);
       arrowBack.setOnClickListener(this);
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
