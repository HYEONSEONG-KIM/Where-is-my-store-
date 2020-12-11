package com.wims.whereismystore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.wims.whereismystore.Class.Users;
import com.wims.whereismystore.R;

import static com.wims.whereismystore.R.id.join_button;
import static com.wims.whereismystore.R.id.login_button;
import static com.wims.whereismystore.R.id.login_email;
import static com.wims.whereismystore.R.id.login_password;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;
    private Button account;
    private Users user;
    String emailS;
    String nameS;
    String passwordS;
    String status;

    String emailString;
    String passwordString;

    boolean login_state =false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = (EditText)findViewById(login_email);
        password = (EditText)findViewById(login_password);
        login = (Button)findViewById(login_button);
        account = (Button)findViewById(join_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailString = email.getText().toString().trim();
                emailString = emailString.replace('.', '+');
                passwordString = password.getText().toString().trim();

                Login_Sys();
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,JoinActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Login_Sys(){


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference roofRef = database.getReference();
        final DatabaseReference usersRef = roofRef.child("users");
        Query myTopPostsQuery = usersRef.child(emailString).child("password");

        // 로그인 비밀번호 체크
        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // 비밀번호 스트링으로 체크함
                if (snapshot.getValue() != null) {
                    if (snapshot.getValue(String.class).equals(passwordString)) {
                        Toast.makeText(LoginActivity.this, "로그인 완료", Toast.LENGTH_SHORT).show();
                        usersRef.orderByKey().equalTo(emailString).addListenerForSingleValueEvent(new ValueEventListener() {


                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                                    emailS= ds.child("email").getValue(String.class);
                                    nameS = ds.child("name").getValue(String.class);
                                    passwordS = ds.child("password").getValue(String.class);
                                    status = ds.child("status").getValue(String.class);

                                    user = (Users)getApplicationContext();
                                    user.setEmail(emailS);
                                    user.setName(nameS);
                                    user.setPassword(passwordS);
                                    user.setStatus(status);
                                    Intent intent = new Intent(LoginActivity.this, SaleActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {}
                        });

                    }
                    else
                        Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                }

                else
                    Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}