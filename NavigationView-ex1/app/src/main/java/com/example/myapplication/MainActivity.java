package com.example.myapplication;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

// stack 생성
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private GridView gvMain;
    private List<Movie> movies;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 전체 인플레이트
        init();
        adapter = new ItemAdapter(movies);
        gvMain.setAdapter(adapter);

    }

    private void init() {
        gvMain = findViewById(R.id.gv_main);
        movies = new ArrayList<>();
        download();
    }

    private void download() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                movies.add(new Movie(1, "써니", R.drawable.mov01));
                movies.add(new Movie(2, "완득이", R.drawable.mov02));
                movies.add(new Movie(3, "괴물", R.drawable.mov03));
                movies.add(new Movie(4, "라디오 스타", R.drawable.mov04));
                movies.add(new Movie(5, "비열한 거리", R.drawable.mov05));
                movies.add(new Movie(6, "왕의 남자", R.drawable.mov06));
                movies.add(new Movie(7, "아일랜드", R.drawable.mov07));
                movies.add(new Movie(8, "웰컴 투 동막골", R.drawable.mov08));
                movies.add(new Movie(9, "헬보이", R.drawable.mov09));
                movies.add(new Movie(10, "백 투 더 퓨처", R.drawable.mov10));
                movies.add(new Movie(11, "여인의 향기", R.drawable.mov11));
                movies.add(new Movie(12, "쥬라기 공원", R.drawable.mov12));
                // main스레드(=UI스레드)가 adapter.notifyDataSetChanged();를 호출하게 해야 함.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}