package com.ruiriot.deepur.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ruiriot.deepur.R;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ruiri on 12-Jul-17.
 */

public class MessengerHolder extends RecyclerView.ViewHolder{

    private CircleImageView userImageView;
    private TextView userName;
    private TextView userText;
    private TextView timeStamp;
    private TextView unreadMessages;

    public MessengerHolder(View itemView) {
        super(itemView);
        userImageView = (CircleImageView) itemView.findViewById(R.id.fragment_messenger_item_user_image);
        userName = (TextView) itemView.findViewById(R.id.fragment_messenger_item_user_name);
        userText = (TextView) itemView.findViewById(R.id.fragment_messenger_item_message_preview);
        timeStamp = (TextView) itemView.findViewById(R.id.fragment_messenger_item_message_time);
        unreadMessages = (TextView) itemView.findViewById(R.id.fragment_messenger_item_message_status);
    }

    public TextView getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(TextView timeStamp) {
        this.timeStamp = timeStamp;
    }

    public TextView getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(TextView unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    public CircleImageView getUserImageView() {
        return userImageView;
    }

    public void setUserImageView(CircleImageView userImageView) {
        this.userImageView = userImageView;
    }

    public TextView getUserName() {
        return userName;
    }

    public void setUserName(TextView userName) {
        this.userName = userName;
    }

    public TextView getUserText() {
        return userText;
    }

    public void setUserText(TextView userText) {
        this.userText = userText;
    }
}
