package com.wims.whereismystore.Activity.admin;

import android.app.Activity;
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
import com.wims.whereismystore.Activity.SaleItemViewActivity;
import com.wims.whereismystore.Class.SaleListItem;
import com.wims.whereismystore.R;

import java.util.ArrayList;

public class adminPostListAdapter extends RecyclerView.Adapter<adminPostListAdapter.adminPostListViewHolder>{
    private ArrayList<SaleListItem> listItems;
    private Context context;
    private Intent intent;

    public adminPostListAdapter(ArrayList<SaleListItem> arrayList, Context context){
        this.listItems=arrayList;
        this.context=context;
    }
    @NonNull
    @Override
    public adminPostListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.salelistitem,parent,false);
        adminPostListViewHolder holder=new adminPostListViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final adminPostListViewHolder holder, final int position) {
        StorageReference storage = FirebaseStorage.getInstance().getReference();
        storage.child("images/"+listItems.get(position).getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("image",uri.toString());
                Glide.with(holder.itemView)
                        .load(uri)
                        .into(holder.image);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("image","fail");
            }
        });

        holder.title.setText(listItems.get(position).getTitle());
        holder.price.setText(listItems.get(position).getPrice());
        if(listItems.get(position).getState().equals("1")){ }
        else if(listItems.get(position).getState().equals("2")){
            holder.state.setText("예약중");
            holder.state.setBackgroundResource(R.drawable.bg_state);
        }else{
            holder.state.setText("거래완료");
            holder.state.setBackgroundResource(R.drawable.bg_state);
        }
        holder.district.setText(listItems.get(position).getDistrictName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(v.getContext(), AdminPostViewActivity.class);
                String postID=listItems.get(position).getPostID();
                intent.putExtra("postID",postID);
                intent.putExtra("postUserID",listItems.get(position).getWriterPin());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ((Activity)holder.itemView.getContext()).startActivityForResult(intent,10001);
                //context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listItems != null ? listItems.size() : 0);
    }

    public class adminPostListViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView district;
        TextView price;
        TextView state;

        public adminPostListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image=itemView.findViewById(R.id.sale_imageView);
            this.title=itemView.findViewById(R.id.saleTitle_textView);
            this.district=itemView.findViewById(R.id.saleDistrict_textView);
            this.price=itemView.findViewById(R.id.saleprice_textView);
            this.state=itemView.findViewById(R.id.saleState_textView);
        }
    }

}
