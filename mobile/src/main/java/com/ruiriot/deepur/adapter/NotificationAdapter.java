package com.ruiriot.deepur.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.holder.NotificationHolder;
import com.ruiriot.deepur.model.Notification;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ruiri on 04-Jul-17.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationHolder> {

    private List<Notification> notifications = new ArrayList<>();
    private Context context;

    public NotificationAdapter(List<Notification> notificationList, Context context) {
        this.notifications = notificationList;
        this.context = context;
    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_notifications_item, viewGroup, false);
        return new NotificationHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationHolder notificationHolder, int i) {

        final RecyclerView.ViewHolder holder = notificationHolder;
    }

    @Override
    public int getItemCount() {
        return notifications != null ? notifications.size() : 0;

    }
}
