package com.ruiriot.deepur.model;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ruiri on 13-Jul-17.
 */

public class Messenger {

    private String id;
    private String username;
    private String userText;
    private String userImage;
    private String timeStamp;
    private String unreadMessages;

    public Messenger() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Messenger(String id, String username, String userText, String userImage, String timeStamp, String unreadMessages) {
        this.id = id;
        this.username = username;
        this.userText = userText;
        this.userImage = userImage;
        this.timeStamp = timeStamp;
        this.unreadMessages = unreadMessages;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", username);
        result.put("message", userText);
        result.put("image", userImage);
        result.put("id", id);
        result.put("timeStamp", timeStamp);
        result.put("unreadMessages", unreadMessages);

        return result;
    }

}