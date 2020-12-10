package com.wims.whereismystore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.wims.whereismystore.Class.Users;
import com.wims.whereismystore.R;

import java.util.HashMap;


public class SaleItemViewActivity extends AppCompatActivity {
    private String postID;
    private StorageReference mStorageRef;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private Photos photo;
    private HashMap<String,Object> post;
    private HashMap<String,Object> post2;

    private TextView name;
    private TextView address;
    private TextView title;
    private TextView time;
    private TextView contents;
    private ViewPager2 pager;

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

        My_Email=((Users)getApplication()).getEmail();

        name=findViewById(R.id.saleView_name);
        address=findViewById(R.id.saleView_address);
        title=findViewById(R.id.saleView_title);
        time=findViewById(R.id.saleView_time);
        contents=findViewById(R.id.saleView_contents);
        pager=findViewById(R.id.SaleItemViewPager);

        chatbnt=findViewById(R.id.saleView_chat);
        //SaleViewpagerAdapter adapter=new SaleViewpagerAdapter(getLayoutInflater());

        //pager.setAdapter(adapter);

        Intent intent=getIntent();
        postID=intent.getStringExtra("postID");

        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("post").child(postID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
                   public void onCancelled(@NonNull DatabaseError error) {

                   }



               });



           }
       });




    }
}