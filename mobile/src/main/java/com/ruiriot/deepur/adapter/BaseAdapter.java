package com.ruiriot.deepur.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by ruiri on 21-May-17.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
