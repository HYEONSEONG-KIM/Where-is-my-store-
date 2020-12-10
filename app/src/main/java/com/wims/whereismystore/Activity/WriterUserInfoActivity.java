package com.wims.whereismystore.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wims.whereismystore.R;

public class WriterUserInfoActivity extends AppCompatActivity {

    private String userID;
    private String userName;
    private TextView userUpload_ID;
    private Button userUplaod_post;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writer_user_info);

        Intent intent=getIntent();
        userID=intent.getStringExtra("userID");
        userName=intent.getStringExtra("userName");

        userUpload_ID=findViewById(R.id.userUpload_ID);
        userUpload_ID.setText(userName);
        userUplaod_post=findViewById(R.id.userUplaod_post);
        userUplaod_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WriterUserInfoActivity.this, UserUploadPostActivity.class);
                intent.putExtra("uploadUserID",userID);
                startActivity(intent);
            }
        });

        toolbar=findViewById(R.id.userUpload_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("프로필");

        Button userReport=findViewById(R.id.userReport_button);
        userReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WriterUserInfoActivity.this,ReportUserActivity.class);
                intent.putExtra("userID",userID);
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });
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