package com.wims.whereismystore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.wims.whereismystore.Class.ChatMessage;
import com.wims.whereismystore.Class.ChatModel;
import com.wims.whereismystore.Class.Users;
import com.wims.whereismystore.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChattingActivity extends AppCompatActivity {
    private FirebaseRemoteConfig mFirebaseRemoteConfig;


    public static final String CHAT = "chat";
    private FirebaseRecyclerAdapter<ChatMessage,MessageViewHolder>mFirebaseAdapter;

    private DatabaseReference mFirebaseDatabaseReference;
    private EditText mMessageEditText;
    private Button send_bnt;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    private String mUsername;
    private String mPhotoUrl;
    private String mEmail;

    private String UID;
    private String UNAME;

    public static class MessageViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView;
        TextView messageTextView;
        CircleImageView photoImageView;
        ImageView messageImageView;


        public MessageViewHolder(View itemView) {
            super(itemView);

            nameTextView=itemView.findViewById(R.id.nameTextView);
            messageImageView=itemView.findViewById(R.id.messageImageview);
            messageTextView=itemView.findViewById(R.id.messageTextview);
            photoImageView=itemView.findViewById(R.id.photoImageview);
        }


    }

    private RecyclerView mMessageRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        Intent intent=getIntent();
        UID=intent.getExtras().getString("UID");
        UNAME=intent.getExtras().getString("NAME");



        mUsername=((Users)getApplication()).getName();
        mEmail=((Users)getApplication()).getEmail();


        mFirebaseDatabaseReference= FirebaseDatabase.getInstance().getReference();
        mMessageEditText=findViewById(R.id.message_edit);
        mMessageRecyclerView=findViewById(R.id.message_recycler_view);



        send_bnt=findViewById((R.id.send_button));
        send_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatModel chatModel=new ChatModel();
                chatModel.My_id=mUsername;
                chatModel.UID=UID;

                FirebaseDatabase.getInstance().getReference().child(CHAT).push().setValue(chatModel);

            }
        });


        Query query=mFirebaseDatabaseReference.child(CHAT);
        FirebaseRecyclerOptions<ChatMessage>options=new FirebaseRecyclerOptions.Builder<ChatMessage>()
                .setQuery(query,ChatMessage.class).build();
        mFirebaseAdapter=new FirebaseRecyclerAdapter<ChatMessage, MessageViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MessageViewHolder holder, int position, @NonNull ChatMessage model) {
                holder.messageTextView.setText(model.getText());
                holder.nameTextView.setText(model.getName());
                if(model.getPhotoUrl()==null){
                    holder.photoImageView.setImageDrawable(ContextCompat.getDrawable(ChattingActivity.this,R.drawable.ic_baseline_account_circle_24));
                }
                else{
                    Glide.with(ChattingActivity.this).load(model.getPhotoUrl()).into(holder.photoImageView);
                }
            }

            @NonNull
            @Override
            public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);
                return new MessageViewHolder(view);
            }
        };
        mMessageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecyclerView.setAdapter(mFirebaseAdapter);

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                LinearLayoutManager layoutManager = (LinearLayoutManager) mMessageRecyclerView.getLayoutManager();
                int lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition();

                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    mMessageRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        // 키보드 올라올 때 RecyclerView의 위치를 마지막 포지션으로 이동
        mMessageRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mMessageRecyclerView.smoothScrollToPosition(mFirebaseAdapter.getItemCount());
                        }
                    }, 100);
                }
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAdapter.stopListening();
    }


}