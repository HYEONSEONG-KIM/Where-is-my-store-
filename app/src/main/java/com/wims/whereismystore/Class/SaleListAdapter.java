package com.wims.whereismystore.Class;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.wims.whereismystore.Activity.SaleItemViewActivity;
import com.wims.whereismystore.R;

import java.util.ArrayList;

public class SaleListAdapter extends RecyclerView.Adapter<SaleListAdapter.SaleListViewHolder>{
    private ArrayList<SaleListItem> listItems;
    private Context context;
    private Intent intent;

    public SaleListAdapter(ArrayList<SaleListItem> arrayList, Context context){
        this.listItems=arrayList;
        this.context=context;
    }
    @NonNull
    @Override
    public SaleListAdapter.SaleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.salelistitem,parent,false);
        SaleListViewHolder holder=new SaleListViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SaleListAdapter.SaleListViewHolder holder, final int position) {
        StorageReference storage =FirebaseStorage.getInstance().getReference();
        StorageReference iamgeRef=storage.child(listItems.get(position).getImage());

        Glide.with(holder.itemView)
                .load(iamgeRef)
                .into(holder.image);

        holder.title.setText(listItems.get(position).getTitle());
        holder.state.setText(listItems.get(position).getState());
        holder.price.setText(listItems.get(position).getPrice());
        holder.district.setText(listItems.get(position).getDistrictName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(v.getContext(), SaleItemViewActivity.class);
                String postID=listItems.get(position).getPostID();
                intent.putExtra("postID",postID);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (listItems != null ? listItems.size() : 0);
    }

    public class SaleListViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView district;
        TextView price;
        TextView state;

        public SaleListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image=itemView.findViewById(R.id.sale_imageView);
            this.title=itemView.findViewById(R.id.saleTitle_textView);
            this.district=itemView.findViewById(R.id.saleDistrict_textView);
            this.price=itemView.findViewById(R.id.saleprice_textView);
            this.state=itemView.findViewById(R.id.saleState_textView);
        }
    }

}
