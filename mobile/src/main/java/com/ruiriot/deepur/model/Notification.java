package com.ruiriot.deepur.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Monitora-PC on 22/08/17.
 */

public class Notification {

    String uid;
    String whom;
    String action;
    String category;
    Long date;
    String image;

    public Notification(String uid, String whom, String action, String category, String image){
        this.uid = uid;
        this.whom = whom;
        this.action = action;
        this.category = category;
        this.image = image;

        date = new Date().getTime();
    }

    public Notification(){

    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("whom", whom);
        result.put("action", action);
        result.put("category", category);
        result.put("date", date);
        result.put("image", image);

        return result;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getWhom() {
        return whom;
    }

    public void setWhom(String whom) {
        this.whom = whom;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
