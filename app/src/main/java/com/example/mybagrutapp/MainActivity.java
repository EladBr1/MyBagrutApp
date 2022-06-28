package com.example.mybagrutapp;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends OptionsMenuActivity
{

    private EditText searchBar; //the search bar
    private BroadcastReceiver broadcastReceiver;// broadcast receiver

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the class
        broadcastReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastReceiver();

        searchBar = findViewById(R.id.search_bar);

    }

    public void search(View view)
    {
        if(searchBar.getText().toString().isEmpty()) // if the search bar is empty...
            //make toast:
            Toast.makeText(this, "You wrote nothing to look for", Toast.LENGTH_SHORT).show();

        else
        {
            Intent intent = new Intent(this,PlayerDisplay.class);
            //move the text from the search bar to the next screen
            intent.putExtra( "searchResults", searchBar.getText().toString() );
            //open the player screen
            startActivity(intent);

        }
    }

    public void sortActivity(View view)
    {
        //open the sorting screen
        Intent intent = new Intent(this,SortActivity.class);
        startActivity(intent);

    }

    //register th broadcast the internet according the broadcast receiver
    protected void registerNetworkBroadcastReceiver()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


}