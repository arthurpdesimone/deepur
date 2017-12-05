package com.ruiriot.deepur.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.holder.Messenger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiri on 12-Jul-17.
 */

public class MessengerAdapter extends RecyclerView.Adapter<Messenger> {

    private List<Messenger> messengerUsers = new ArrayList<>();
    private Context context;

    public MessengerAdapter(List<Messenger> users, Context context) {
        this.messengerUsers = users;
        this.context = context;

    }
    @Override
    public Messenger onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_messenger_item, viewGroup, false);
        return new Messenger(view);
    }

    @Override
    public void onBindViewHolder(Messenger messenger, int i) {

        messenger.setUserImageView(messengerUsers.get(i).getUserImageView());
        messenger.setUserName(messengerUsers.get(i).getUserName());
        messenger.setUserText(messengerUsers.get(i).getUserText());
        messenger.setTimeStamp(messengerUsers.get(i).getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return messengerUsers != null ? messengerUsers.size() : 0;
    }

}
