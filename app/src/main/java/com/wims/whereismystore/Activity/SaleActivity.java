package com.wims.whereismystore.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wims.whereismystore.Activity.Fragments.Fragment1;
import com.wims.whereismystore.Activity.Fragments.Fragment2;
import com.wims.whereismystore.Activity.Fragments.Fragment3;
import com.wims.whereismystore.Activity.Fragments.Fragment5;
import com.wims.whereismystore.R;

public class SaleActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀네비게이션 뷰
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment5 fragment5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.sale:
                        setFrag(0);
                        break;
                    case R.id.current:
                        setFrag(1);
                        break;
                    case R.id.chat:
                        setFrag(2);
                        break;
                    case R.id.MY:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment5 = new Fragment5();

        setFrag(0); // 첫화면 설정
    }

    // 프래그먼트 교체가 일어나는 메서드
    private void setFrag(int n){

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        switch (n){
            case 0:
                transaction.replace(R.id.main_frame, fragment1);
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.main_frame, fragment2);
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.main_frame, fragment3);
                transaction.commit();
                break;
            case 3:
                transaction.replace(R.id.main_frame, fragment5);
                transaction.commit();
                break;
        }
    }
}