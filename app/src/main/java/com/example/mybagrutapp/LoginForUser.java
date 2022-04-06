package com.example.mybagrutapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginForUser extends AppCompatActivity {

    private Button loginBtn, reginBtn;
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.edUserN);
        password = findViewById(R.id.edPass);
        loginBtn = findViewById(R.id.submitBtn);
        reginBtn = findViewById(R.id.reginBtn);

    }


}