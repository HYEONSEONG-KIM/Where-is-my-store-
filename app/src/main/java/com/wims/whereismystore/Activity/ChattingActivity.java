package com.wims.whereismystore.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.wims.whereismystore.Class.ChatModel;
import com.wims.whereismystore.Class.Users;
import com.wims.whereismystore.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ChattingActivity extends AppCompatActivity {
    private FirebaseRemoteConfig mFirebaseRemoteConfig;



    private DatabaseReference mFirebaseDatabaseReference;
    private EditText mMessageEditText;
    private Button send_bnt;





    private String mUsername;
    private String mPhotoUrl;
    private String mEmail;

    private String UID;
    private String UNAME;
    private String chatRoomUid;

    private String Opponent; //아이디와 이름 합쳐서
    private String My;

    private String my; //.을+로 바꾸어 저장
    private String op;

    private String destinationUid;


    TextView chat_text;




    private RecyclerView mMessageRecyclerView;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ChattingActivity.this, SaleActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);




        Intent intent = getIntent();
        UID = intent.getExtras().getString("UID");  //상대방의 아이디
        UNAME = intent.getExtras().getString("NAME"); //상대방의 이름




        op = intent.getExtras().getString("destinationUid");
        mUsername = ((Users) getApplication()).getName(); //내 이름
        mEmail = ((Users) getApplication()).getEmail(); //내 아이디

        Log.d("test3",op);

        My = mUsername + "(" + mEmail + ")"; //나의 이름(이메일)
        my = My.replace(".", "+");

        Opponent =op.replace("+",".");

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mMessageEditText = findViewById(R.id.message_edit);
        mMessageRecyclerView = findViewById(R.id.message_recycler_view);
        chat_text=findViewById(R.id.chat_textview);

        chat_text.setText(Opponent);


        send_bnt = findViewById((R.id.send_button));
        send_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatModel chatModel = new ChatModel();
                chatModel.users.put(my, true);
                chatModel.users.put(op, true);

                if (chatRoomUid == null) {
                    FirebaseDatabase.getInstance().getReference().child("chatroom")
                            .push().setValue(chatModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            checkChatRoom();
                        }
                    });
                    mMessageEditText.setText("");

                } else {
                    ChatModel.Comment comment = new ChatModel.Comment();
                    comment.uid = my;
                    comment.message = mMessageEditText.getText().toString();
                    comment.name=mUsername;
                    FirebaseDatabase.getInstance().getReference().child("chatroom").child(chatRoomUid)
                            .child("comments").push().setValue(comment);
                    mMessageEditText.setText("");
                }
            }
        });

        checkChatRoom();


//        // 키보드 올라올 때 RecyclerView의 위치를 마지막 포지션으로 이동
//        mMessageRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                if (bottom < oldBottom) {
//                    v.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            mMessageRecyclerView.smoothScrollToPosition(mFirebaseAdapter.getItemCount());
//                        }
//                    }, 100);
//                }
//            }
//        });



    }

    void checkChatRoom() {

        FirebaseDatabase.getInstance().getReference().child("chatroom").orderByChild("users/"+ my).equalTo(true)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot item : snapshot.getChildren()) {
                            ChatModel chatModel = item.getValue(ChatModel.class);
                            if (chatModel.users.containsKey(op)) {
                                chatRoomUid = item.getKey(); //방 아이디
                                mMessageRecyclerView.setLayoutManager(new LinearLayoutManager(ChattingActivity.this));
                                mMessageRecyclerView.setAdapter(new RecyclerViewAdapter());

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        List<ChatModel.Comment> comments;
        public RecyclerViewAdapter() {
            comments=new ArrayList<>();

            FirebaseDatabase.getInstance().getReference().child("chatroom").child(chatRoomUid)
                    .child("comments").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    comments.clear();

                    for(DataSnapshot item : snapshot.getChildren()){
                        comments.add(item.getValue(ChatModel.Comment.class));
                    }

                    notifyDataSetChanged();

                    mMessageRecyclerView.scrollToPosition(comments.size()-1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);

            return new MessageViewHolder(view);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if(comments.get(position).uid.equals(my)) {
                ((MessageViewHolder) holder).textView_message.setText(comments.get(position).message);
                ((MessageViewHolder) holder).textView_name.setText(comments.get(position).name);
            }

            else{
                ((MessageViewHolder) holder).textView_message.setText(comments.get(position).message);
                ((MessageViewHolder) holder).textView_name.setText(comments.get(position).name);
            }

        }



        @Override
        public int getItemCount() {
            return comments.size();
        }

        private class MessageViewHolder extends RecyclerView.ViewHolder {
            public TextView textView_message;
            public TextView textView_name;




            public MessageViewHolder(View view) {
                super(view);
                textView_message=view.findViewById(R.id.messageTextview);
                textView_name=view.findViewById(R.id.messageItem_textview_name);


            }
        }




    }



}


