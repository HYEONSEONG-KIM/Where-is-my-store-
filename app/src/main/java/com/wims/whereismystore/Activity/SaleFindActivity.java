package com.wims.whereismystore.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wims.whereismystore.Class.Photos;
import com.wims.whereismystore.Class.SaleListAdapter;
import com.wims.whereismystore.Class.SaleListItem;
import com.wims.whereismystore.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SaleFindActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SaleListItem> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseError databaseError;
    private Photos photo;
    private HashMap<String,Object> post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_find);

        final EditText findText=findViewById(R.id.saleFind_EditText);
        recyclerView=findViewById(R.id.saleFind_RecyclerView);
        toolbar=findViewById(R.id.saleFind_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("판매 상품 찾기");

        final Button findButton=findViewById(R.id.saleFind_button);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String findString=findText.getText().toString();
                //리사클러 뷰

                recyclerView.setHasFixedSize(true);
                layoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                arrayList=new ArrayList<>();

                database= FirebaseDatabase.getInstance();
                databaseReference=database.getReference("post");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        arrayList.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            post= (HashMap<String, Object>) dataSnapshot.getValue();
                            if(post.get("contents").toString().contains(findString)||post.get("title").toString().contains(findString)) {
                                SaleListItem saleListItem = dataSnapshot.getValue(SaleListItem.class);
                                saleListItem.setPostID(dataSnapshot.getKey());
                                photo = dataSnapshot.child("photo").getValue(Photos.class);
                                saleListItem.setImage(photo.getPhoto_1());
                                arrayList.add(0, saleListItem);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("fragment1",String.valueOf(databaseError.toException()));
                    }
                });
                adapter=new SaleListAdapter(arrayList,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
        });
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