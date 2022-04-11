package com.example.mybagrutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SortActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_sort);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<Player> players = new ArrayList<>();

        PlayerAdapter adapter = new PlayerAdapter(players);
        recyclerView.setAdapter(adapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://champions-league-legends-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("players");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Player player = snapshot.getValue(Player.class);
                players.clear();
                for (DataSnapshot playerSnapshot : snapshot.getChildren()) {

                    Player currentPlayer = playerSnapshot.getValue(Player.class);
                    players.add(currentPlayer);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(SortActivity.this, "error", Toast.LENGTH_SHORT).show();
            }

        });
    }



}