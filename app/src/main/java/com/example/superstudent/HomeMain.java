package com.example.superstudent;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.superstudent.Map.Map_Activity;
import com.example.superstudent.Materials.Materials_Activity;
import com.example.superstudent.Profile.Profile_Activity;
import com.example.superstudent.Quotes.Home_Activity;
import com.example.superstudent.ToDoList.ToDo_Activity;

import java.util.ArrayList;
import java.util.List;

public class HomeMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home_button);
        tabLayout.getTabAt(1).setIcon(R.drawable.user);
        tabLayout.getTabAt(2).setIcon(R.drawable.books_stack);
        tabLayout.getTabAt(3).setIcon(R.drawable.map);
        tabLayout.getTabAt(4).setIcon(R.drawable.list);

    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new Home_Activity(), "");
        adapter.addFragment(new Profile_Activity(), "");
        adapter.addFragment(new Materials_Activity(), "");
        adapter.addFragment(new Map_Activity(), "");
        adapter.addFragment(new ToDo_Activity(), "");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
}