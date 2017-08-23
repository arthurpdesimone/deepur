package com.ruiriot.deepur.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ruiriot.deepur.R;

/**
 * Created by ruiri on 04-Jul-17.
 */

public class NotificationHolder extends RecyclerView.ViewHolder {

    private TextView dateNotification;
    private TextView actionNotification;

    public NotificationHolder(View itemView) {
        super(itemView);
        dateNotification = (TextView) itemView.findViewById(R.id.fragment_notifications_item_date);
        actionNotification = (TextView) itemView.findViewById(R.id.fragment_notifications_action);
    }

    public TextView getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(TextView dateNotification) {
        this.dateNotification = dateNotification;
    }

    public TextView getActionNotification() {
        return actionNotification;
    }

    public void setActionNotification(TextView actionNotification) {
        this.actionNotification = actionNotification;
    }
}
