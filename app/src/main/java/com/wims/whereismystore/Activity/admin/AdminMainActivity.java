package com.wims.whereismystore.Activity.admin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.wims.whereismystore.Activity.LoginActivity;
import com.wims.whereismystore.Activity.SaleItemViewActivity;
import com.wims.whereismystore.Activity.SaleUploadActivity;
import com.wims.whereismystore.Activity.admin.adminFragment.adminTabVPAdapter;
import com.wims.whereismystore.Activity.userUploadFragment.tabItemVPAdapter;
import com.wims.whereismystore.Class.Photos;
import com.wims.whereismystore.Class.Post;
import com.wims.whereismystore.R;

import java.util.ArrayList;
import java.util.HashMap;

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
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_admin_main_menu,menu);

        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder= (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.admin_logout:
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
                alertDialog.setTitle("로그아웃");
                alertDialog.setMessage("로그아웃 하시겠습니까??").setCancelable(false)
                        .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(AdminMainActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}