package com.example.superstudent;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.BTChat.BTChat;
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
        tabLayout.getTabAt(5).setIcon(R.drawable.chat2);

    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new Home_Activity());
        adapter.addFragment(new Profile_Activity());
        adapter.addFragment(new Materials_Activity());
        adapter.addFragment(new Map_Activity());
        adapter.addFragment(new ToDo_Activity());
        adapter.addFragment(new BTChat());
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

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

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        /*@Override
        /public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }*/

    }


}