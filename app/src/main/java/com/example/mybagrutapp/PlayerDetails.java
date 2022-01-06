package com.example.mybagrutapp;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerDetails extends AppCompatActivity {

    LinearLayout linearL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_details);

        linearL = findViewById(R.id.linearL);
        linearL.setBackgroundColor(#aabbcc);

    }
}