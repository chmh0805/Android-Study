package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // xml에 있는 그림이 메모리에 올라오는 과정(=인플레이트)

        // pgb_loading은 실행시에 메모리에 뜬다.
        // 실행 전 컴파일 시 툴이 pgb_loading을 R에 등록을 해준다.
        mProgressBar = findViewById(R.id.pgb_loading);

        download(); // 3초 = UI 스레드
    }

    private void download() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    // 다운로드 완료, 스프링 서버의 결과 (json)
                    mProgressBar.setVisibility(View.INVISIBLE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}