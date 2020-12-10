package com.wims.whereismystore.Activity.userUploadFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class tabItemVPAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> items;
    private ArrayList<String> itext=new ArrayList<>();

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
    public tabItemVPAdapter(FragmentManager fm, String userUploadID){
        super(fm);
        items=new ArrayList<>();
        items.add(new tabItem1(userUploadID));
        items.add(new tabItem2(userUploadID));
        items.add(new tabItem3(userUploadID));

        itext.add("전체");
        itext.add("거래중");
        itext.add("거래완료");
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itext.get(position);
    }

}
