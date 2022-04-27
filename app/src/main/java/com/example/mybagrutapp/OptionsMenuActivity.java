package com.example.mybagrutapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsMenuActivity extends AppCompatActivity
{

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
            Intent intent = new Intent(this, ContactUsActivity.class);
            startActivity(intent);
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

}

