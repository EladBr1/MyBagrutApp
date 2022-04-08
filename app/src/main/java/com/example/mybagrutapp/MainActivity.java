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


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private EditText searchBar;
    private Button listBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBar = findViewById(R.id.search_bar);
        listBtn = findViewById(R.id.listBtn);
        listBtn.setOnClickListener(this);

    }

    @Override
    public void onClick( View v )
    {



        if (listBtn == v)
        {
            Intent intent = new Intent(this, SortActivity.class);
            startActivity(intent);
        }

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
}