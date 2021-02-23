package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private MediaPlayer mediaPlayer;
    private MusicService musicService;
    private TextView tvSinger, tvAlbum, tvTitle, tvTime;
    private ImageView btnPlayStop;
    private SeekBar seekBar;
    private boolean isPlaying = false;
    private Handler handler = new Handler();
    private Thread uiHandleThread;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.LocalBinder mb = (MusicService.LocalBinder) service;
            musicService = ((MusicService.LocalBinder) service).getService();
            mediaPlayer = musicService.getMediaPlayer();
            seekBar.setMax(mediaPlayer.getDuration());
        }
        
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent musicIntent = new Intent(getApplicationContext(), MusicService.class);
        bindService(musicIntent, connection, BIND_AUTO_CREATE);

        tvSinger = findViewById(R.id.tv_singer);
        tvAlbum = findViewById(R.id.tv_album);
        tvTitle = findViewById(R.id.tv_title);
        tvTime = findViewById(R.id.tv_time);
        btnPlayStop = findViewById(R.id.btn_play_stop);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        btnPlayStop.setImageResource(R.drawable.ic_pause);
                    }
                });
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
            }
        });

        btnPlayStop.setOnClickListener(v -> {
            isPlaying = !isPlaying;
            Log.d(TAG, "isPlaying : " + isPlaying);
            if (isPlaying == true) {
                btnPlayStop.setImageResource(R.drawable.ic_pause);
                mediaPlayer.start();
            } else {
                mediaPlayer.pause();
                btnPlayStop.setImageResource(R.drawable.ic_play);
            }

            uiHandleThread = new Thread(() -> {
                while (isPlaying == true) {
                    handler.post(() -> {
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                        tvTime.setText(getTimeString(mediaPlayer.getCurrentPosition()));
                    });

                    if (mediaPlayer.getCurrentPosition() >= mediaPlayer.getDuration()-50) {
                        mediaPlayer.stop();
                        mediaPlayer = MediaPlayer.create(musicService.getApplicationContext(), R.raw.sample1);
                        handler.post(() -> {
                            btnPlayStop.setImageResource(R.drawable.ic_play);
                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                            tvTime.setText(getTimeString(mediaPlayer.getCurrentPosition()));
                        });
                        isPlaying = false;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            uiHandleThread.start();
        });
    }

    private String getTimeString(long millis) {
        StringBuffer buffer = new StringBuffer();

        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);

        buffer
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return buffer.toString();
    }

}