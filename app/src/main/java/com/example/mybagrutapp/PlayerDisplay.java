package com.example.mybagrutapp;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class PlayerDisplay extends AppCompatActivity implements View.OnClickListener {
    private TextView tvTitName, tvFullName, tvBirthday, tvAge, tvHeight, tvPos, tvTeam, tvNum, tvNtlTeam, tvNtlGoals,
            tvGoals, tvAsissts, tvFormerTeams, tvInfo, wikiUrl, instaUrl;
    private ImageView imageView;
    private Button copyWiki, copyInsta, btnDi;
    private Dialog dialogNotF;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_display);

        initViews();

        Intent intent=getIntent();
        String searchResults = intent.getExtras().getString("searchResults");

        final ArrayList<Player> players = new ArrayList<>();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

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

                boolean found = false;
                for(int i = 0; i < players.size(); i++)
                {
                    found = check(i);
                    if (check(i) == true)
                        break;
                }
                if (found == false)
                    createErrorDialog();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(PlayerDisplay.this, "error finding player", Toast.LENGTH_SHORT).show();
            }

            public boolean check(int i)
            {
                String age, num, ntlG, goal, asisst;
                boolean found = false;

                if (searchResults.toUpperCase().equals(players.get(i).getSName().toUpperCase())  || searchResults.toUpperCase().equals(players.get(i).getTitName().toUpperCase()) || searchResults.toUpperCase().equals(players.get(i).getFullName().toUpperCase()) )
                {

                    age = String.valueOf(players.get(i).getAge());
                    num = String.valueOf(players.get(i).getNum());
                    ntlG = String.valueOf(players.get(i).getNtlGoals());
                    goal = String.valueOf(players.get(i).getGoals());
                    asisst = String.valueOf(players.get(i).getAsissts());

                    setTextViews(age, num, ntlG, goal, asisst, i);
                    setImage(players.get(i).getSName());
                    found = true;
                }
                return found;
            }

            public void setTextViews(String age, String num, String ntlG, String goal, String asisst, int i)
            {
                tvTitName.setText(players.get(i).getTitName());
                tvFullName.setText(players.get(i).getFullName());
                tvBirthday.setText(players.get(i).getBirthday());
                tvAge.setText(age);
                tvHeight.setText(players.get(i).getHeight());
                tvPos.setText(players.get(i).getPos());
                tvTeam.setText(players.get(i).getCrTeam());
                tvNum.setText(num);
                tvNtlTeam.setText(players.get(i).getNltTeam());
                tvNtlGoals.setText(ntlG);
                tvGoals.setText(goal);
                tvAsissts.setText(asisst);
                tvFormerTeams.setText(players.get(i).getFormerTeams());
                tvInfo.setText(players.get(i).getBasicInfo());
                wikiUrl.setText(players.get(i).getWiki());
                instaUrl.setText(players.get(i).getInsta());
            }


        });

        copyWiki = findViewById(R.id.copyWikiBtn);
        copyWiki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

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

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView", instaUrl.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(PlayerDisplay.this, "Link for Instagram copied",Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void createErrorDialog()
    {

        dialogNotF = new Dialog(this);
        dialogNotF.setContentView(R.layout.layout_dialog);
        btnDi = dialogNotF.findViewById(R.id.btnDi);
        dialogNotF.setCancelable(true);
        btnDi.setOnClickListener(this);
        dialogNotF.show();

    }

    public void setImage( String n )
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