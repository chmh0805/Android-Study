package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetailTitle;
    private ImageView ivDetail;
    private FloatingActionButton fabHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailTitle = findViewById(R.id.tv_detail_title);
        ivDetail = findViewById(R.id.iv_detail);
        fabHome = findViewById(R.id.fab_home);

        Intent mainIntent = getIntent();

        Movie movie = (Movie) mainIntent.getSerializableExtra("movie");

        tvDetailTitle.setText(movie.getTitle());
        ivDetail.setImageResource(movie.getPic());

        fabHome.setOnClickListener(v -> {
            finish();
        });
    }
}