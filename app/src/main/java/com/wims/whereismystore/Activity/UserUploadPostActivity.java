package com.wims.whereismystore.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.wims.whereismystore.Activity.userUploadFragment.tabItem1;
import com.wims.whereismystore.Activity.userUploadFragment.tabItem2;
import com.wims.whereismystore.Activity.userUploadFragment.tabItem3;
import com.wims.whereismystore.Activity.userUploadFragment.tabItemVPAdapter;
import com.wims.whereismystore.R;

public class UserUploadPostActivity extends AppCompatActivity {

    private String writerID;
    private Toolbar toolbar;
    private tabItemVPAdapter tabItemVPAdapter;
    private ViewPager vp;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_upload_post);

        Intent intent=getIntent();
        writerID=intent.getStringExtra("uploadUserID");

        toolbar=findViewById(R.id.userUplaod_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("판매 상품 보기");

        vp=findViewById(R.id.userUpload_viewpager);
        tabItemVPAdapter=new tabItemVPAdapter(getSupportFragmentManager(),writerID);
        vp.setAdapter(tabItemVPAdapter);

        tabLayout=findViewById(R.id.userUploae_tab);
        tabLayout.setupWithViewPager(vp);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}