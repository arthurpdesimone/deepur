package com.ruiriot.deepur.model;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ruiri on 13-Jul-17.
 */

public class Messenger {

    String username;
    String userText;
    CircleImageView userImage;

    public Messenger() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Messenger(String username, String userText, CircleImageView userImage) {
        this.username = username;
        this.userText = userText;
        this.userImage = userImage;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", username);
        result.put("message", userText);
        result.put("image", userImage);

        return result;
    }

}