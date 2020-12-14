package com.wims.whereismystore.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.wims.whereismystore.Activity.Fragments.Fragment1;
import com.wims.whereismystore.Class.Photos;
import com.wims.whereismystore.Class.Post;
import com.wims.whereismystore.Class.SaleViewpagerAdapter;
import com.wims.whereismystore.Class.Users;
import com.wims.whereismystore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator;


public class SaleItemViewActivity extends AppCompatActivity {
    private String postID;
    private StorageReference mStorageRef;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private HashMap<String, Object> photo;
    private HashMap<String,Object> post;

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
        setContentView(R.layout.activity_sale_item_view);




        My_Email=((Users)getApplication()).getEmail();


        chatbnt=findViewById(R.id.saleView_chat);

        toolbar = findViewById(R.id.saleItemView_toolbar);
        toolbar = findViewById(R.id.saleItemView_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("상품 보기");

        My_Email=((Users)getApplication()).getEmail();
        chatbnt=findViewById(R.id.saleView_chat);


        name = findViewById(R.id.saleView_name);
        address = findViewById(R.id.saleView_address);
        title = findViewById(R.id.saleView_title);
        time = findViewById(R.id.saleView_time);
        contents = findViewById(R.id.saleView_contents);
        pager = findViewById(R.id.SaleItemViewPager);
        layout = findViewById(R.id.saleView_layout);
        indicator = findViewById(R.id.saleView_indicator);
        //SaleViewpagerAdapter adapter=new SaleViewpagerAdapter(getLayoutInflater());

        chatbnt=findViewById(R.id.saleView_chat);

        final Intent intent = getIntent();
        postID = intent.getStringExtra("postID");
        writerPin=intent.getStringExtra("postUserID");

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

                if(My_Email.equals(post.get("writerPin"))){
                    chatbnt.setVisibility(View.INVISIBLE);
                }
                UID = Objects.requireNonNull(post.get("writerPin")).toString();
                UNAME = Objects.requireNonNull(post.get("name")).toString();

                if(My_Email.equals(post.get("writerPin"))){
                    chatbnt.setVisibility(View.INVISIBLE);
                }
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(My_Email.equals(writerPin)){}
                        else {
                            Intent intent = new Intent(SaleItemViewActivity.this, WriterUserInfoActivity.class);
                            intent.putExtra("userID", post.get("writerPin").toString());
                            intent.putExtra("userName", post.get("name").toString());
                            startActivity(intent);
                        }
                    }
                });

                report = findViewById(R.id.reportPost_Report);
                report.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(My_Email.equals(writerPin)){}
                        else{
                            Intent intent1 = new Intent(SaleItemViewActivity.this, ReportPostActivity.class);
                            intent1.putExtra("userID", post.get("writerPin").toString());
                            intent1.putExtra("userName", post.get("name").toString());
                            intent1.putExtra("postID", key);
                            intent1.putExtra("title", post.get("title").toString());
                            startActivity(intent1);
                        }
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
                        post=(HashMap<String, Object>) snapshot.getValue();
                        UID=post.get("writerPin").toString();
                        UNAME=post.get("name").toString();

                        String uid=UNAME+"("+UID+")";
                        String destinationUid=uid.replace(".","+");

                        if(UID.equals(My_Email)) {
                            chatbnt.setEnabled(false);
                        }
                        else{
                            Intent intent = new Intent(SaleItemViewActivity.this, ChattingActivity.class);
                            intent.putExtra("UID", UID);
                            intent.putExtra("NAME", UNAME);
                            intent.putExtra("destinationUid",destinationUid);
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


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_main_menu,menu);

        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder= (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.post_modify_item:
                if(My_Email.equals(writerPin)) {

                    database = FirebaseDatabase.getInstance();
                    databaseReference = database.getReference().child("post").child(postID);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            HashMap<String, Object> postHash= (HashMap<String, Object>) snapshot.getValue();
                            Post post=new Post();
                            post.setRemainAddress(postHash.get("remainAddress").toString());
                            post.setDistrictName(postHash.get("districtName").toString());
                            post.setAddress(postHash.get("address").toString());
                            post.setPin(postID);
                            post.setPrice(postHash.get("price").toString());
                            post.setReport(postHash.get("report").toString());
                            post.setBLNumber(postHash.get("blnumber").toString());
                            post.setWriterPin(postHash.get("writerPin").toString());
                            post.setCreateDate(postHash.get("createDate").toString());
                            post.setModifyDate(postHash.get("modifyDate").toString());
                            post.setTitle(postHash.get("title").toString());
                            post.setContents(postHash.get("contents").toString());
                            post.setIndustryCode(postHash.get("industryCode").toString());
                            post.setLocalName(postHash.get("localName").toString());
                            post.setName(postHash.get("name").toString());
                            post.setState(postHash.get("state").toString());


                            HashMap<String, Object> photoHash = (HashMap<String, Object>) snapshot.child("photo").getValue();
                            final Photos photo=new Photos();
                            photo.setCount(photoHash.get("count").toString());
                            for (int i = 0; i < Integer.parseInt(photo.getCount()); i++) {
                                final int index=i;
                                String photoIndex = "photo_" + (i + 1);
                                photo.setPhotos(photoHash.get(photoIndex).toString(),i);
                                Log.d("upload",photo.getPhotos(i));
                            }

                            Intent intent=new Intent(SaleItemViewActivity.this,SaleUploadActivity.class);
                            intent.putExtra("photo",photo);
                            intent.putExtra("post",post);
                            startActivity(intent);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                    Toast.makeText(this, "게시글 작성자만 수정할 수 있습니다.", Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.post_delete_item:
                if(My_Email.equals(writerPin)) {
                    database = FirebaseDatabase.getInstance();
                    databaseReference = database.getReference().child("post").child(postID);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            HashMap<String, Object> photo = (HashMap<String, Object>) snapshot.child("photo").getValue();
                            ArrayList<String> data = new ArrayList<>();
                            for (int i = 0; i < Integer.parseInt(photo.get("count").toString()); i++) {
                                String photoIndex = "photo_" + (i + 1);
                                Log.d("delete",photo.get(photoIndex).toString());
                                data.add(photo.get(photoIndex).toString());
                                StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("images/"+data.get(i));
                                storageReference.delete();
                            }
                            databaseReference.removeValue();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    setResult(Activity.RESULT_OK);
                    finish();
                }else{
                    Toast.makeText(this, "게시글 작성자만 삭제할 수 있습니다.", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}