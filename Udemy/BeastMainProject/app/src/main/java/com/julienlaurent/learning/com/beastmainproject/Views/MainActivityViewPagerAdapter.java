package com.julienlaurent.learning.com.beastmainproject.Views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.julienlaurent.learning.com.beastmainproject.Fragments.AboutUsFragment;
import com.julienlaurent.learning.com.beastmainproject.Fragments.MeetABroFragment;
import com.julienlaurent.learning.com.beastmainproject.Fragments.RushFragment;

/**
 * Created by djoum on 7/31/17.
 */

public class MainActivityViewPager extends FragmentStatePagerAdapter {
    public MainActivityViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment;
        switch (position){
            case 0:
                returnFragment = AboutUsFragment.newInstance();
                break;
            case 1:
                returnFragment = MeetABroFragment.newInstance();
                break;
            case 2:
                returnFragment = RushFragment.newInstance();
                break;
            default:
                return null;
        }
        return returnFragment;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title;
        switch (position){
            case 0:
                title = "About us";
                break;
            case 1:
                title = "Meet A Bro";
                break;
            case 2:
                title = "Rush";
                break;
            default:
                title = null;
        }
        return title;
    }
}
