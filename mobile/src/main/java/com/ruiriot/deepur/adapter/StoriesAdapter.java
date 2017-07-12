package com.ruiriot.deepur.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.holder.StoriesHolder;
import com.ruiriot.deepur.fragment.StoriesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiri on 12-Jul-17.
 */

public class StoriesAdapter extends RecyclerView.Adapter<StoriesHolder> {

    private List<StoriesHolder> stories = new ArrayList<>();
    private Context context;

    public StoriesAdapter(List<StoriesHolder> stories, Context context) {
        this.stories = stories;
        this.context = context;
    }
    @Override
    public StoriesHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_item_post, viewGroup, false);
        return new StoriesHolder(view);
    }

    @Override
    public void onBindViewHolder(StoriesHolder storiesHolder, int i) {
        storiesHolder.setUserImageView(stories.get(i).getUserImageView());
        storiesHolder.setUserName(stories.get(i).getUserName());
        storiesHolder.setUserText(stories.get(i).getUserText());
    }

    @Override
    public int getItemCount() {
        return stories != null ? stories.size() : 0;
    }
}
