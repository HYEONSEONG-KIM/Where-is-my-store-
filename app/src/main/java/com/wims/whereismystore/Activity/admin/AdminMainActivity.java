package com.wims.whereismystore.Activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.wims.whereismystore.Activity.admin.adminFragment.adminTabVPAdapter;
import com.wims.whereismystore.Activity.userUploadFragment.tabItemVPAdapter;
import com.wims.whereismystore.R;

public class AdminMainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private adminTabVPAdapter adminTabVPAdapter;
    private ViewPager vp;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);


        toolbar=findViewById(R.id.admin_Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("신고 게시글 / 유저");

        vp=findViewById(R.id.admin_viewpager);
        adminTabVPAdapter=new adminTabVPAdapter(getSupportFragmentManager());
        vp.setAdapter(adminTabVPAdapter);

        tabLayout=findViewById(R.id.admin_tab);
        tabLayout.setupWithViewPager(vp);
    }

}