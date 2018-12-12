package com.ruiriot.deepur.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.holder.callback.OnItemSelected;
import com.ruiriot.deepur.model.Topic;

import java.util.Calendar;
import java.util.List;

public class TrendingTopicsAdapter extends BaseAdapter {

    private final List<Topic> mValues;
    private final OnItemSelected<Topic> mListener;
    private Context context;

    public TrendingTopicsAdapter(List<Topic> items, OnItemSelected<Topic> listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_topic_trending_item, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, int position) {
        final ViewHolder holder = (ViewHolder) h;
        holder.mItem = mValues.get(position);
        holder.TopicName.setText(holder.mItem.getName());

        Calendar instance = Calendar.getInstance();
        if (holder.mItem.getTimestamp() != null) {

            instance.setTimeInMillis(holder.mItem.getTimestamp());
            int hours = instance.get(Calendar.HOUR_OF_DAY);
            int minutes = instance.get(Calendar.MINUTE);
            String output = String.format("%02d:%02d", hours, minutes);

            holder.TopicTimestamp.setText(output);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onItemSelected(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView TopicName;
        final TextView TopicTimestamp;
//        final ImageView TopicImage;
        Topic mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            TopicName = view.findViewById(R.id.fragment_topic_trending_item_title);
//            TopicImage = view.findViewById(R.id.fragment_categories_image);
            TopicTimestamp = view.findViewById(R.id.fragment_topic_trending_item_timestamp);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + TopicName.getText() + "'";
        }
    }
}
