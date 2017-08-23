package com.ruiriot.deepur.model;

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
    String date;
    String image;

    public Notification(String uid, String whom, String action, String category, String date, String image){
        this.uid = uid;
        this.whom = whom;
        this.action = action;
        this.category = category;
        this.date = date;
        this.image = image;
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
}
