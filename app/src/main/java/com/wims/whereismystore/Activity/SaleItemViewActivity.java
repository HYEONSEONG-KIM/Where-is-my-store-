package com.wims.whereismystore.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.wims.whereismystore.Class.Photos;
import com.wims.whereismystore.Class.SaleViewpagerAdapter;
import com.wims.whereismystore.R;

import java.util.ArrayList;
import java.util.HashMap;

import me.relex.circleindicator.CircleIndicator;


public class SaleItemViewActivity extends AppCompatActivity {
    private String postID;
    private StorageReference mStorageRef;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private HashMap<String, Object> photo;
    private HashMap<String,Object> post;
    private HashMap<String,Object> post2;

    private TextView name;
    private TextView address;
    private TextView title;
    private TextView time;
    private TextView contents;
    private ViewPager pager;
    private LinearLayout layout;
    private Toolbar toolbar;
    private TextView report;

    private SaleViewpagerAdapter adapter;
    CircleIndicator indicator;
    private String UID;
    private String UNAME;
    private Button chatbnt;

    private String My_Email;

//    @IgnoreExtraProperties
//    public class User{
//        public User(){
//
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_item_view);

        toolbar=findViewById(R.id.saleItemView_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("상품 보기");

        My_Email=((Users)getApplication()).getEmail();

        name=findViewById(R.id.saleView_name);
        address=findViewById(R.id.saleView_address);
        title=findViewById(R.id.saleView_title);
        time=findViewById(R.id.saleView_time);
        contents=findViewById(R.id.saleView_contents);
        pager=findViewById(R.id.SaleItemViewPager);
        layout=findViewById(R.id.saleView_layout);
        indicator=findViewById(R.id.saleView_indicator);

        chatbnt=findViewById(R.id.saleView_chat);
        //SaleViewpagerAdapter adapter=new SaleViewpagerAdapter(getLayoutInflater());

        //pager.setAdapter(adapter);


        final Intent intent=getIntent();
        postID=intent.getStringExtra("postID");

        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("post").child(postID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String key=snapshot.getKey();
                post=(HashMap<String, Object>) snapshot.getValue();
                Log.d("post",post.toString());
                name.setText(post.get("name").toString());
                address.setText(post.get("localName").toString()+" "+post.get("districtName").toString());
                title.setText(post.get("title").toString());
                if(post.get("modifyDate").toString().equals("")){
                    time.setText(post.get("createDate").toString());
                }else{
                    time.setText(post.get("modifyDate").toString());
                }
                contents.setText(post.get("contents").toString());
                photo= (HashMap<String, Object>) snapshot.child("photo").getValue();

                ArrayList<String> data=new ArrayList<>();
                for(int i=0; i<Integer.parseInt(photo.get("count").toString());i++){
                    String photoIndex="photo_"+(i+1);
                    data.add(photo.get(photoIndex).toString());
                }
                adapter=new SaleViewpagerAdapter(getApplicationContext(),data);
                pager.setAdapter(adapter);
                indicator.setViewPager(pager);

                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(SaleItemViewActivity.this, WriterUserInfoActivity.class);
                        intent.putExtra("userID",post.get("writerPin").toString());
                        intent.putExtra("userName",post.get("name").toString());
                        startActivity(intent);
                    }
                });
                UID=post.get("writerPin").toString();

                if(UID.equals(My_Email))
                    chatbnt.setText("수정");
                else
                    chatbnt.setText("채팅");

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }



        });

                report=findViewById(R.id.reportPost_Report);
                report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1=new Intent(SaleItemViewActivity.this,ReportPostActivity.class);
                        intent1.putExtra("userID",post.get("writerPin").toString());
                        intent1.putExtra("userName",post.get("name").toString());
                        intent1.putExtra("postID",key);
                        intent1.putExtra("title",post.get("title").toString());
                        startActivity(intent1);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });






        chatbnt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               database=FirebaseDatabase.getInstance();
               databaseReference=database.getReference().child("post").child(postID);
               databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       post2=(HashMap<String, Object>) snapshot.getValue();
                       UID=post2.get("writerPin").toString();
                       UNAME=post2.get("name").toString();

                       if(UID.equals(My_Email)) {
                           Intent intent =new Intent(SaleItemViewActivity.this,TestActivity.class);
                           startActivity(intent);
                       }
                       else{
                           Intent intent = new Intent(SaleItemViewActivity.this, ChattingActivity.class);
                           intent.putExtra("UID", UID);
                           intent.putExtra("NAME", UNAME);
                           startActivity(intent);

                       }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}