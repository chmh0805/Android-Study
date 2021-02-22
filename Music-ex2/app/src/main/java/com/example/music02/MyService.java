package com.example.music02;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    private static final String TAG = "MyService";
    private MediaPlayer mediaPlayer;
    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }

        public MediaPlayer getMediaPlayer() {
            return mediaPlayer;
        }
    }

    public MyService() {
        LocalBinder binder = (LocalBinder) mBinder;
        MyService m = binder.getService();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.sample2);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }


}