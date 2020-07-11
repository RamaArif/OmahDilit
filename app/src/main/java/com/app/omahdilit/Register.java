package com.app.omahdilit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @OnClick(R.id.register_button_register) void register(){
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
    }
}