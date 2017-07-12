package com.ruiriot.deepur.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ruiri on 04-Jul-17.
 */

public class NotificationHolder extends RecyclerView.ViewHolder {

    public NotificationHolder(View itemView) {
        super(itemView);
    }

    private String dateNotification;
    private String descriptionNotification;

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
