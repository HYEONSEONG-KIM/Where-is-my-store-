package com.wims.whereismystore.Activity.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wims.whereismystore.Activity.ChattingActivity;
import com.wims.whereismystore.Class.ChatModel;
import com.wims.whereismystore.Class.Users;
import com.wims.whereismystore.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Fragment3 extends Fragment {
    //채팅기능 class
    private View view;
    private String name;
    private String id;

    private String uname;
    private String UNAME;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3, container, false);



        name=((Users)getActivity().getApplication()).getName();
        id=((Users)getActivity().getApplication()).getEmail();
        uname=name+"("+id+")";
        UNAME=uname.replace(".","+");


        RecyclerView recyclerView=view.findViewById(R.id.chatfragment_recyclerview);
        recyclerView.setAdapter(new ChatRecyclerviewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));


        return view;
    }

    class ChatRecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private List<ChatModel> chatModels=new ArrayList<>();
        private String uid;
        private ArrayList<String> destinationUid=new ArrayList<>();

        public ChatRecyclerviewAdapter() {
            uid=UNAME;
            FirebaseDatabase.getInstance().getReference().child("chatroom").orderByChild("users/"+uid).equalTo(true).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    chatModels.clear();
                    for(DataSnapshot item:snapshot.getChildren()){
                        chatModels.add(item.getValue(ChatModel.class));

                    }
                    notifyDataSetChanged();
                    Log.d("test1",chatModels.toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Log.d("test1",chatModels.toString());

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);

            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            final CustomViewHolder customViewHolder=(CustomViewHolder)holder;
            String op=null;
            String oprepalce=null;


            for(String user: chatModels.get(position).users.keySet()){
                if(!user.equals(uid)) {
                    op = user;
                    oprepalce=op.replace("+",".");
                    destinationUid.add(op);

                }
            }
            Log.d("test2",destinationUid.toString());
            Log.d("test2",oprepalce);

            customViewHolder.textView_title.setText(oprepalce);
//            FirebaseDatabase.getInstance().getReference().child("users").child(op).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                    //UserModel userModel=snapshot.getValue(UserModel.class);
//                    //Log.d("test1",userModel.toString());
//                    //customViewHolder.textView_title.setText(userModel.userName);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });


            Map<String,ChatModel.Comment> commentMap=new TreeMap<>(Collections.<String>reverseOrder());
            commentMap.putAll(chatModels.get(position).comments);
            String lastMessagekey=(String)commentMap.keySet().toArray()[0];
            customViewHolder.textView_lastmessage.setText(chatModels.get(position).comments.get(lastMessagekey).message);

            customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(view.getContext(), ChattingActivity.class);
                    intent.putExtra("destinationUid",destinationUid.get(position));
                    startActivity(intent);
                    Log.d("test2",destinationUid.toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return chatModels.size();
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textView_title;
            public TextView textView_lastmessage;



            public CustomViewHolder(View view) {
                super(view);
                imageView=view.findViewById(R.id.chatitem_Imageview);
                textView_title=view.findViewById(R.id.chatitem_textview_title);
                textView_lastmessage=view.findViewById(R.id.cahtitem_textview_lastmessage);



            }
        }
    }
}
