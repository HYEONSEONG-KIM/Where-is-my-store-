package com.wims.whereismystore.Activity.Fragments;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wims.whereismystore.Activity.ChattingActivity;
import com.wims.whereismystore.R;

public class Fragment3 extends Fragment {
    //채팅기능 class
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3, container, false);

        Button testchat=(Button)view.findViewById(R.id.testchat);
        testchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(getActivity(), ChattingActivity.class);
                startActivity(intent2);
            }
        });



        return view;
    }
}
