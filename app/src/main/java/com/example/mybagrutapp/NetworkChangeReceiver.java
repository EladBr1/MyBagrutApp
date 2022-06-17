package com.example.mybagrutapp;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        //if the connection lost
        if (!isOnline(context))
        {
            Dialog internetDialog = new Dialog(context); //error dialog
            internetDialog.setContentView(R.layout.internet_dialog);
            Button tryAgainBtn = internetDialog.findViewById(R.id.btnTryAgain);
            internetDialog.setCancelable(false);
            internetDialog.show();

            tryAgainBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (isOnline(context))//if connection is back
                    {
                        internetDialog.dismiss();
                        Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        internetDialog.dismiss();
                        onReceive(context, intent);//showing the dialog again
                    }

                }
            });
        }

    }

    //check the connection
    public boolean isOnline(Context context)
    {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }


}