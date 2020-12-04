package com.wims.whereismystore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.wims.whereismystore.R;

import static com.wims.whereismystore.R.id.login_button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button1 = (Button)findViewById(R.id.join_button);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, com.wims.whereismystore.Activity.JoinActivity.class);
                startActivity(intent);
            }
        });


        Button button2 = (Button)findViewById(login_button);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SaleActivity.class);
                startActivity(intent);
            }
        });

    }
}