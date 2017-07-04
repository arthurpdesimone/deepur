package com.ruiriot.deepur.fragment;

/**
 * Created by ruiri on 04-Jul-17.
 */

public class ItemNotification {

    private String dateNotification;
    private String descriptionNotification;


    public ItemNotification(String dateNotification, String descriptionNotification) {
        this.dateNotification = dateNotification;
        this.descriptionNotification = descriptionNotification;
    }

    public String getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(String dateNotification) {
        this.dateNotification = dateNotification;
    }

    public String getDescriptionNotification() {
        return descriptionNotification;
    }

    public void setDescriptionNotification(String descriptionNotification) {
        this.descriptionNotification = descriptionNotification;
    }
}
