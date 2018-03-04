package com.example.asus.recruit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.asus.recruit.ui.CommonFragment;

import java.util.List;



public class FragmentAdapter extends FragmentPagerAdapter {
   private List<CommonFragment> mFragments;


    public FragmentAdapter(FragmentManager fm, List<CommonFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return mFragments.get(position%mFragments.size());
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


}
