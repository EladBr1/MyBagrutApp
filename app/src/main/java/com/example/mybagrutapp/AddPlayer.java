package com.example.mybagrutapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.MalformedURLException;
import java.net.URL;

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

    public void savePlayer(View view)
    {

        String birthday = Integer.parseInt( edDay.getText().toString() ) + " " + edMonth.getText().toString() + " " + Integer.parseInt( edYear.getText().toString() );

        URL urlWiki = null, urlInsta = null;

        try {
            urlWiki = new URL(edWikiUrl.getText().toString());
            Log.d("Mine", "URL created: " + urlWiki);
            urlInsta = new URL(edInstaUrl.getText().toString());
            Log.d("Mine", "URL created: " + urlInsta);
        }
        catch (MalformedURLException e) {
            Log.d("Mine","Malformed URL: " + e.getMessage());
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("players");

        Player player = new Player( edTitName.getText().toString(),
                edFullName.getText().toString(),
                birthday,
                Integer.parseInt( edAge.getText().toString() ),
                edHeight.getText().toString(),
                edPos.getText().toString(),
                edCrTeam.getText().toString(),
                Integer.parseInt( edNum.getText().toString() ),
                edNltTeam.getText().toString(),
                Integer.parseInt( edGoals.getText().toString() ),
                Integer.parseInt( edAsissts.getText().toString() ),
                Integer.parseInt( edNltGoals.getText().toString() ),
                edFteams.getText().toString(),
                edInfo.getText().toString(),
                urlWiki,
                urlInsta,);

        myRef.setValue("player");

    }

}