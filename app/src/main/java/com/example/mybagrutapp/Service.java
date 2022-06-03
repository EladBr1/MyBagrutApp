package com.example.mybagrutapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class Service extends android.app.Service
{

    private MediaPlayer player;//the music file

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //start the service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player =MediaPlayer.create(this, R.raw.championsleague_anthem);//the music file
        player.setLooping(true);
        player.start();
        return START_STICKY;
    }

    //stop the music
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }


}
