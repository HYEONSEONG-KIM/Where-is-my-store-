package com.wims.whereismystore.Activity.admin.adminFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wims.whereismystore.Activity.admin.adminPostListAdapter;
import com.wims.whereismystore.Class.Photos;
import com.wims.whereismystore.Class.SaleListAdapter;
import com.wims.whereismystore.Class.SaleListItem;
import com.wims.whereismystore.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link admin_post#newInstance} factory method to
 * create an instance of this fragment.
 */
public class admin_post extends Fragment {

    private String userUploadID;
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SaleListItem> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseError databaseError;
    private Photos photo;
    private HashMap<String,Object> post;

    public admin_post() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_admin_post, container, false);

        //리사클러 뷰
        recyclerView=view.findViewById(R.id.tab_adminPost);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
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
                    if(!post.get("report").toString().equals("1")) {
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
        adapter=new adminPostListAdapter(arrayList,getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}