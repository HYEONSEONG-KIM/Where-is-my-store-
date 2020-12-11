package com.wims.whereismystore.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wims.whereismystore.Class.Post;
import com.wims.whereismystore.R;

import org.w3c.dom.Text;

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


        TextView scam=findViewById(R.id.reportPost_scam);//게시글 신고
        TextView prohibited=findViewById(R.id.reportPost_Prohibited);//게시글 신고
        TextView notPost=findViewById(R.id.reportPost_notPost);//게시글 신고
        TextView otherProblem=findViewById(R.id.reportPost_otherProblem);//게시글 신고
        TextView reportUser=findViewById(R.id.reportPost_reportUser);//사용자신고
        scam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDialog reportDialog=new ReportDialog(ReportPostActivity.this);
                reportDialog.callReportPostDialog(postKey, postTitle, "3","사기");
            }
        });
        prohibited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDialog reportDialog=new ReportDialog(ReportPostActivity.this);
                reportDialog.callReportPostDialog(postKey,postTitle,"1","판매 금자 품목");
            }
        });
        notPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDialog reportDialog=new ReportDialog(ReportPostActivity.this);
                reportDialog.callReportPostDialog(postKey, postTitle,"2","거래 게시글 아님");
            }
        });
        otherProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDialog reportDialog=new ReportDialog(ReportPostActivity.this);
                reportDialog.callReportPostDialog(postKey,postTitle,"4","기타");
            }
        });

        reportUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(ReportPostActivity.this,ReportUserActivity.class);
                intent1.putExtra("userID", userID);
                intent1.putExtra("userName", userName);
                startActivity(intent1);
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