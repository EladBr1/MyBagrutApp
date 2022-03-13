package com.example.mybagrutapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView tvTitName, tvFullName, tvBirthday, tvAge, tvHeight, tvPos, tvTeam, tvNum, tvNtlTeam, tvNtlGoals,
            tvGoals, tvAsissts, tvFormerTeams, tvInfo, wikiUrl, instaUrl;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_display);

        tvTitName = findViewById(R.id.titName);
        imageView = findViewById(R.id.pic);
        tvFullName = findViewById(R.id.tvFullName);
        tvBirthday = findViewById(R.id.tvBirthDay);
        tvAge = findViewById(R.id.tvAge);
        tvHeight = findViewById(R.id.tvHeight);
        tvPos = findViewById(R.id.tvPos);
        tvTeam = findViewById(R.id.tvTeam);
        tvNum = findViewById(R.id.tvNum);
        tvNtlTeam = findViewById(R.id.tvNtlTeam);
        tvNtlGoals = findViewById(R.id.tvNtlGoals);
        tvGoals = findViewById(R.id.tvGoals);
        tvAsissts = findViewById(R.id.tvAsissts);
        tvFormerTeams = findViewById(R.id.tvFormerTeams);
        tvInfo = findViewById(R.id.tvInfo);
        wikiUrl = findViewById(R.id.wikiUrl);
        instaUrl = findViewById(R.id.instaUrl);

        Intent intent=getIntent();
        String searchResults = intent.getExtras().getString("searchResults");

        final ArrayList<Player> players = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://champions-league-legends-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("players");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Player player = snapshot.getValue(Player.class);

                players.clear();
                for(DataSnapshot playerSnapshot : snapshot.getChildren())
                {

                    Player currentPlayer = playerSnapshot.getValue(Player.class);
                    players.add(currentPlayer);
                }


               for(int i = 0; i < players.size(); i++)
                {

                    if (searchResults == players.get(i).getSName() || searchResults == players.get(i).getTitName() || searchResults == players.get(i).getFullName() )
                    {

                        tvTitName.setText(players.get(i).getTitName());
                        tvFullName.setText(players.get(i).getFullName());


                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PlayerDisplay.this, "error finding player", Toast.LENGTH_SHORT).show();
            }
        });

    }



}