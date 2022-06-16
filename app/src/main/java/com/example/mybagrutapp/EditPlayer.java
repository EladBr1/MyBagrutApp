package com.example.mybagrutapp;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditPlayer extends OptionsMenuActivity {

    private EditText edSearchB; //search bar
    private Button minGBtn, plusGBtn, minABtn, plusABtn, minGnBtn, plusGnBtn, plusNBtn, minNBtn, ageBtn, saveBtn, btnSearch;//buttons to edit the player details
    private TextView tvNumOfGoals, tvNumOfAsisst, tvNumOfNgoals, tvShirtNum, tvNewAge, tvName; // views of the player details
    private LinearLayout editLayout;// the layout of the edit tools
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);

        broadcastReceiver = new NetworkChangeRecevier();
        registerNetworkBroadcastReceiver();

        initViews();

        final ArrayList<Player> players = new ArrayList<>();// list of players

        //get instance for firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://champions-league-legends-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("players");

        //use the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Player player = snapshot.getValue(Player.class);
                players.clear();//clear the list

                //add all the players of the list
                for (DataSnapshot playerSnapshot : snapshot.getChildren())
                {
                    Player currentPlayer = playerSnapshot.getValue(Player.class);
                    players.add(currentPlayer);
                    currentPlayer.setKey(playerSnapshot.getKey());
                }

                //serch the player
                btnSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String searchB = edSearchB.getText().toString();
                        boolean found = false;//if the player found
                        boolean isMatched = found;
                        for (int i = 0; i < players.size(); i++) {

                            isMatched = searchB.toUpperCase().equals(players.get(i).getSName().toUpperCase()) ||
                                    searchB.toUpperCase().equals(players.get(i).getTitName().toUpperCase()) ||
                                    searchB.toUpperCase().equals(players.get(i).getFullName().toUpperCase());
                            //check if the name matches to one of the players name
                            if ( isMatched ) {

                                editLayout.setVisibility(View.VISIBLE);//set the edit layout to visible
                                setAllTheNums(i);//set the views to the player details
                                buttons(i);//listen to the buttons
                                found = true; //the player was founded
                            }

                        }
                        //if the player has not been founded
                        if (found == false)
                            Toast.makeText(EditPlayer.this, "No player found, try using a different name.", Toast.LENGTH_SHORT).show();

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditPlayer.this, "error finding player", Toast.LENGTH_SHORT).show();
            }

            //listen to the buttons
            public void buttons(int i) {

                minGBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int num = players.get(i).getGoals();//get the player goals
                        myRef.child(players.get(i).getKey()).child("goals").setValue(num-1);//set the goals
                        String str = String.valueOf(num-1);
                        tvNumOfGoals.setText(str);//set the view of the player
                    }
                });

                plusGBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int num = players.get(i).getGoals();
                        myRef.child(players.get(i).getKey()).child("goals").setValue(num+1);
                        String str = String.valueOf(num+1);
                        tvNumOfGoals.setText(str);
                    }
                });

                minABtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int num = players.get(i).getAsissts();
                        myRef.child(players.get(i).getKey()).child("asissts").setValue(num-1);
                        String str = String.valueOf(num-1);
                        tvNumOfAsisst.setText(str);
                    }
                });

                plusABtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int num = players.get(i).getAsissts();
                        myRef.child(players.get(i).getKey()).child("asissts").setValue(num+1);
                        String str = String.valueOf(num+1);
                        tvNumOfAsisst.setText(str);
                    }
                });

                minGnBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int num = players.get(i).getNtlGoals();
                        myRef.child(players.get(i).getKey()).child("ntlGoals").setValue(num-1);
                        myRef.child(players.get(i).getKey()).child("goals").setValue(num-1);
                        String str = String.valueOf(players.get(i).getNtlGoals());
                        tvNumOfNgoals.setText(str);
                    }
                });

                plusGnBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int num = players.get(i).getNtlGoals();
                        myRef.child(players.get(i).getKey()).child("ntlGoals").setValue(num+1);
                        myRef.child(players.get(i).getKey()).child("goals").setValue(num+1);
                        String str = String.valueOf(players.get(i).getNtlGoals());
                        tvNumOfNgoals.setText(str);
                    }
                });

                plusNBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int num = players.get(i).getNum();
                        myRef.child(players.get(i).getKey()).child("num").setValue(num+1);
                        String str = String.valueOf(num+1);
                        tvShirtNum.setText(str);
                    }
                });

                minNBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int num = players.get(i).getNum();
                        myRef.child(players.get(i).getKey()).child("num").setValue(num-1);
                        String str = String.valueOf(num-1);
                        tvShirtNum.setText(str);
                    }
                });

                ageBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int num = players.get(i).getAge();
                        myRef.child(players.get(i).getKey()).child("age").setValue(num+1);
                        String str = String.valueOf(num+1);
                        tvNewAge.setText(str);
                    }
                });

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(EditPlayer.this, UserActivity.class);
                        startActivity(intent);
                    }
                });

            }

            public void setAllTheNums(int i) {
                String age, num, ntlG, goal, assist;
                age = String.valueOf(players.get(i).getAge());
                num = String.valueOf(players.get(i).getNum());
                ntlG = String.valueOf(players.get(i).getNtlGoals());
                goal = String.valueOf(players.get(i).getGoals());
                assist = String.valueOf(players.get(i).getAsissts());

                tvName.setText(players.get(i).getTitName());
                tvNumOfGoals.setText(goal);
                tvNumOfAsisst.setText(assist);
                tvNumOfNgoals.setText(ntlG);
                tvShirtNum.setText(num);
                tvNewAge.setText(age);
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

    private void initViews()
    {
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
        tvName = findViewById(R.id.tvPlName);
        btnSearch = findViewById(R.id.btnSearch);
        tvNumOfGoals = findViewById(R.id.tvNumOfGoals);
        tvNumOfAsisst = findViewById(R.id.tvNumOfAsisst);
        tvNumOfNgoals = findViewById(R.id.tvNumOfNgoals);
        tvShirtNum = findViewById(R.id.tvShirtNum);
        tvNewAge = findViewById(R.id.tvNewAge);
        editLayout = (LinearLayout) (findViewById(R.id.ln));
        editLayout.setVisibility(View.INVISIBLE);
    }

}