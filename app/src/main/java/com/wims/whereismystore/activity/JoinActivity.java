package com.wims.whereismystore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wims.whereismystore.R;
import com.wims.whereismystore.data_format.Users;

import java.util.HashMap;

public class JoinActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText name;
    EditText password_confirm;
    private DatabaseReference mDatabase;
    private boolean Email_check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Button button = (Button) findViewById(R.id.delete);
        Button join = (Button) findViewById(R.id.join_button);
        Button check = (Button)findViewById(R.id.check_button);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        email = (EditText) findViewById(R.id.join_email);
        password = (EditText) findViewById(R.id.join_password);
        name = (EditText) findViewById(R.id.join_name);
        password_confirm = (EditText) findViewById(R.id.join_pwck);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserEmail = email.getText().toString();
                checkEmail(getUserEmail);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinActivity.this, com.wims.whereismystore.activity.LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserName = name.getText().toString();
                String getUserEmail = email.getText().toString();
                String getUserPassword = password.getText().toString();
                Toast.makeText(JoinActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                //hashmap 만들기
                HashMap result = new HashMap<>();
                result.put("email", getUserEmail);
                result.put("password", getUserPassword);
                result.put("name", getUserName);
                writeNewUser(getUserEmail, getUserName, getUserPassword);
            }
        });

    }

    // 데이터베이스에 저장하기
    private void writeNewUser(String email, String name, String password) {
        Users user = new Users(email, name, password);
        mDatabase.child("users").child(email).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(JoinActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(JoinActivity.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 이메일 중복 확인 :D
    public void checkEmail(String email) {  
        mDatabase.child("users").child(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             if(dataSnapshot.getValue() == null){
                 Toast.makeText(JoinActivity.this, "존재 실패.", Toast.LENGTH_SHORT).show();
             }

             else{
                 Toast.makeText(JoinActivity.this, "이 메일 존재.", Toast.LENGTH_SHORT).show();
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




