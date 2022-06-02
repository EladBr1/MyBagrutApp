package com.example.mybagrutapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsMenuActivity extends AppCompatActivity
{
    boolean musicIsPlaying;
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        musicIsPlaying = true;
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();


        if (id == R.id.service)
        {

            if (musicIsPlaying)
            {
                stopService(new Intent(this, Service.class));//stop the music
                item.setIcon(R.drawable.music_mute);//set the mute icon
                musicIsPlaying = false;
            }
            else
            {
                startService(new Intent(this, Service.class));//play the music
                item.setIcon(R.drawable.music_unmute);//set the unmute icon
                musicIsPlaying = true;
            }
        }

        if (id == R.id.home)
        {
            Intent intent = new Intent(this, MainActivity.class);
            //go to main screen
            startActivity(intent);
        }

        else if (id == R.id.login)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            //go to login screen
            startActivity(intent);
        }

        else if (id == R.id.contact)
        {
            //go to contactUs screen
            Intent intent = new Intent(this, ContactUsActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.list)
        {
            Intent intent = new Intent(this, SortActivity.class);
            //
            startActivity(intent);
        }

        else if (id == R.id.exit)
        {
            stopService(new Intent(this, Service.class));
            //exiting the app
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        return true;

    }

}

