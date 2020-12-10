package com.wims.whereismystore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wims.whereismystore.Class.Users;
import com.wims.whereismystore.R;


public class PasswordCheckActivity extends AppCompatActivity  {



    EditText pass;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_check);

        password=((Users)getApplication()).getPassword();


        pass= (EditText) findViewById(R.id.password_check);






        Button OKbnt=(Button)findViewById(R.id.OK);
        OKbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pass_check=pass.getText().toString();
               if(Pass_check.equals(password)){
            Intent intent=new Intent(PasswordCheckActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
                   Toast.makeText(PasswordCheckActivity.this,"성공", Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(PasswordCheckActivity.this, "비밀번호를 확인하여 주세요", Toast.LENGTH_SHORT).show();
               }

            }
        });


        //취소버튼 누르면 뒤로
        Button Deletebnt=(Button)findViewById(R.id.Delete);
        Deletebnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

    }



}