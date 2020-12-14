package com.wims.whereismystore.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wims.whereismystore.Activity.admin.dataFormat.ReportPost;
import com.wims.whereismystore.Activity.admin.dataFormat.ReportUser;
import com.wims.whereismystore.Class.Users;
import com.wims.whereismystore.R;

public class ReportDialog extends AppCompatActivity {

    private Context context;
    private DatabaseReference databaseReference;

    public ReportDialog(Context context){
        this.context=context;
    }

    @SuppressLint("ResourceType")
    public void callReportPostDialog(final String postID, String postTitle, final String reportCode, final String reportReason){
        final Dialog dig = new Dialog(context);
        dig.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dig.setContentView(R.layout.activity_report_dialog);
        dig.show();

        TextView ID=dig.findViewById(R.id.reportID);
        ID.setText(postTitle);
        TextView reason=dig.findViewById(R.id.reportReason);
        reason.setText(reportReason+", 해당 사유로 신고하시겠습니까?");
        final EditText message = dig.findViewById(R.id.reportContents);
        Button okBtn = dig.findViewById(R.id.reportOK);
        Button cancelBtn = dig.findViewById(R.id.reportCancel);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference =FirebaseDatabase.getInstance().getReference();
                if(reportCode.equals("4")){
                    Toast.makeText(context,"기타 사유 선택 시 상세 이유를 적어주세요.",Toast.LENGTH_SHORT).show();
                }else{

                    ReportPost reportPost = new ReportPost(postID, reportCode, reportReason, message.getText().toString(),"1");
                    String postKey = databaseReference.child("report").child("post").push().getKey();
                    databaseReference.child("report").child("post").child(postKey).setValue(reportPost);
                    dig.dismiss();
                    databaseReference.child("post").child(postID).child("report").setValue("2");
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dig.dismiss();
            }
        });
    }
    public void callReportUserDialog(final String userID, String userName, final String reportCode, final String reportReason) {
        final Dialog dig = new Dialog(context);
        dig.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dig.setContentView(R.layout.activity_report_dialog);
        dig.show();

        TextView ID=dig.findViewById(R.id.reportID);
        ID.setText(userName);
        TextView reason=dig.findViewById(R.id.reportReason);
        reason.setText(reportReason+", 해당 사유로 신고하시겠습니까?");
        final EditText message = dig.findViewById(R.id.reportContents);
        Button okBtn = dig.findViewById(R.id.reportOK);
        Button cancelBtn = dig.findViewById(R.id.reportCancel);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference =FirebaseDatabase.getInstance().getReference();
                if(reportCode.equals("6")){
                    Toast.makeText(context,"기타 사유 선택 시 상세 이유를 적어주세요.",Toast.LENGTH_SHORT).show();
                }else{
                    ReportUser reportUser = new ReportUser(userID, reportCode, reportReason, message.getText().toString(),"1");
                    String postKey = databaseReference.child("report").child("user").push().getKey();
                    databaseReference.child("report").child("user").child(postKey).setValue(reportUser);
                    String email=userID.replace('.', '+');
                    databaseReference.child("users").child(email).child("status").setValue("2");
                    dig.dismiss();
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dig.dismiss();
            }
        });
    }


}