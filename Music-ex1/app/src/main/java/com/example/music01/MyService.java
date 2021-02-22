package com.example.music01;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";
    private MediaPlayer mediaPlayer;

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        String musicName = intent.getStringExtra("musicName");
        Log.d(TAG, "onStartCommand: musicName : " + musicName);

        if (musicName.equals("sample1")) {
            mediaPlayer = MediaPlayer.create(MyService.this, R.raw.sample1);
        } else {
            mediaPlayer = MediaPlayer.create(MyService.this, R.raw.sample2);
        }

        mediaPlayer.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}