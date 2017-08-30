package com.ruiriot.deepur.model;

/**
 * Created by ruiriot on 30/08/17.
 */

public class MessageP2P {
    private String id; //message id
    private String content;
    private int type;
    private String senderId;
    private String senderName;

    //photo url for now wont be needed
    private String photoURL;
    private String recieverId;
    private Long timestamp; //timestamp of the message
    private String senderDeleted; //receiver - could be a group
    private String receiverDeleted;


    public MessageP2P() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(String recieverId) {
        this.recieverId = recieverId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSenderDeleted() {
        return senderDeleted;
    }

    public void setSenderDeleted(String senderDeleted) {
        this.senderDeleted = senderDeleted;
    }

    public String getReceiverDeleted() {
        return receiverDeleted;
    }

    public void setReceiverDeleted(String receiverDeleted) {
        this.receiverDeleted = receiverDeleted;
    }
}