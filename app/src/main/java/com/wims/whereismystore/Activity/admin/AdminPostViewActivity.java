package com.wims.whereismystore.Activity.admin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.wims.whereismystore.Activity.ChattingActivity;
import com.wims.whereismystore.Activity.ReportPostActivity;
import com.wims.whereismystore.Activity.SaleItemViewActivity;
import com.wims.whereismystore.Activity.SaleUploadActivity;
import com.wims.whereismystore.Activity.WriterUserInfoActivity;
import com.wims.whereismystore.Class.Photos;
import com.wims.whereismystore.Class.Post;
import com.wims.whereismystore.Class.SaleViewpagerAdapter;
import com.wims.whereismystore.Class.Users;
import com.wims.whereismystore.R;

import java.util.ArrayList;
import java.util.HashMap;

import me.relex.circleindicator.CircleIndicator;

public class AdminPostViewActivity extends AppCompatActivity {

    private String postID;
    private StorageReference mStorageRef;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private HashMap<String, Object> photo;
    private HashMap<String, Object> post;

    private TextView name;
    private TextView address;
    private TextView title;
    private TextView time;
    private TextView contents;
    private ViewPager pager;
    private LinearLayout layout;
    private Toolbar toolbar;
    private TextView report;
    private Button chatbnt;
    private String UID;
    private String UNAME;
    private String My_Email;
    private SaleViewpagerAdapter adapter;
    CircleIndicator indicator;
    private String writerPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_post_view);

        toolbar = findViewById(R.id.adminPost_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("상품 보기");

        name = findViewById(R.id.adminPost_name);
        address = findViewById(R.id.adminPost_address);
        title = findViewById(R.id.adminPost_title);
        time = findViewById(R.id.adminPost_time);
        contents = findViewById(R.id.adminPost_contents);
        pager = findViewById(R.id.AdminItemViewPager);
        indicator = findViewById(R.id.adminPost_indicator);

        final Intent intent = getIntent();
        postID = intent.getStringExtra("postID");
        writerPin = intent.getStringExtra("postUserID");

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("post").child(postID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String key = snapshot.getKey();
                post = (HashMap<String, Object>) snapshot.getValue();
                Log.d("post", post.toString());
                name.setText(post.get("name").toString());
                address.setText(post.get("localName").toString() + " " + post.get("districtName").toString());
                title.setText(post.get("title").toString());
                if (post.get("modifyDate").toString().equals("")) {
                    time.setText(post.get("createDate").toString());
                } else {
                    time.setText(post.get("modifyDate").toString());
                }
                contents.setText(post.get("contents").toString());
                photo = (HashMap<String, Object>) snapshot.child("photo").getValue();

                ArrayList<String> data = new ArrayList<>();
                for (int i = 0; i < Integer.parseInt(photo.get("count").toString()); i++) {
                    String photoIndex = "photo_" + (i + 1);
                    data.add(photo.get(photoIndex).toString());
                }
                adapter = new SaleViewpagerAdapter(getApplicationContext(), data);
                pager.setAdapter(adapter);
                indicator.setViewPager(pager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        Button report1=findViewById(R.id.admin_report_1_Button);
        report1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                database.getReference().child("post").child(postID).child("report").setValue("1");
                finish();
            }
        });
        Button report3=findViewById(R.id.admin_report_3_Button);
        report3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                database.getReference().child("post").child(postID).child("report").setValue("3");
                finish();
            }
        });

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