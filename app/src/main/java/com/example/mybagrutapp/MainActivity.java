package com.example.mybagrutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    EditText searchBar;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBtn = (Button)findViewById(R.id.search_button);
        searchBar = (EditText)findViewById(R.id.search_bar);

    }

    public void onClick( View v )
    {

        if( searchBtn == v)
        {

            Intent intent = new Intent( this, PlayerDetails.class );
            startActivity(intent);

        }

    }

}