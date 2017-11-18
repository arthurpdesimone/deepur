package com.ruiriot.deepur.activity;

import android.os.Bundle;
import com.ruiriot.deepur.R;
import com.ruiriot.deepur.fragment.CategoriesFragment;

public class CategoriesActivity extends BaseActivity implements CategoriesFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

    }
}
