package com.wims.whereismystore.Activity.admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wims.whereismystore.Activity.admin.dataFormat.ReportUser;
import com.wims.whereismystore.Class.SaleListItem;
import com.wims.whereismystore.R;

import java.util.ArrayList;

public class adminUserListAdapter extends RecyclerView.Adapter<adminUserListAdapter.adminUserListHolder>{
    private ArrayList<ReportUser> listItems;
    private Context context;
    private Intent intent;

    public adminUserListAdapter(ArrayList<ReportUser> arrayList, Context context){
        this.listItems=arrayList;
        this.context=context;
    }
    @NonNull
    @Override
    public adminUserListAdapter.adminUserListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.report_user_item,parent,false);
        adminUserListHolder holder=new adminUserListHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final adminUserListAdapter.adminUserListHolder holder, final int position) {
        holder.adminuserItem_ID.setText(listItems.get(position).getEmail());
        holder.adminuserItem_Code.setText(listItems.get(position).getReportName());

        final ReportUser reportUser=new ReportUser();
        reportUser.setEmail(listItems.get(position).getEmail());
        reportUser.setReason(listItems.get(position).getReason());
        reportUser.setReportCode(listItems.get(position).getReportCode());
        reportUser.setReportKey(listItems.get(position).getReportKey());
        reportUser.setReportName(listItems.get(position).getReportName());
        reportUser.setState(listItems.get(position).getState());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(v.getContext(), AdminUserViewActivity.class);
                intent.putExtra("reportUser",reportUser);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listItems != null ? listItems.size() : 0);
    }

    public class adminUserListHolder extends RecyclerView.ViewHolder{
        TextView adminuserItem_ID;
        TextView adminuserItem_Code;

        public adminUserListHolder(@NonNull View itemView) {
            super(itemView);
            this.adminuserItem_Code=itemView.findViewById(R.id.adminuserItem_code);
            this.adminuserItem_ID=itemView.findViewById(R.id.adminuserItem_ID);
        }
    }
}
