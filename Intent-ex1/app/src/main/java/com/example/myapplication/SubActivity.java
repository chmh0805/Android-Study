package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class SubActivity extends AppCompatActivity {

    FloatingActionButton fabPop;
    private static final String TAG = "SubActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent mainIntent = getIntent(); // intent를 받기
        // 방법 1
        Bundle mBundle = mainIntent.getBundleExtra("mBundle");
        User user = (User) mBundle.getSerializable("user");
        Log.d(TAG, "user: " + user);

        // 방법 2
        User user2 = (User) mainIntent.getSerializableExtra("serial");
        Log.d(TAG, "user2: " + user2);

        // 방법 3
        Gson gson = new Gson();
        String data = mainIntent.getStringExtra("data");
        User user3 = gson.fromJson(data, User.class);
        Log.d(TAG, "user3: " + user3);

        fabPop = findViewById(R.id.fab_pop);

        fabPop.setOnClickListener(v -> {
            Intent newIntent = new Intent();
            newIntent.putExtra("auth", "ok");

            setResult(RESULT_OK, newIntent);
            finish();
        });
    }
}