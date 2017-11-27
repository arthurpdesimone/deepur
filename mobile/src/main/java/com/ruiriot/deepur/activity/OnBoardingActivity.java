package com.ruiriot.deepur.activity;

import android.animation.ArgbEvaluator;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.ruiriot.deepur.R;
import com.ruiriot.deepur.utils.ActivityUtils;

import static com.ruiriot.deepur.utils.ActivityUtils.callActivity;

public class OnBoardingActivity extends BaseActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    ImageView nextButton;
    TextView skipButton, doneButton;

    ImageView zero, one, two;
    ImageView[] indicators;

    int lastLeftValue = 0;

    CoordinatorLayout mCoordinator;


    static final String TAG = "PagerActivity";

    int page = 0;   //  to track page position

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.transparent));
        }

        setContentView(R.layout.activity_onboarding);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        nextButton = (ImageView) findViewById(R.id.activity_onboarding_next_button);

        skipButton = (TextView) findViewById(R.id.activity_onboarding_skip_button);
        doneButton = (TextView) findViewById(R.id.activity_onboarding_done_button);

        zero = (ImageView) findViewById(R.id.activity_onboarding_dots_1);
        one = (ImageView) findViewById(R.id.activity_onboarding_dots_2);
        two = (ImageView) findViewById(R.id.activity_onboarding_dots_3);

        mCoordinator = (CoordinatorLayout) findViewById(R.id.activity_onboarding_coordinator);

        indicators = new ImageView[]{zero, one, two};

        mViewPager = (ViewPager) findViewById(R.id.activity_onboarding_view_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setCurrentItem(page);
        updateIndicators(page);

        final int color1 = ContextCompat.getColor(this, R.color.background);
        final int color2 = ContextCompat.getColor(this, R.color.color_accent);
        final int color3 = ContextCompat.getColor(this, R.color.background_dark);

        final int[] colorList = new int[]{color1, color2, color3};
        final ArgbEvaluator evaluator = new ArgbEvaluator();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                int colorUpdate = (Integer) evaluator.evaluate(positionOffset, colorList[position], colorList[position == 2 ? position : position + 1]);
                mViewPager.setBackgroundColor(colorUpdate);

            }

            @Override
            public void onPageSelected(int position) {

                page = position;

                updateIndicators(page);

                switch (position) {
                    case 0:
                        mViewPager.setBackgroundColor(color1);
                        break;
                    case 1:
                        mViewPager.setBackgroundColor(color2);
                        break;
                    case 2:
                        mViewPager.setBackgroundColor(color3);
                        break;
                }

                nextButton.setVisibility(position == 2 ? View.GONE : View.VISIBLE);
                doneButton.setVisibility(position == 2 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page += 1;
                mViewPager.setCurrentItem(page, true);
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityUtils.saveSharedSetting(OnBoardingActivity.this, LoginActivity.PREF_USER_FIRST_TIME, "false");
                callActivity(OnBoardingActivity.this, LoginActivity.class);
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  update 1st time pref
                ActivityUtils.saveSharedSetting(OnBoardingActivity.this, LoginActivity.PREF_USER_FIRST_TIME, "false");
                callActivity(OnBoardingActivity.this, LoginActivity.class);
            }
        });

    }

    void updateIndicators(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setBackgroundResource(
                    i == position ? R.drawable.bg_round_onboarding_active : R.drawable.bg_round_onboarding_inactive
            );
        }
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        ImageView img;
        TextView textView;

        int[] texts = new int[]{R.string.onboarding1, R.string.onboarding2, R.string.onboarding3};

        int[] bgs = new int[]{R.drawable.ic_person_24dp, R.drawable.ic_person_24dp, R.drawable.ic_person_24dp};

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_onboarding, container, false);
            textView = (TextView) rootView.findViewById(R.id.fragment_onboarding_title);
            textView.setText(texts[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);

            img = (ImageView) rootView.findViewById(R.id.fragment_onboarding_image);
            img.setBackgroundResource(bgs[getArguments().getInt(ARG_SECTION_NUMBER) - 1]);

            return rootView;
        }

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position + 1);

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }

    }

}