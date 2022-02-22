package com.example.mybagrutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddPlayer extends AppCompatActivity
{

    TextView tvWelcome;
    EditText edTitName, edFullName, edYear, edMonth, edDay, edAge, edHeight, edPos, edCrTeam, edNum, edNltTeam, edGoals, edAsissts, edNltGoals, edFteams, edInfo, edWikiUrl, edInstaUrl;
    Button uploadBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplayer);

        tvWelcome = findViewById(R.id.tvWelcome);
        edTitName = findViewById(R.id.edTitName);
        edFullName = findViewById(R.id.edFullName);
        edYear = findViewById(R.id.edYear);
        edMonth = findViewById(R.id.edMonth);
        edDay = findViewById(R.id.edDay);
        edAge = findViewById(R.id.edAge);
        edHeight= findViewById(R.id.edHeight);
        edPos = findViewById(R.id.edPos);
        edCrTeam = findViewById(R.id.edCrTeam);
        edNum = findViewById(R.id.edNum);
        edNltTeam = findViewById(R.id.edNltTeam);
        edGoals = findViewById(R.id.edGoals);
        edAsissts = findViewById(R.id.edAsissts);
        edNltGoals = findViewById(R.id.edNltGoals);
        edFteams = findViewById(R.id.edFteams);
        edInfo = findViewById(R.id.edInfo);
        edWikiUrl = findViewById(R.id.edWikiUrl);
        edInstaUrl = findViewById(R.id.edInstaUrl);
        uploadBtn = findViewById(R.id.uploadBtn);
        uploadBtn.setOnClickListener((View.OnClickListener) this);




    }
}