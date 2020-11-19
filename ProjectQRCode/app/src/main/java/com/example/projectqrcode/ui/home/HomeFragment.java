package com.example.projectqrcode.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectqrcode.R;
import com.example.projectqrcode.addvehicle;
import com.example.projectqrcode.balancecheck;
import com.example.projectqrcode.changepassword;
import com.example.projectqrcode.history;
import com.example.projectqrcode.myvehicles;
import com.example.projectqrcode.recharge;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    TextView newforms;
    TextView myforms;
    TextView viewtariff;
    TextView changepsswd;
    TextView history;
    TextView changpassword;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        newforms=root.findViewById(R.id.newvehicle);

        newforms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), addvehicle.class));
            }
        });
        myforms=root.findViewById(R.id.myvehicles);

        myforms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), myvehicles.class));
            }
        });
        viewtariff=root.findViewById(R.id.balance);

        viewtariff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),balancecheck.class));
            }
        });
        changepsswd=root.findViewById(R.id.recharge);

        changepsswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), myvehicles.class));
            }
        });
        history=root.findViewById(R.id.history);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), com.example.projectqrcode.history.class));
            }
        });
        changpassword=root.findViewById(R.id.changepassword);

        changpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), changepassword.class));
            }
        });



        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }
}
