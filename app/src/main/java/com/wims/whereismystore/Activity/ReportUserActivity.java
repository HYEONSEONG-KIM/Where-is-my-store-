package com.wims.whereismystore.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wims.whereismystore.Class.Photos;
import com.wims.whereismystore.Class.SaleListItem;
import com.wims.whereismystore.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportUserActivity extends AppCompatActivity {

    private String userID;
    private String userName;
    private TextView userNameText;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseError databaseError;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_user);

        Intent intent=getIntent();
        userID=intent.getStringExtra("userID");
        userName=intent.getStringExtra("userName");

        userNameText=findViewById(R.id.reportUser_ID);
        userNameText.setText("'"+userName+"'을 신고하는 이유를 선택해주세요");

        TextView nonManners=findViewById(R.id.reportUser_nonManners);
        TextView abuse=findViewById(R.id.reportUser_abuse);
        TextView scam=findViewById(R.id.reportUser_scam);
        TextView sexual=findViewById(R.id.reportUser_SexualHarassment);
        TextView dispute=findViewById(R.id.reportUser_dispute);
        TextView otherProblem=findViewById(R.id.reportUser_otherProblem);

        nonManners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDialog reportDialog=new ReportDialog(ReportUserActivity.this);
                reportDialog.callReportUserDialog(userID,userName,"1","비매너");
            }
        });
        abuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDialog reportDialog=new ReportDialog(ReportUserActivity.this);
                reportDialog.callReportUserDialog(userID,userName,"2","욕설");
            }
        });
        scam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDialog reportDialog=new ReportDialog(ReportUserActivity.this);
                reportDialog.callReportUserDialog(userID,userName,"5","사기");
            }
        });
        sexual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDialog reportDialog=new ReportDialog(ReportUserActivity.this);
                reportDialog.callReportUserDialog(userID,userName,"3","성희롱");
            }
        });
        dispute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDialog reportDialog=new ReportDialog(ReportUserActivity.this);
                reportDialog.callReportUserDialog(userID,userName,"4","거래/환불 분쟁");
            }
        });
        otherProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportDialog reportDialog=new ReportDialog(ReportUserActivity.this);
                reportDialog.callReportUserDialog(userID,userName,"6","기타");
            }
        });


        toolbar=findViewById(R.id.reportUser_toolbar);
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