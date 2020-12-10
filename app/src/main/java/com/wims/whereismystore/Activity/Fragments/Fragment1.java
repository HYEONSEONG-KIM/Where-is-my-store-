package com.wims.whereismystore.Activity.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.wims.whereismystore.Activity.SaleFindActivity;
import com.wims.whereismystore.Activity.SaleUploadActivity;
import com.wims.whereismystore.Class.Photos;
import com.wims.whereismystore.Class.SaleListAdapter;
import com.wims.whereismystore.Class.SaleListItem;
import com.wims.whereismystore.R;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    //매매기능 class
    private View view;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SaleListItem> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseError databaseError;

    Photos photo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parView=getActivity().findViewById(R.id.bottomNavi);

        view = inflater.inflate(R.layout.fragment1, container, false);
        Button uploadPost=view.findViewById(R.id.saleUpload_button);

        final BottomNavigationView bottomNavigationView =parView.findViewById(R.id.bottomNavi);

        uploadPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), SaleUploadActivity.class);
                startActivity(intent);
            }
        });
        Button findPost=view.findViewById(R.id.saleFind_button);
        findPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SaleFindActivity.class);
                startActivity(intent);
            }
        });

        //리사클러 뷰
        recyclerView=view.findViewById(R.id.saleRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList=new ArrayList<>();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                //super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && bottomNavigationView.isShown()) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (dy < 0 ) {
                    bottomNavigationView.setVisibility(View.VISIBLE);

                }
            }
        });
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("post");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SaleListItem saleListItem=dataSnapshot.getValue(SaleListItem.class);
                    saleListItem.setPostID(dataSnapshot.getKey());
                    photo=dataSnapshot.child("photo").getValue(Photos.class);
                    saleListItem.setImage(photo.getPhoto_1());
                    arrayList.add(0,saleListItem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("fragment1",String.valueOf(databaseError.toException()));
            }
        });
        adapter=new SaleListAdapter(arrayList,getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}