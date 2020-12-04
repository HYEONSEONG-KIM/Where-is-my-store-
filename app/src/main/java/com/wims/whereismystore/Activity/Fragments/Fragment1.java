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

import com.wims.whereismystore.Activity.SaleUploadActivity;
import com.wims.whereismystore.R;

public class Fragment1 extends Fragment {
    //매매기능 class
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        Button uploadPost=view.findViewById(R.id.saleUpload_button);

        uploadPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), SaleUploadActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}