package com.ruiriot.deepur.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.BaseAdapter;
import com.ruiriot.deepur.fragment.CategoriesFragment.OnListFragmentInteractionListener;
import com.ruiriot.deepur.adapter.CategoriesAdapter.CategoriesItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link CategoriesItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCategoriesRecyclerViewAdapter extends BaseAdapter {

    final List<CategoriesItem> mValues;
    final OnListFragmentInteractionListener mListener;

    public MyCategoriesRecyclerViewAdapter(List<CategoriesItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_categories, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.categoryName.setText(holder.mItem.name);
        //holder.categoryImage.setImageDrawable(mValues.get(position).name);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView categoryName;
        final ImageView categoryImage;
        CategoriesItem mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            categoryName = (TextView) view.findViewById(R.id.fragment_categories_name);
            categoryImage = (ImageView) view.findViewById(R.id.fragment_categories_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + categoryName.getText() + "'";
        }
    }
}
