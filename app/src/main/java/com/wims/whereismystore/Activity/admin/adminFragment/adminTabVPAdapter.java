package com.wims.whereismystore.Activity.admin.adminFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import java.util.ArrayList;

public class adminTabVPAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> items;
    private ArrayList<String> itext=new ArrayList<>();

    public adminTabVPAdapter(@NonNull FragmentManager fm) {
        super(fm);
        items=new ArrayList<>();
        items.add(new admin_post());
        items.add(new admin_user());

        itext.add("게시글");
        itext.add("사용자");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itext.get(position);
    }
}
