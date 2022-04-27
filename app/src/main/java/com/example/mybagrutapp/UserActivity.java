package com.example.mybagrutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends OptionsMenuActivity implements View.OnClickListener {

    private Button addBtn, editBtn, logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
        editBtn = findViewById(R.id.editBtn);
        editBtn.setOnClickListener(this);
        logOut = findViewById(R.id.logoutBtn);
        logOut.setOnClickListener(this);

    }


    @Override
    public void onClick(View v)
    {

        if (addBtn == v)
        {
            Intent intent = new Intent(this,AddPlayer.class);
            startActivity(intent);
        }

        else if (editBtn == v)
        {
            Intent intent = new Intent(this,EditPlayer.class);
            startActivity(intent);
        }

        else if (logOut == v)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
}