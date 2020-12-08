package com.wims.whereismystore.Activity.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wims.whereismystore.Activity.PasswordCheckActivity;
import com.wims.whereismystore.Activity.PerInfoActivity;
import com.wims.whereismystore.Class.Users;
import com.wims.whereismystore.R;



public class Fragment5 extends Fragment {
    //마이페이지기능 class

    private View view;


     Users user = new Users();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment5, container, false);

        String email=((Users)getActivity().getApplication()).getEmail();


        TextView textView = view.findViewById(R.id.ID);
        textView.setText(email);

        final String pass=user.getPassword();





        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PerInfoActivity.class);
                startActivity(intent);
            }
        });

        Button changebnt=(Button)view.findViewById(R.id.change);
        changebnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(getActivity(), PasswordCheckActivity.class);
                startActivity(intent2);
            }
        });


        return view;


    }

}