package com.example.mybagrutapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends OptionsMenuActivity
{

    private EditText searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.search_bar);

        //when app is open the service music will be activated
        startService(new Intent(this, Service.class));

    }

    public void search(View view)
    {
        if(searchBar.getText().toString().isEmpty())
            Toast.makeText(this, "You wrote nothing to look for", Toast.LENGTH_SHORT).show();

        else
        {
            Intent intent = new Intent(this,PlayerDisplay.class);
            intent.putExtra( "searchResults", searchBar.getText().toString() );
            startActivity(intent);
        }
    }

    public void sortActivity(View view)
    {

        Intent intent = new Intent(this,SortActivity.class);
        startActivity(intent);

    }





}