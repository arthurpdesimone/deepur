package com.ruiriot.deepur.model;

import android.net.Uri;

import java.util.Date;

/**
 * Created by ruiriot on 30/08/17.
 */

public class MessageP2P {
//    private String id; //message id
//    private int type;
    private String senderId;
    private String senderName;
    private String content;
    private Long timestamp;

    private Uri photoURL;
//    private String receiverId;
//    private String senderDeleted; //receiver - could be a group
//    private String receiverDeleted;

    public MessageP2P (){

    }

    public MessageP2P(String senderId,String senderName, String content, Uri photoURL) {
        this.senderId = senderId;
        this.senderName = senderName;
        this.content = content;
        this.photoURL = photoURL;

        timestamp = new Date().getTime();
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Uri getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(Uri photoURL) {
        this.photoURL = photoURL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}