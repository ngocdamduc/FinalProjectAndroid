package com.example.wor.ui.workout.search_exercise_tabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    //Adaptor fields
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    //Constructor
    ViewPagerAdapter(FragmentManager manager){
        super(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }
    @Override
    @NonNull
    public Fragment getItem(int position){
        return mFragmentList.get(position);
    }
    @Override
    public int getCount(){return mFragmentList.size();}
    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }
    // Other Adaptor Methods
    void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
}
