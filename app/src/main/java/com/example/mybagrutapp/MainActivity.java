package com.example.mybagrutapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private EditText searchBar;
    private Button searchBtn;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBtn = findViewById(R.id.search_button);
        addBtn = findViewById(R.id.addBtn);
        searchBar = findViewById(R.id.search_bar);
        searchBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);

    }



    @Override
    public void onClick( View v )
    {

        if( searchBtn == v)
        {

            if(searchBar.getText().toString().isEmpty())
            {
                Toast.makeText(this, "You wrote nothing to look for", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent intent = new Intent(this,PlayerDisplay.class);
                intent.putExtra( "searchResults", searchBar.getText().toString() );
                startActivity(intent);

            }
        }

        if (addBtn == v)
        {
            Intent intent = new Intent(this,AddPlayer.class);
            startActivity(intent);
        }

    }





}