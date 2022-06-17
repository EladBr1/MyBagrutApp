package com.example.mybagrutapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class PlayerDisplay extends OptionsMenuActivity implements View.OnClickListener {
    private TextView tvTitName, tvFullName, tvBirthday, tvAge, tvHeight, tvPos, tvTeam, tvNum, tvNtlTeam, tvNtlGoals,
            tvGoals, tvAsissts, tvFormerTeams, tvInfo, wikiUrl, instaUrl; //text of the player details
    private ImageView imageView ; //image of the player
    private Button copyWiki, copyInsta; //buttons for copying the links
    private Button btnDi; //dialog button
    private Dialog dialogNotF; //error dialog
    private BroadcastReceiver broadcastReceiver;

    //instance for firebase storage and StorageReference
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_display);

        broadcastReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastReceiver();

        initViews();
        Glide.with(getApplicationContext()).load(R.drawable.loader_pic).into(imageView);

        //get the search results from the main screen
        Intent intent=getIntent();
        String searchResults = intent.getExtras().getString("searchResults");

        final ArrayList<Player> players = new ArrayList<>(); // list of players

        //get the Firebase storage Reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //open progress dialog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Searching player...");
        progressDialog.show();

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
                for(DataSnapshot playerSnapshot : snapshot.getChildren())
                {
                    Player currentPlayer = playerSnapshot.getValue(Player.class);
                    players.add(currentPlayer);
                }


                boolean found = false;//if the player found
                for(int i = 0; i < players.size(); i++)
                {
                    found = findThePlayer(i); //check if the search results
                    if (found)
                        break;
                }
                if (!found)
                    createErrorDialog(); //open the error dialog

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(PlayerDisplay.this, "error finding player", Toast.LENGTH_SHORT).show();
            }

            //check if the search results
            public boolean findThePlayer(int location)
            {

                boolean found = false;
                boolean isMatched = searchResults.toUpperCase().equals(players.get(location).getSName().toUpperCase())  ||
                        searchResults.toUpperCase().equals(players.get(location).getTitName().toUpperCase()) ||
                        searchResults.toUpperCase().equals(players.get(location).getFullName().toUpperCase()); //if the results match to the current player

                //if this is the player
                if ( isMatched)
                {
                    //set the screen for the player
                    setTextViews(location);

                    //dismiss the dialog
                    progressDialog.dismiss();

                    //get the image from database
                    setImageFireBase(players.get(location).getSName());
                    found = true;
                }
                return found;
            }

            //set the texts for the player
            public void setTextViews(int location)
            {
                tvTitName.setText(players.get(location).getTitName());
                tvFullName.setText(players.get(location).getFullName());
                tvBirthday.setText(players.get(location).getBirthday());
                tvAge.setText(String.valueOf(players.get(location).getAge()));
                tvHeight.setText(players.get(location).getHeight());
                tvPos.setText(players.get(location).getPos());
                tvTeam.setText(players.get(location).getCrTeam());
                tvNum.setText(String.valueOf(players.get(location).getNum()));
                tvNtlTeam.setText(players.get(location).getNltTeam());
                tvNtlGoals.setText(String.valueOf(players.get(location).getNtlGoals()));
                tvGoals.setText(String.valueOf(players.get(location).getGoals()));
                tvAsissts.setText(String.valueOf(players.get(location).getAsissts()));
                tvFormerTeams.setText(players.get(location).getFormerTeams());
                tvInfo.setText(players.get(location).getBasicInfo());
                wikiUrl.setText(players.get(location).getWiki());
                instaUrl.setText(players.get(location).getInsta());
            }


        });

        copyWiki = findViewById(R.id.copyWikiBtn);

        copyWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //copy the wiki link
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView", wikiUrl.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(PlayerDisplay.this, "Link for Wikipedia copied",Toast.LENGTH_SHORT).show();

            }
        });

        copyInsta = findViewById(R.id.copyInBtn);
        copyInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //copy the in link
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView", instaUrl.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(PlayerDisplay.this, "Link for Instagram copied",Toast.LENGTH_SHORT).show();


            }
        });


    }

    //open the error dialog if results did not match to any player
    public void createErrorDialog()
    {
        dialogNotF = new Dialog(this);
        dialogNotF.setContentView(R.layout.notfound_dialog);
        btnDi = dialogNotF.findViewById(R.id.btnDi);
        dialogNotF.setCancelable(false);
        btnDi.setOnClickListener(this);
        dialogNotF.show();

    }

    //get the image from firebase
    public void setImageFireBase( String n )
    {
        StorageReference pic = storageReference.child("images/" + n);

        final long ONE_MEGABYTE = 1024 * 1024;
        pic.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    //dismiss the dialog
    @Override
    public void onClick(View view)
    {
        if (btnDi == view)
        {
            dialogNotF.dismiss();
            Intent intent = new Intent(PlayerDisplay.this, MainActivity.class);
            startActivity(intent);

        }
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
        
        
    }


}