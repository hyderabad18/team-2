package com.example.admin.cfg2k18_team2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by admin on 7/15/2018.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter{
    private static final String TAG = FragmentPagerAdapter.class.getSimpleName();
    private static final int FRAGMENT_COUNT = 4;
    ArrayList<Fragment> fragments=new ArrayList<>();

    FragmentAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position)
    {

        return fragments.get(position);
    }

    public void addFragment(Fragment f){
        fragments.add(f);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position){
      /*  switch(position){
            case 0:return "Updates";
            case 1:return "Media";
            case 2:return "Polls";
            case 3:return "About us";
        }*/
        return fragments.get(position).toString();
    }

//    public void addFragment(NotificationsFroMStudents notificationsFroMStudents) {
//    }
//
//    public void addFragment(NotififcationsFromVolunteers notififcationsFromVolunteers) {
//    }
}
