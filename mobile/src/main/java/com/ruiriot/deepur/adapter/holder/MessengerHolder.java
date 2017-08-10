package com.ruiriot.deepur.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ruiriot.deepur.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ruiri on 12-Jul-17.
 */

public class MessengerHolder extends RecyclerView.ViewHolder{

    private CircleImageView userImageView;
    private TextView userName;
    private TextView userText;

    public MessengerHolder(View itemView) {
        super(itemView);
        userImageView = (CircleImageView) itemView.findViewById(R.id.fragment_messenger_item_user_image);
        userName = (TextView) itemView.findViewById(R.id.fragment_messenger_item_user_name);
        userText = (TextView) itemView.findViewById(R.id.fragment_messenger_item_message_preview);
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
