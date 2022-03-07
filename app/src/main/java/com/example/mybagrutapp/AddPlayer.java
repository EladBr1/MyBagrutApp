package com.example.mybagrutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPlayer extends AppCompatActivity implements View.OnClickListener {

    private TextView tvWelcome;
    private EditText edTitName, edFullName, edYear, edMonth, edDay, edAge, edHeight, edPos, edCrTeam, edNum, edNltTeam, edGoals, edAsissts, edNltGoals, edFteams, edInfo, edWikiUrl, edInstaUrl;
    private Button savePlayer;
    //private ImageView imageView;
    //private Uri filePath;


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
        savePlayer = findViewById(R.id.savePlayer);
        savePlayer.setOnClickListener(this);

    }


    @Override
    public void onClick(View v)
    {

        if(savePlayer == v)
        {

            String birthday = Integer.parseInt( edDay.getText().toString() ) + " " + edMonth.getText().toString() + " " + Integer.parseInt( edYear.getText().toString() );

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("players").push();

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
                    edWikiUrl.getText().toString(),
                    edInstaUrl.getText().toString()/*,
                    ((BitmapDrawable)imageView.getDrawable()).getBitmap()*/);

            myRef.setValue(player);

            Toast.makeText(this, "Player saved", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }

    }
    /*
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

    }*/


}