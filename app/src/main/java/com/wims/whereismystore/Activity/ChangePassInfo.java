package com.wims.whereismystore.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wims.whereismystore.R;

import java.util.HashMap;
import java.util.Map;

public class ChangePassInfo extends AppCompatActivity {

    EditText pass;
    EditText pass_pwck;
    Button changebnt, canclebnt;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);

        pass = (EditText) findViewById(R.id.change_password);
        pass_pwck = (EditText) findViewById(R.id.change_password_pwck);
        changebnt = (Button) findViewById(R.id.change);
        canclebnt = (Button) findViewById(R.id.change_delete);

        changebnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pass.equals(pass_pwck)) {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Map<String, Object> hopperUpdates = new HashMap<>();
                    hopperUpdates.put("password", pass);
                } else
                    Toast.makeText(ChangePassInfo.this, "두 입력값이 일치하지 않습니다", Toast.LENGTH_SHORT).show();


            }
        });

    }
}