package com.example.mybagrutapp;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SortActivity extends OptionsMenuActivity
{

    Button goalsBtn, asisstBtn, ntlBtn, ageBtn; //buttons for sorting
    TextView loader;//loading view
    LinearLayout linearLayoutLoader;//layout of loading

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        broadcastReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastReceiver();

        //get the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerview_sort);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initViews();

        final ArrayList<Player> players = new ArrayList<>(); //list of players


        //get instance for firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://champions-league-legends-default-rtdb.firebaseio.com/");

        //sort the players by goals
        goalsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //sort by goals
                sortThePlayer(database, players, recyclerView, "goals");
            }
        });

        //sort the players by assists
        asisstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sort by: assists
                sortThePlayer(database, players, recyclerView, "asissts");
            }
        });

        //sort the players by national goals
        ntlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortThePlayer(database, players, recyclerView, "ntlGoals");
            }
        });

        //sort the player by age
        ageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortThePlayer(database, players, recyclerView, "age");
            }
        });

    }

    //sort the player by the selected type
    public void sortThePlayer(FirebaseDatabase database, ArrayList<Player> players, RecyclerView recyclerView, String type)
        {
            //loading view
            loader.setText("Loading...");

            Query myQuery = database.getReference("players").orderByChild(type);
            myQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    Player player = snapshot.getValue(Player.class);
                    players.clear();//clear the list

                    //add all the players of the list
                    for (DataSnapshot playerSnapshot : snapshot.getChildren()) {

                        Player currentPlayer = playerSnapshot.getValue(Player.class);
                        players.add(0,currentPlayer);
                    }
                    //set the adapter by types
                    if (type.equals("goals"))
                    {
                        PlayerAdapterG adapter = new PlayerAdapterG(players);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                    else if (type.equals("asissts"))
                    {
                        PlayerAdapterAst adapter = new PlayerAdapterAst(players);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    else if (type.equals("ntlGoals"))
                    {
                        PlayerAdapterNtl adapter = new PlayerAdapterNtl(players);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    else if (type.equals("age"))
                    {
                        PlayerAdapterAge adapter = new PlayerAdapterAge(players);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    linearLayoutLoader.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error)
                {
                    Toast.makeText(SortActivity.this, "error", Toast.LENGTH_SHORT).show();
                }

            });
        }




    protected void registerNetworkBroadcastReceiver()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void initViews()
    {
        goalsBtn = findViewById(R.id.goalBtn);
        asisstBtn = findViewById(R.id.asisstBtn);
        ntlBtn = findViewById(R.id.ntlGoalBtn);
        ageBtn = findViewById(R.id.playerAgeBtn);
        loader = findViewById(R.id.tvLoader);
        linearLayoutLoader = findViewById(R.id.lrLoader);
    }

}