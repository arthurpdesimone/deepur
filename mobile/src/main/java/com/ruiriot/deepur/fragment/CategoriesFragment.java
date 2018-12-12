package com.ruiriot.deepur.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.CategoriesAdapter;
import com.ruiriot.deepur.adapter.TrendingTopicsAdapter;
import com.ruiriot.deepur.adapter.holder.callback.OnItemSelected;
import com.ruiriot.deepur.model.Category;
import com.ruiriot.deepur.model.Topic;
import com.ruiriot.deepur.repository.CategoryRepository;
import com.ruiriot.deepur.repository.TopicRepository;
import com.ruiriot.deepur.repository.callback.OnDataFetchCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesFragment extends BaseFragment{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    String TAG = "Firebase";
    private OnItemSelected<Topic> mTopicSelectionListener;
    private OnItemSelected<Category> mCategorySelectionListener;
    public ArrayList<Category> categories = new ArrayList<>();
    public ArrayList<Topic> trendingTopics = new ArrayList<>();
    public CategoryRepository categoryRepository = new CategoryRepository();
    public TopicRepository topicRepository = new TopicRepository();

    @BindView(R.id.fragment_categories_list)
    RecyclerView recyclerViewCategories;

    @BindView(R.id.fragment_categories_trending_list)
    RecyclerView recyclerViewTrending;

    public CategoriesFragment() {
        mTopicSelectionListener = new OnItemSelected<Topic>() {
            @Override
            public void onItemSelected(Topic item) {

            }
        };

        mCategorySelectionListener = new OnItemSelected<Category>() {
            @Override
            public void onItemSelected(Category item) {

            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        categoryRepository.getAll(new OnDataFetchCallBack<List<Category>>() {
            @Override
            public void onFetch(List<Category> data) {
                if (data != null) {
                    categories.addAll(data);
                    recyclerViewCategories.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });

        topicRepository.getAllTrending(new OnDataFetchCallBack<List<Topic>>() {
            @Override
            public void onFetch(List<Topic> data) {
                if (data != null) {
                    trendingTopics.addAll(data);
                    recyclerViewTrending.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        ButterKnife.bind(this, view);

        Context context = view.getContext();
        if (mColumnCount <= 1) {
            recyclerViewCategories.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        } else {
            recyclerViewCategories.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        recyclerViewCategories.setAdapter(new CategoriesAdapter(categories, mCategorySelectionListener));
        recyclerViewTrending.setAdapter(new TrendingTopicsAdapter(trendingTopics, mTopicSelectionListener));

        return view;
    }
}
