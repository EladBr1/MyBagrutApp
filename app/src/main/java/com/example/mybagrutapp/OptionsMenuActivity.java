package com.example.mybagrutapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsMenuActivity extends AppCompatActivity
{
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        //shared preferences for music icon
        SharedPreferences sp;
        MenuItem it = menu.findItem(R.id.service);
        sp = getSharedPreferences("sound", 0);
        if(sp.getBoolean("music", true)) {
            it.setIcon(R.drawable.music_unmute);
        } else {
            it.setIcon(R.drawable.music_mute);
        }

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();


        if (id == R.id.service)
        {
            //shared preferences for music icon, when it's muted- there is mute icon, and if it is on- there is unmute icon
            SharedPreferences sp;
            sp = getSharedPreferences("sound", 0);
            SharedPreferences.Editor editor = sp.edit();
            if (sp.getBoolean("music", true)) {
                item.setIcon(R.drawable.music_mute);
                stopService(new Intent(this, Service.class));
                editor.putBoolean("music", false);
            } else {
                item.setIcon(R.drawable.music_unmute);
                startService(new Intent(this, Service.class));
                editor.putBoolean("music", true);
            }
            editor.apply();
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

