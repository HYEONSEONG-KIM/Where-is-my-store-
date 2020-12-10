package com.wims.whereismystore.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.wims.whereismystore.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wims.whereismystore.Class.Users;

import java.util.HashMap;
import java.util.Map;

public class JoinActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText name;
    EditText password_confirm;
    Button check, join, button;
    private DatabaseReference mDatabase;
    private String emailString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        button = (Button) findViewById(R.id.delete);
        join = (Button) findViewById(R.id.change_delete);
        check = (Button)findViewById(R.id.check_button);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        email = (EditText) findViewById(R.id.join_email);
        password = (EditText) findViewById(R.id.join_password);
        name = (EditText) findViewById(R.id.join_name);
        password_confirm = (EditText) findViewById(R.id.join_pwck);



        // 이메일 입력칸에 따른 변경리스너
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            // 값이 변경될 때마다 버튼 비활성화 ~~ :D
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                check.setEnabled(false);
                join.setEnabled(false);
            }
            // 이메일 형식이 맞는지 확인
            @Override
            public void afterTextChanged(Editable s) {
                emailString = email.getText().toString().trim();
                if(Patterns.EMAIL_ADDRESS.matcher(emailString).matches() && s.length() > 0) {
                    check.setEnabled(true);
                }
                else{
                    check.setEnabled(false);
                }
            }
        });

        // 이메일 중복 확인 리스너
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserEmail = email.getText().toString();
                checkEmail(getUserEmail);
            }
        });


        // 버튼
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        // 회원가입 완료 될때!! :D
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userPin = email.getText().toString().replace('.','+');
                String getUserName = name.getText().toString();
                String getUserEmail = email.getText().toString();
                String getUserPassword = password.getText().toString();
                //hashmap 만들기
                Map<String, Users> users = new HashMap<>();
                writeNewUser(getUserEmail, getUserName, getUserPassword);
            }
        });

    }

    // 데이터베이스에 저장하기
    private void writeNewUser(String email, String name, String password) {
        Users user = new Users(email, name, password);
        email = email.replace('.','+');
        mDatabase.child("users").child(email).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(JoinActivity.this, "가입을 완료했습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(JoinActivity.this, "가입이 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 이메일 중복 확인 :D
    public void checkEmail(String email) {
        email = email.replace('.', '+');
        mDatabase.child("users").child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null){
                    Toast.makeText(JoinActivity.this, "이 이메일을 쓰셔도 좋습니다.", Toast.LENGTH_SHORT).show();
                    join.setEnabled(true);
                }

                else{
                    Toast.makeText(JoinActivity.this, "이메일이 중복됩니다.", Toast.LENGTH_SHORT).show();
                    join.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("FireBaseData", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }
}