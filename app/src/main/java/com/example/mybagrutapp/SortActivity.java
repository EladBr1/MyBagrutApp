package com.example.mybagrutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SortActivity extends AppCompatActivity
{

    Button goalsBtn, asisstBtn, ntlBtn, ageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_sort);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initViews();

        final ArrayList<Player> players = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://champions-league-legends-default-rtdb.firebaseio.com/");

        goalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query myQuery = database.getReference("players").orderByChild("goals");
                myQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        Player player = snapshot.getValue(Player.class);
                        players.clear();
                        for (DataSnapshot playerSnapshot : snapshot.getChildren()) {

                            Player currentPlayer = playerSnapshot.getValue(Player.class);
                            players.add(0,currentPlayer);
                        }
                        PlayerAdapterG adapter = new PlayerAdapterG(players);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        Toast.makeText(SortActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });


        asisstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query myQuery = database.getReference("players").orderByChild("asissts");
                myQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        Player player = snapshot.getValue(Player.class);
                        players.clear();
                        for (DataSnapshot playerSnapshot : snapshot.getChildren()) {

                            Player currentPlayer = playerSnapshot.getValue(Player.class);
                            players.add(0,currentPlayer);
                        }
                        PlayerAdapterAst adapter = new PlayerAdapterAst(players);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        Toast.makeText(SortActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });


        ntlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query myQuery = database.getReference("players").orderByChild("ntlGoals");
                myQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        Player player = snapshot.getValue(Player.class);
                        players.clear();
                        for (DataSnapshot playerSnapshot : snapshot.getChildren()) {

                            Player currentPlayer = playerSnapshot.getValue(Player.class);
                            players.add(0,currentPlayer);
                        }
                        PlayerAdapterNtl adapter = new PlayerAdapterNtl(players);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        Toast.makeText(SortActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

        ageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Query myQuery = database.getReference("players").orderByChild("age");
                myQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        Player player = snapshot.getValue(Player.class);
                        players.clear();
                        for (DataSnapshot playerSnapshot : snapshot.getChildren()) {

                            Player currentPlayer = playerSnapshot.getValue(Player.class);
                            players.add(0,currentPlayer);
                        }
                        PlayerAdapterAge adapter = new PlayerAdapterAge(players);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        Toast.makeText(SortActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

    }

    public void initViews()
    {
        goalsBtn = findViewById(R.id.goalBtn);
        asisstBtn = findViewById(R.id.asisstBtn);
        ntlBtn = findViewById(R.id.ntlGoalBtn);
        ageBtn = findViewById(R.id.playerAgeBtn);
    }

}