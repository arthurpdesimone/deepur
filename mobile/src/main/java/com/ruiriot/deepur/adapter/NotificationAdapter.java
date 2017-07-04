package com.ruiriot.deepur.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.holder.NotificationHolder;
import com.ruiriot.deepur.fragment.ItemNotification;
import com.ruiriot.deepur.fragment.NotificationsFragment;

import java.util.List;


/**
 * Created by ruiri on 04-Jul-17.
 */

public class NotificationAdapter extends RecyclerView.Adapter {

    private List<ItemNotification> notifications;
    private Context context;

    public NotificationAdapter(List<ItemNotification> notificationList, Context context) {
        this.notifications = notificationList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_notifications_item, viewGroup, false);
        NotificationHolder notificationHolder = new NotificationHolder(view);
        return notificationHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        NotificationHolder holder = (NotificationHolder) viewHolder;

        ItemNotification itemNotification  = notifications.get(i) ;

        itemNotification.setDateNotification(notifications.get(i).getDateNotification());
        itemNotification.setDescriptionNotification(notifications.get(i).getDescriptionNotification());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }
}
