package com.ruiriot.deepur.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.adapter.HomeAdapter;
import com.ruiriot.deepur.adapter.StoriesAdapter;
import com.ruiriot.deepur.adapter.holder.NotificationHolder;
import com.ruiriot.deepur.adapter.holder.StoriesHolder;
import com.ruiriot.deepur.fragment.CategoriesFragment;
import com.ruiriot.deepur.fragment.MessengerFragment;
import com.ruiriot.deepur.fragment.NotificationsFragment;
import com.ruiriot.deepur.utils.GaussianBlurUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

/**Receive email > Set email on TextView > getUserName > setUserName*/

public class HomeActivity extends BaseActivity {

    @BindView(R.id.activity_home_viewpager)
    ViewPager homeViewPager;

    @BindView(R.id.activity_home_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_home_tabs)
    TabLayout tabLayout;

    @BindView(R.id.activity_main_header_settings_icon)
    ImageView settingsButton;

    Context context;

    List<NotificationHolder> notificationsList = new ArrayList<>();
    List<StoriesHolder> storiesList = new ArrayList<>();

    private Bundle extras;

    private int[] tabIcons = {
            R.drawable.ic_chat_white_24dp,
            R.drawable.ic_web_white_24dp,
            R.drawable.ic_notifications_none_white_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        extras = getIntent().getExtras();

        toolbar = (Toolbar) findViewById(R.id.activity_home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar();
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));
        context = getApplication().getApplicationContext();

        setupViewPager(homeViewPager);
        tabLayout.setupWithViewPager(homeViewPager);
        setupTabIcons();

        if (savedInstanceState == null) {
            // Set the local night mode to some value
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
            // Now recreate for it to take effect
            recreate();
        }

        String newString;

        if (savedInstanceState == null) {

            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("email");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("email");
        }

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                Bundle extrasBundle = new Bundle();
                String userEmail = extras.getString("email");
                String userName = extras.getString("name");
                extras.putString("activity", "home");
                extras.putString("email", userEmail);
                extras.putString("name", userName);
                intent.putExtras(extrasBundle);
                startActivity(intent);
            }
        });
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);

        tabLayout.getTabAt(1).getIcon().setAlpha(128);
        tabLayout.getTabAt(2).getIcon().setAlpha(128);

        tabLayout.getTabAt(0).getIcon().setColorFilter(getResources().getColor(R.color.active_icon), PorterDuff.Mode.MULTIPLY);
        tabLayout.getTabAt(1).getIcon().setColorFilter(getResources().getColor(R.color.active_icon), PorterDuff.Mode.MULTIPLY);
        tabLayout.getTabAt(2).getIcon().setColorFilter(getResources().getColor(R.color.active_icon), PorterDuff.Mode.MULTIPLY);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(128);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.getIcon().setAlpha(255);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager());
        adapter.addFragment(new MessengerFragment(), getResources().getString(R.string.title_activity_messenger));
        adapter.addFragment(new CategoriesFragment(), getResources().getString(R.string.categories));
        adapter.addFragment(new NotificationsFragment(), getResources().getString(R.string.activity_settings_notification_title));
        viewPager.setAdapter(adapter);
    }

    private void applyBlur() {
        //GaussianBlurUtils.with(this).put(dinosaurId, ivBlurredImage);
    }
}
