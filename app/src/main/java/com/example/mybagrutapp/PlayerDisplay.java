package com.example.mybagrutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PlayerDisplay extends AppCompatActivity
{
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_display);

        tv=(TextView)findViewById(R.id.tvDisplay);

        Intent intent=getIntent();
        String searchResults = intent.getExtras().getString("searchResults");

        tv.setText(searchResults);

    }



}