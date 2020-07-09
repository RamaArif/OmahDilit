package com.app.omahdilit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity {
    @BindView(R.id.login_layout_email) TextInputLayout login_layout_email;
    @BindView(R.id.login_layout_password) TextInputLayout login_layout_password;
    @BindView(R.id.login_input_email) TextInputEditText login_input_email;
    @BindView(R.id.login_input_password) TextInputEditText login_input_password;
    @BindView(R.id.login_button_email) Button login_button_email;
    @BindView(R.id.login_button_google) Button login_button_google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
}