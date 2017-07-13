package com.ruiriot.deepur.activity;

import android.content.Context;
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
import com.ruiriot.deepur.adapter.NotificationAdapter;
import com.ruiriot.deepur.adapter.StoriesAdapter;
import com.ruiriot.deepur.adapter.holder.NotificationHolder;
import com.ruiriot.deepur.adapter.holder.StoriesHolder;
import com.ruiriot.deepur.fragment.MessengerFragment;
import com.ruiriot.deepur.fragment.NotificationsFragment;
import com.ruiriot.deepur.fragment.StoriesFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

/**Receive email > Set email on TextView > getUserName > setUserName*/

public class HomeActivity extends BaseActivity {

    @BindView(R.id.activity_home_viewpager)
    ViewPager homeViewPager;

    @BindView(R.id.acitivity_home_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_home_tabs)
    TabLayout tabLayout;

    @BindView(R.id.activity_main_header_settings_icon)
    ImageView settingsButton;

    Context context;

    List<NotificationHolder> notificationsList = new ArrayList<>();
    List<StoriesHolder> storiesList = new ArrayList<>();

    @Nullable
    @BindView(R.id.fragment_notifications_recycler_view)
    RecyclerView recyclerViewNotifications;

    @Nullable
    @BindView(R.id.fragment_stories_recycler_view)
    RecyclerView recyclerViewStories;

    @Nullable
    @BindView(R.id.fragment_notifications_item_date)
    TextView dateNotification;

    @Nullable
    @BindView(R.id.fragment_notifications_item_description)
    TextView descriptionNotification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        toolbar = findViewById(R.id.acitivity_home_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.background));
        context = getApplication().getApplicationContext();

        setupViewPager(homeViewPager);
        tabLayout.setupWithViewPager(homeViewPager);

        if (savedInstanceState == null) {
            // Set the local night mode to some value
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
            // Now recreate for it to take effect
            recreate();
        }

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callActivity(getApplicationContext(), SettingsActivity.class);
            }
        });

        //setupRecyclerViewNotifications();
        //setupRecyclerViewStories();

    }

    private void setupRecyclerViewNotifications(){


    }

    private void setupRecyclerViewStories(){

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerViewStories.setLayoutManager(mLayoutManager);
        recyclerViewStories.setItemAnimator(new DefaultItemAnimator());

        recyclerViewStories.setAdapter(new StoriesAdapter(storiesList, context));
    }

    private void setupViewPager(ViewPager viewPager) {
        HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager());
        adapter.addFragment(new MessengerFragment(), getResources().getString(R.string.title_activity_messenger));
        adapter.addFragment(new StoriesFragment(), getResources().getString(R.string.stories));
        adapter.addFragment(new NotificationsFragment(), getResources().getString(R.string.activity_settings_notification_title));
        viewPager.setAdapter(adapter);
    }
}
