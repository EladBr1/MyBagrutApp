package com.example.mybagrutapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class AddPlayer extends OptionsMenuActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST = 69;
    private EditText edTitName, edFullName, edSName, edYear, edMonth, edDay, edAge, edHeight, edPos, edCrTeam,
            edNum, edNltTeam, edGoals, edAsissts, edNltGoals, edFteams, edInfo, edWikiUrl, edInstaUrl; //the player details to fill
    private ImageView imageView; //the view of the image that the user choose
    private Uri filePath = null;
    private BroadcastReceiver broadcastReceiver;

    //instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplayer);

        broadcastReceiver = new NetworkChangeRecevier();
        registerNetworkBroadcastReceiver();

        initViews(); //"findViewById" is in that function

        //get the Firebase storage Reference
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

    }

    //if the user wants to upload the image
    public void upload(View view)
    {
        selectImageGallery();
    }

    //if the user wants to take picture for the image
    public void takeImageCamera(View view)
    {
        selectImageCamera();
    }

    //saving the player to firebase
    public void save(View view)
    {
        if (edTitName.getText().toString().isEmpty() || edFullName.getText().toString().isEmpty() || edSName.getText().toString().isEmpty()||
                edDay.getText().toString().isEmpty() || edMonth.getText().toString().isEmpty()||edYear.getText().toString().isEmpty()||
                edAge.getText().toString().isEmpty() ||
                edHeight.getText().toString().isEmpty()||
                edPos.getText().toString().isEmpty()||
                edCrTeam.getText().toString().isEmpty()||
                edNum.getText().toString().isEmpty()||
                edNltTeam.getText().toString().isEmpty()||
                edGoals.getText().toString().isEmpty()|| edAsissts.getText().toString().isEmpty()|| edNltGoals.getText().toString().isEmpty()||
                edFteams.getText().toString().isEmpty()||
                edInfo.getText().toString().isEmpty()||
                edWikiUrl.getText().toString().isEmpty()||
                edInstaUrl.getText().toString().isEmpty())
        {
            Toast.makeText(this, "some fields are empty", Toast.LENGTH_SHORT).show();
        }

        else
        {
            //Turns birth details into one sentence
            String birthday = Integer.parseInt( edDay.getText().toString() ) + " " + edMonth.getText().toString() + " " + Integer.parseInt( edYear.getText().toString() );

            //upload the image to firebase
            getTheImage();

            //instance for firebase realtime database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("players").push(); //make new value in the database

            //add the details to the value in the database
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
                    //add to the database the image location in the storage
                    "images/" + edSName.getText().toString());

            myRef.setValue(player);

            Toast.makeText(this, "Player saved", Toast.LENGTH_SHORT).show();

            //after it was saved, the user gets back to the user screen
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        }
    }

    //open the gallery to select an image
    public void selectImageGallery()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }

    public void selectImageCamera()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
            }
            else {
                Intent takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                try
                {
                    startActivityForResult(takePictureIntent, CAMERA_REQUEST);
                }
                catch (ActivityNotFoundException e)
                {
                    // display error state to the user
                    Toast.makeText(this, "Camera is not available", Toast.LENGTH_SHORT).show();

                }
            }
        }
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

                Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap1);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

       else if ( resultCode == RESULT_OK && requestCode == 69)
       {
           filePath = data.getData();

           Bitmap bitmap2 = (Bitmap) data.getExtras().get("data");
           imageView.setImageBitmap(bitmap2);

       }

    }




    //upload the image to firebase
    public void getTheImage()
    {
        if (filePath != null) {

            //open progress dialog
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageRef.child("images/" + edSName.getText().toString());
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                // if image uploaded successfully
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Dismiss dialog
                    progressDialog.dismiss();
                }
            })
                    //if the image could not upload
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            e.printStackTrace();
                            Toast.makeText(AddPlayer.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
                                }
                            });

        }

        else
            Toast.makeText(AddPlayer.this, "Saving requires picture", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
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
        imageView = findViewById(R.id.image);
    }


}