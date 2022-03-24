package com.example.mybagrutapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

public class EditPlayer extends AppCompatActivity
{

    private EditText edSearchB;
    private Button minGBtn, plusGBtn, minABtn, plusABtn, minGnBtn, plusGnBtn, plusNBtn, minNBtn, ageBtn, saveBtn, btnSearch;
    private TextView tvNumOfGoals, tvNumOfAsisst, tvNumOfNgoals, tvShirtNum, tvNewAge, tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);

        edSearchB = findViewById(R.id.edSearchBar);
        minABtn = findViewById(R.id.minABtn);
        plusABtn = findViewById(R.id.plusABtn);
        minGBtn = findViewById(R.id.minGBtn);
        plusGBtn = findViewById(R.id.plusGBtn);
        minGnBtn = findViewById(R.id.minGnBtn);
        plusGnBtn = findViewById(R.id.plusGnBtn);
        minNBtn = findViewById(R.id.minNBtn);
        plusNBtn = findViewById(R.id.plusNBtn);
        ageBtn = findViewById(R.id.ageBtn);
        saveBtn = findViewById(R.id.btnSaveChanges);
        tvName = findViewById(R.id.tvName);
        btnSearch = findViewById(R.id.btnSearch);


        final ArrayList<Player> players = new ArrayList<>();

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

                String age, num, ntlG, goal, asisst;
                boolean found = false;

                for (int i = 0; i < players.size(); i++) {

                    if (edSearchB.equals(players.get(i).getSName()) || edSearchB.equals(players.get(i).getTitName()) || edSearchB.equals(players.get(i).getFullName())) {
                        age = String.valueOf(players.get(i).getAge());
                        num = String.valueOf(players.get(i).getNum());
                        ntlG = String.valueOf(players.get(i).getNtlGoals());
                        goal = String.valueOf(players.get(i).getGoals());
                        asisst = String.valueOf(players.get(i).getAsissts());


                        found = true;

                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(EditPlayer.this, "error finding player", Toast.LENGTH_SHORT).show();
            }
        });

    }
}