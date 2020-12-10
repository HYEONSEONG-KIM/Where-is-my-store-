package com.wims.whereismystore.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.wims.whereismystore.Class.Post;
import com.wims.whereismystore.R;

public class ReportPostActivity extends AppCompatActivity {

    private String userID;
    private String userName;
    private String postKey;
    private String postTitle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_post);

        Intent intent=getIntent();
        userID=intent.getStringExtra("userID");
        userName=intent.getStringExtra("userName");
        postKey=intent.getStringExtra("postID");
        postTitle=intent.getStringExtra("title");

        TextView reportPostTitle=findViewById(R.id.reportPost_ID);
        TextView reportWriterName=findViewById(R.id.reportPost_reportUserID);

        reportPostTitle.setText("'"+postTitle+"'");
        reportWriterName.setText("혹시 '"+userName+"'을 신고하고 싶으신가요?");


        toolbar=findViewById(R.id.reportPost_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("신고하기");

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