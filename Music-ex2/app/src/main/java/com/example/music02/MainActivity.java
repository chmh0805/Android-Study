package com.example.music02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import com.example.music02.R;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private TextInputEditText tiMusic;
    private Button btnStart, btnPause, btnStop;
    private MediaPlayer mediaPlayer;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: 서비스 연결됨");
            mediaPlayer = ((MyService.LocalBinder) service).getMediaPlayer();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: 종료됨");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 서비스 실행 = Service의 onCreate와 onBind가 실행 -> connection 객체의 onServiceConnedcted가 콜백됨.
        Intent musicIntent = new Intent(MainActivity.this, MyService.class);
        bindService(musicIntent, connection, BIND_AUTO_CREATE);

        tiMusic = findViewById(R.id.ti_music);
        btnStart = findViewById(R.id.btn_start);
        btnPause = findViewById(R.id.btn_pause);
        btnStop = findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(v -> {
            mediaPlayer.start();
        });

        btnPause.setOnClickListener(v -> {
            mediaPlayer.pause();
        });

        btnStop.setOnClickListener(v -> {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        });
    }

}