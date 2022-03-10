package com.example.mybagrutapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class AddPlayer extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 1;
    private TextView tvWelcome;
    private EditText edTitName, edFullName, edSName, edYear, edMonth, edDay, edAge, edHeight, edPos, edCrTeam,
            edNum, edNltTeam, edGoals, edAsissts, edNltGoals, edFteams, edInfo, edWikiUrl, edInstaUrl;
    private Button savePlayer, uploadBtn;
    private ImageView imageView;
    private Uri filePath;
    //instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplayer);

        tvWelcome = findViewById(R.id.tvWelcome);
        edTitName = findViewById(R.id.edTitName);
        edFullName = findViewById(R.id.edFullName);
        edSName = findViewById(R.id.edSName);
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
        uploadBtn = findViewById(R.id.uploadBtn);
        uploadBtn.setOnClickListener(this);
        imageView = findViewById(R.id.image);
        //get the Firebase storage Reference
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

    }


    @Override
    public void onClick(View v)
    {

        if(savePlayer == v)
        {

            String birthday = Integer.parseInt( edDay.getText().toString() ) + " " + edMonth.getText().toString() + " " + Integer.parseInt( edYear.getText().toString() );

            if (filePath != null) {

                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                StorageReference ref = storageRef.child("images/" + edSName.getText().toString());
                ref.putFile(filePath)
                        .addOnSuccessListener(
                                new OnSuccessListener<UploadTask.TaskSnapshot>()
                                {

                                    @Override
                                    public void onSuccess(
                                            UploadTask.TaskSnapshot taskSnapshot)
                                    {

                                        // Image uploaded successfully
                                        // Dismiss dialog
                                        progressDialog.dismiss();
                                        Toast
                                                .makeText(AddPlayer.this,
                                                        "Image Uploaded!!",
                                                        Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                })

                        .addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {

                                // Error, Image not uploaded
                                progressDialog.dismiss();
                                e.printStackTrace();
                                Toast
                                        .makeText(AddPlayer.this,
                                                "Failed " + e.getMessage(),
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .addOnProgressListener(
                                new OnProgressListener<UploadTask.TaskSnapshot>()
                                {

                                    // Progress Listener for loading
                                    // percentage on the dialog box
                                    @Override
                                    public void onProgress(
                                            UploadTask.TaskSnapshot taskSnapshot)
                                    {
                                        double progress
                                                = (100.0
                                                * taskSnapshot.getBytesTransferred()
                                                / taskSnapshot.getTotalByteCount());
                                        progressDialog.setMessage(
                                                "Uploaded "
                                                        + (int) progress + "%");
                                    }
                                });

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("players").push();

                Player player = new Player(edTitName.getText().toString(),
                        edFullName.getText().toString(),
                        edSName.getText().toString(),
                        birthday,
                        Integer.parseInt(edAge.getText().toString()),
                        edHeight.getText().toString(),
                        edPos.getText().toString(),
                        edCrTeam.getText().toString(),
                        Integer.parseInt(edNum.getText().toString()),
                        edNltTeam.getText().toString(),
                        Integer.parseInt(edGoals.getText().toString()),
                        Integer.parseInt(edAsissts.getText().toString()),
                        Integer.parseInt(edNltGoals.getText().toString()),
                        edFteams.getText().toString(),
                        edInfo.getText().toString(),
                        edWikiUrl.getText().toString(),
                        edInstaUrl.getText().toString(),
                        "images/" + edSName.getText().toString());

                myRef.setValue(player);

                Toast.makeText(this, "Player saved", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }

        }

        if (uploadBtn == v)
        {

            selectImage();


        }

    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1 && data != null && data.getData() != null)
        {

            filePath = data.getData();

            try
            {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

    }

}