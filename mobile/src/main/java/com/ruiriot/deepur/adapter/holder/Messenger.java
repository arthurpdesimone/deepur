package com.ruiriot.deepur.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.ruiriot.deepur.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ruiri on 12-Jul-17.
 */

public class Messenger extends RecyclerView.ViewHolder{

    private CircleImageView userImageView;
    private TextView userName;
    private TextView userText;
    private TextView timeStamp;

    public Messenger(View itemView) {
        super(itemView);
        userImageView = itemView.findViewById(R.id.fragment_messenger_item_user_image);
        userName = itemView.findViewById(R.id.fragment_messenger_item_user_name);
        userText = itemView.findViewById(R.id.fragment_messenger_item_message_preview);
        timeStamp = itemView.findViewById(R.id.fragment_messenger_item_message_time);
    }

    public TextView getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(TextView timeStamp) {
        this.timeStamp = timeStamp;
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
