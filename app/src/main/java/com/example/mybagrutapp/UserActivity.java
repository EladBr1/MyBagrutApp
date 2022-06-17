package com.example.mybagrutapp;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends OptionsMenuActivity implements View.OnClickListener {

    private Button addBtn, editBtn, logOut; //buttons for the user actions
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        broadcastReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastReceiver();

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
            //go to adding screen
            Intent intent = new Intent(this,AddPlayer.class);
            startActivity(intent);
        }

        else if (editBtn == v)
        {
            //go to editing screen
            Intent intent = new Intent(this,EditPlayer.class);
            startActivity(intent);
        }

        else if (logOut == v)
        {
            //log out
            FirebaseAuth.getInstance().signOut();

            //go back to login screen
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    protected void registerNetworkBroadcastReceiver()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

}