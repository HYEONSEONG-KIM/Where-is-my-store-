package com.wims.whereismystore.Activity.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wims.whereismystore.Activity.PerInfoActivity;
import com.wims.whereismystore.R;

public class Fragment5 extends Fragment {
    //마이페이지기능 class
    private View view;
    String email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment5, container, false);

        email = "kimgustjd@naver.com";
        TextView textView = view.findViewById(R.id.ID);
        textView.setText(email);



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PerInfoActivity.class);
                startActivity(intent);
            }
        });
        return view;


    }

}