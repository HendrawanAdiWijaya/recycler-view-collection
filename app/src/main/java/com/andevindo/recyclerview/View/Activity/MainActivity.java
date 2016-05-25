package com.andevindo.recyclerview.View.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.andevindo.recyclerview.R;
import com.andevindo.recyclerview.View.Fragment.CardViewFragment;
import com.andevindo.recyclerview.View.Fragment.GridLayoutManagerFragment;
import com.andevindo.recyclerview.View.Fragment.LikeBookmarkFragment;
import com.andevindo.recyclerview.View.Fragment.StaggeredLayoutManagerFragment;
import com.andevindo.recyclerview.View.Fragment.TextOnlyFragment;
import com.andevindo.recyclerview.View.Fragment.WithImageFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTab;
    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTab = (TabLayout) findViewById(R.id.tab);
        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mTab.setupWithViewPager(mPager);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        String[] mTitle = {"Text Only", "With Image", "CardView", "LikeBookmark", "Grid 2 Span", "Staggered 2 span"};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            if (position == 0)
                fragment = new TextOnlyFragment();
            else if (position == 1)
                fragment = new WithImageFragment();
            else if (position == 2)
                fragment = new CardViewFragment();
            else if (position == 3)
                fragment = new LikeBookmarkFragment();
            else if (position == 4)
                fragment = new GridLayoutManagerFragment();
            else
                fragment = new StaggeredLayoutManagerFragment();
            return fragment;
        }

        @Override
        public int getCount() {
            return mTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    }

}
