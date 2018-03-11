package com.cse.cou.mobarak.coublooddonars;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Mobarak on 4/24/2017.
 */

public class MyAdapter extends FragmentStatePagerAdapter{
    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        if(position==0){
            fragment=new BloodGroupFragment();
        }
        if(position==1){
            fragment=new MyAccountFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "DONARS LIST";
        }
        if(position==1){
            return "MY ACCOUNT";
        }

            return super.getPageTitle(position);
    }
}
