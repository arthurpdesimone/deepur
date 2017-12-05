package com.ruiriot.deepur.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.model.MessageP2P;

import java.util.Calendar;
import java.util.List;

/**
 * Created by ruiri on 12-Jul-17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<MessageP2P> conversations;
    private OnItemClickListener onItemClickListener;
    private static final int CHAT_RIGHT = 1;
    private static final int CHAT_LEFT = 2;

    public ChatAdapter(List<MessageP2P> conversations, OnItemClickListener onItemClickListener) {
        this.conversations = conversations;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case CHAT_LEFT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                                .fragment_messenger_message_received,
                        parent, false);
                return new ViewHolder(view, CHAT_LEFT);
            case CHAT_RIGHT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                                .fragment_messenger_message_sent,
                        parent, false);
                return new ViewHolder(view, CHAT_RIGHT);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MessageP2P messageP2P = conversations.get(position);

        holder.messageText.setText(messageP2P.getContent());

        Calendar instance = Calendar.getInstance();
        if (messageP2P.getTimestamp() != null) {

            instance.setTimeInMillis(messageP2P.getTimestamp());
            int hours = instance.get(Calendar.HOUR_OF_DAY);
            int minutes = instance.get(Calendar.MINUTE);
            String output = String.format("%02d:%02d", hours, minutes);

            holder.messageTimeStamp.setText(output);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onConversationClick(messageP2P);
            }
        });
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    public interface OnItemClickListener {
        void onConversationClick(MessageP2P conversation);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView messageText;
        TextView messageTimeStamp;

        private ViewHolder(View itemView, int type) {
            super(itemView);

            switch (type) {

                case CHAT_RIGHT:
                        messageText = itemView.findViewById(R.id.fragment_messenger_message_sent);
                        messageTimeStamp = itemView.findViewById(R.id.fragment_messenger_message_sent_timestamp);
                    break;

                case CHAT_LEFT:
                    messageText = itemView.findViewById(R.id.fragment_messenger_message_received);
                    messageTimeStamp = itemView.findViewById(R.id.fragment_messenger_message_received_timestamp);
                    break;
            }
        }
    }
}