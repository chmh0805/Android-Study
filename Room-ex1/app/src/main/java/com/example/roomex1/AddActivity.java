package com.example.roomex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomex1.DB.AppDatabase;
import com.example.roomex1.model.User;

public class AddActivity extends AppCompatActivity {

    private AppDatabase db = null;
    private EditText etUsername, etAge, etRegion;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etUsername = findViewById(R.id.et_username);
        etAge = findViewById(R.id.et_age);
        etRegion = findViewById(R.id.et_region);
        btnAdd = findViewById(R.id.btn_add);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userDB").build();

        btnAdd.setOnClickListener(v -> {
            if (!(etUsername.getText().toString().equals("")) && (!(etAge.getText().toString().equals(""))) && (!(etRegion.getText().toString().equals("")))) {
                new Thread(() -> {
                    User newUser = new User();
                    newUser.setUsername(etUsername.getText().toString());
                    newUser.setRegion(etRegion.getText().toString());
                    newUser.setAge(Integer.parseInt(etAge.getText().toString()));
                    db.userDao().insertOne(newUser);

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }).start();
            } else {
                Toast.makeText(this, "값을 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}