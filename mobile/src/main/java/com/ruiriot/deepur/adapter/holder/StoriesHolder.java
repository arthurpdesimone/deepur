package com.ruiriot.deepur.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruiriot.deepur.R;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ruiri on 12-Jul-17.
 */

public class StoriesHolder extends RecyclerView.ViewHolder {

    private CircleImageView userImageView;
    private TextView userName;
    private EditText userText;

    public StoriesHolder(View itemView) {
        super(itemView);
        userImageView = (CircleImageView) itemView.findViewById(R.id.fragment_stories_post_user_image);
        userName = (TextView) itemView.findViewById(R.id.fragment_stories_post_user_name);
        userText = (EditText) itemView.findViewById(R.id.fragment_new_item_post_edit_text);
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

    public EditText getUserText() {
        return userText;
    }

    public void setUserText(EditText userText) {
        this.userText = userText;
    }
}
