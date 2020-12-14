package com.wims.whereismystore.Activity.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.wims.whereismystore.Activity.LoginActivity;
import com.wims.whereismystore.Activity.PasswordCheckActivity;
import com.wims.whereismystore.Activity.PerInfoActivity;
import com.wims.whereismystore.Activity.UserUploadPostActivity;
import com.wims.whereismystore.Class.Users;
import com.wims.whereismystore.R;

public class Fragment5 extends Fragment {
    //마이페이지기능 class

    private View view;
    String userID;




    Users user = new Users();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment5, container, false);





        String email=((Users)getActivity().getApplication()).getEmail();


        TextView textView = view.findViewById(R.id.ID);
        textView.setText(email);

        final String pass=user.getPassword();
        userID=((Users)getActivity().getApplication()).getEmail();


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






        Button logoutbnt=view.findViewById(R.id.logout);
        logoutbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("로그아웃");
                alertDialog.setMessage("로그아웃 하시겠습니까??").setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(getActivity(), LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();

            }
        });



        Button My_Sale=view.findViewById(R.id.mysale);
        My_Sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), UserUploadPostActivity.class);
                intent.putExtra("uploadUserID",userID);
                startActivity(intent);
            }
        });






        return view;


    }


}
