package com.wims.whereismystore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wims.whereismystore.Activity.Fragments.Fragment5;
import com.wims.whereismystore.Class.Users;
import com.wims.whereismystore.R;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity {

    Fragment5 fragment5;



    private EditText pass;
    private EditText pass_pwck;
    private String BeforePass;
    private DatabaseReference mDatabase;
    private String password;
    private String password_pwck;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        BeforePass=((Users)getApplication()).getPassword();

        fragment5=new Fragment5();

        Email=((Users)getApplication()).getEmail();

        pass=(EditText)findViewById(R.id.change_password);
        pass_pwck=(EditText)findViewById(R.id.change_password_pwck);
        Button changebnt=(Button)findViewById(R.id.changebnt);
        Button canclebnt=(Button)findViewById(R.id.change_delete);


        changebnt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        password=pass.getText().toString().trim();
                        password_pwck=pass_pwck.getText().toString().trim();

                        if(BeforePass.equals(pass)){
                            Toast.makeText(ChangePasswordActivity.this, "이전 비밀번호와 같습니다 다른 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                        }

                        else if(password.equals(password_pwck)){
                    String Uid = Email.replace('.','+');
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
                    Map<String, Object> updateMap = new HashMap<>();
                    updateMap.put("password",password);

                    ref.child(Uid).updateChildren(updateMap);


                            ((Users) getApplication()).setPassword(password);
                            Intent intent=new Intent(ChangePasswordActivity.this,SaleActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            Toast.makeText(ChangePasswordActivity.this, "비밀번호가 변경되었습니다", Toast.LENGTH_SHORT).show();


                        }
                else
                    Toast.makeText(ChangePasswordActivity.this,"두 입력값이 일치하지 않습니다", Toast.LENGTH_SHORT).show();


            }
        });

        canclebnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ChangePasswordActivity.this,SaleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Toast.makeText(ChangePasswordActivity.this, "변경이 취소되었습니다", Toast.LENGTH_SHORT).show();

            }
        });










    }
}