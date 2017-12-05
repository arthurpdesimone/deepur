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

public class ChatAdapter extends RecyclerView.Adapter<Messenger> {

    private List<Messenger> messages = new ArrayList<>();
    private Context context;

    public ChatAdapter(List<Messenger> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @Override
    public Messenger onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_messenger_message_sent, viewGroup, false);
        return new Messenger(view);
    }

    @Override
    public void onBindViewHolder(Messenger messageP2P, int i) {

        messageP2P.setUserImageView(messages.get(i).getUserImageView());
        messageP2P.setUserName(messages.get(i).getUserName());
        messageP2P.setUserText(messages.get(i).getUserText());
        messageP2P.setTimeStamp(messages.get(i).getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return messages != null ? messages.size() : 0;
    }

}
