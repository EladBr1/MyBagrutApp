package com.example.mybagrutapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlayerDisplay extends AppCompatActivity
{
    private TextView tvTitName, tvFullName, tvBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_display);

        tvTitName =(TextView)findViewById(R.id.titName);

        Intent intent=getIntent();
        String searchResults = intent.getExtras().getString("searchResults");

        ArrayList<Player> players = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://champions-league-legends-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("players");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Player player = snapshot.getValue(Player.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}