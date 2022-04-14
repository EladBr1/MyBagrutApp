package com.example.mybagrutapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity
{

    private EditText searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.search_bar);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if (id == R.id.home)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.login)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.contact)
        {
            //Intent intent = new Intent(this, ContactUs.class);
            //startActivity(intent);
        }

        else if (id == R.id.list)
        {
            Intent intent = new Intent(this, SortActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.exit)
        {
            //exiting the app
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        return true;

    }

    public void search(View view)
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

    public void sortActivity(View view)
    {

        Intent intent = new Intent(this,SortActivity.class);
        startActivity(intent);

    }

}