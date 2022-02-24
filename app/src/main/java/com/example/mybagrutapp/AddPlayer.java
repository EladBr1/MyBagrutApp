package com.example.mybagrutapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AddPlayer extends AppCompatActivity implements View.OnClickListener {

    private TextView tvWelcome;
    private EditText edTitName, edFullName, edYear, edMonth, edDay, edAge, edHeight, edPos, edCrTeam, edNum, edNltTeam, edGoals, edAsissts, edNltGoals, edFteams, edInfo, edWikiUrl, edInstaUrl;
    private Button uploadBtn;
    private Button savePlayer;
    private ImageView imageView;
    private Uri filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplayer);

        tvWelcome = findViewById(R.id.tvWelcome);
        edTitName = findViewById(R.id.edTitName);
        edFullName = findViewById(R.id.edFullName);
        edYear = findViewById(R.id.edYear);
        edMonth = findViewById(R.id.edMonth);
        edDay = findViewById(R.id.edDay);
        edAge = findViewById(R.id.edAge);
        edHeight = findViewById(R.id.edHeight);
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
        uploadBtn.setOnClickListener(this);
        savePlayer = findViewById(R.id.savePlayer);
        savePlayer.setOnClickListener(this);
        imageView = findViewById(R.id.ivPro);

    }


    @Override
    public void onClick(View v)
    {


        if (uploadBtn == v)
        {

            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

        }

       if(savePlayer == v)
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
                    urlInsta,
                    imageView );

            myRef.setValue(player);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1 && data != null && data.getData() != null) {

            filePath = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}