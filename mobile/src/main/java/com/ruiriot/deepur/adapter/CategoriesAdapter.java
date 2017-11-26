package com.ruiriot.deepur.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.BaseAdapter;
import com.ruiriot.deepur.fragment.CategoriesFragment.OnListFragmentInteractionListener;
import com.ruiriot.deepur.model.Category;

import java.util.List;

public class CategoriesAdapter extends BaseAdapter {

    final List<Category> mValues;
    final OnListFragmentInteractionListener mListener;

    public CategoriesAdapter(List<Category> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_categories_item, parent, false);
//        view.setOnClickListener(mOnClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder h, int position) {
        final ViewHolder holder = (ViewHolder) h;
        holder.mItem = mValues.get(position);
        holder.categoryName.setText(holder.mItem.description);
        holder.categoryImage.setImageDrawable(Drawable.createFromPath(mValues.get(position).image));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
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
        final TextView categoryName;
        final ImageView categoryImage;
        Category mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            categoryName = view.findViewById(R.id.fragment_categories_name);
            categoryImage = view.findViewById(R.id.fragment_categories_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + categoryName.getText() + "'";
        }
    }
}
