package com.wims.whereismystore.Activity.admin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.wims.whereismystore.Activity.admin.dataFormat.ReportUser;
import com.wims.whereismystore.R;

public class AdminUserViewActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_view);

        toolbar = findViewById(R.id.adminUser_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("상세 보기");

        Intent intent=getIntent();
        ReportUser reportUser=(ReportUser)intent.getSerializableExtra("reportUser");
        TextView email=findViewById(R.id.adminUser_email);
        TextView codeName=findViewById(R.id.adminUser_name);
        TextView reason=findViewById(R.id.adminUser_reason);

        email.setText(reportUser.getEmail());
        codeName.setText(reportUser.getReportName());
        reason.setText(reportUser.getReason());

        Button report1=findViewById(R.id.adminUser_report_1_Button);
        Button report3=findViewById(R.id.adminUser_report_3_Button);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}