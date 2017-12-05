package com.ruiriot.deepur.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.CategoriesAdapter;
import com.ruiriot.deepur.adapter.TrendingCategoriesAdapter;
import com.ruiriot.deepur.model.Category;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesFragment extends BaseFragment{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    String TAG = "Firebase";
    public ArrayList<Category> categories = new ArrayList<>();
    FirebaseAuth mAuth;

    @BindView(R.id.fragment_categories_list)
    RecyclerView recyclerViewCategories;

    @BindView(R.id.fragment_categories_trending_list)
    RecyclerView recyclerViewTrending;

    public CategoriesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("categories");
        mAuth = FirebaseAuth.getInstance();

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Category categoriesItem = dataSnapshot.getValue(Category.class);
                Log.d(TAG, "onChildAdded: " + categoriesItem);
                categories.add(categoriesItem);
                recyclerViewCategories.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.addChildEventListener(childEventListener);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Category category = dataSnapshot.getValue(Category.class);
                Log.d(TAG, "Value is: " + category);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
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
        recyclerViewCategories.setAdapter(new CategoriesAdapter(categories, mListener));
        recyclerViewTrending.setAdapter(new TrendingCategoriesAdapter(categories, mListener));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            mListener = (OnListFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {

        void onListFragmentInteraction(Category item);
    }
}
