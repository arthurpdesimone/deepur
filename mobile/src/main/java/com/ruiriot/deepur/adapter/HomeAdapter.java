package com.ruiriot.deepur.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.holder.HomeHolder;
import com.ruiriot.deepur.fragment.ItemPostFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ruiri on 30-Jun-17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeHolder> {

    private final List<ItemPostFragment> itemPostFragments;

    public HomeAdapter(ArrayList posts) {
        itemPostFragments = posts;
    }

    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itemPostFragments != null ? itemPostFragments.size() : 0;
    }

}