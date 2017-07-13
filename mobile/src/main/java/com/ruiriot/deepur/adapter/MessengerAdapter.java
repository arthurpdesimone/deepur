package com.ruiriot.deepur.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.holder.MessengerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiri on 12-Jul-17.
 */

public class MessengerAdapter extends RecyclerView.Adapter<MessengerHolder> {

    private List<MessengerHolder> messengerUsers = new ArrayList<>();
    private Context context;

    public MessengerAdapter(List<MessengerHolder> users, Context context) {
        this.messengerUsers = users;
        this.context = context;

    }
    @Override
    public MessengerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_messenger_item, viewGroup, false);
        return new MessengerHolder(view);
    }

    @Override
    public void onBindViewHolder(MessengerHolder messengerHolder, int i) {
        messengerHolder.setUserImageView(messengerUsers.get(i).getUserImageView());
        messengerHolder.setUserName(messengerUsers.get(i).getUserName());
        messengerHolder.setUserText(messengerUsers.get(i).getUserText());
    }

    @Override
    public int getItemCount() {
        return messengerUsers != null ? messengerUsers.size() : 0;
    }

}
