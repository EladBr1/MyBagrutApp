package com.example.mybagrutapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerDisplay extends AppCompatActivity
{
    private TextView tvTitName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_display);

        tvTitName =(TextView)findViewById(R.id.titName);

        Intent intent=getIntent();
        String searchResults = intent.getExtras().getString("searchResults");


    }



}