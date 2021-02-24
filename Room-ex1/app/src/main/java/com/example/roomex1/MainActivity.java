package com.example.roomex1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.roomex1.DB.AppDatabase;
import com.example.roomex1.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity2";
    public List<User> users;
    private MyAdapter adapter;
    private RecyclerView rvUsers;
    private Button btnStartAddActivity;
    private AppDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initAdapter();
        initEvent();
    }

    private void init() {
        rvUsers = findViewById(R.id.rv_users);
        btnStartAddActivity = findViewById(R.id.btn_startAddActivity);
        users = new ArrayList<>();

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB").build();

        new Thread(() -> {
            users = db.userDao().findAll();
            adapter.setUsers();
        }).start();
    }

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(this);
        rvUsers.setLayoutManager(linearLayoutManager);
        rvUsers.setAdapter(adapter);
    }

    private void initEvent() {
        btnStartAddActivity.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, 1 << 2) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                new Thread(() -> {
                    db.userDao().delete(users.get(viewHolder.getAdapterPosition()));
                    runOnUiThread(() -> {
                        adapter.deleteUser(viewHolder.getAdapterPosition());
                    });
                }).start();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvUsers);
    }
}