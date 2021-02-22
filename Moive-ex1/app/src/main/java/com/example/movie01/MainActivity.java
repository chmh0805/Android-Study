package com.example.movie01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMovie;
    private List<Integer> movies;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = new ArrayList<>();
        movies.add(R.drawable.mov01);
        movies.add(R.drawable.mov02);
        movies.add(R.drawable.mov03);
        movies.add(R.drawable.mov04);
        movies.add(R.drawable.mov05);
        movies.add(R.drawable.mov06);
        movies.add(R.drawable.mov07);
        movies.add(R.drawable.mov08);
        movies.add(R.drawable.mov09);
        movies.add(R.drawable.mov10);
        movies.add(R.drawable.mov11);
        movies.add(R.drawable.mov12);

        rvMovie = findViewById(R.id.rv_movie);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        movieAdapter = new MovieAdapter(movies);
        rvMovie.setLayoutManager(gridLayoutManager);
        rvMovie.setAdapter(movieAdapter);
    }
}