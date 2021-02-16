package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

// stack 생성
public class MainActivity extends AppCompatActivity {

    private Context mContext = MainActivity.this;
    private FloatingActionButton fabRoute;
    private static final String TAG = "MainActivity2";
    private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.main_layout);

        fabRoute = findViewById(R.id.fab_route);
        fabRoute.setOnClickListener(v -> {
//            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01011112222"));
            // 방법 1 : Bundle
            User user = new User();
            user.setId(1);
            user.setUsername("cos");
            user.setPassword("1234");
            final Bundle mBundle = new Bundle();
            mBundle.putSerializable("user", user);

            // 방법 2 : Serializable
            User user2 = new User();
            user2.setId(2);
            user2.setUsername("hello");
            user2.setPassword("1234");

            // 방법 3 : gson
            User user3 = new User();
            user3.setId(3);
            user3.setUsername("hyuk");
            user3.setPassword("1234");
            Gson gson = new Gson();
            String data = gson.toJson(user3);

            Intent intent = new Intent(mContext, SubActivity.class);
            intent.putExtra("mBundle", mBundle); // 1
            intent.putExtra("serial", user2); // 2
            intent.putExtra("data", data); // 3

//            intent.putExtra("username", "hyuk");
//            startActivity(intent);
            startActivityForResult(intent, 300);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: 실행됨");
        Log.d(TAG, "requestCode: " + requestCode);
        Log.d(TAG, "resultCode: " + resultCode);

        if (requestCode == 300) { // SubActivity 에서 돌아왔는 지 확인
            if (resultCode == RESULT_OK) { // 로직이 성공했는 지 확인
//                Toast.makeText(mContext, "인증 성공함 : " + data.getStringExtra("auth"), Toast.LENGTH_SHORT).show();
                Snackbar.make(mainLayout, "인증 성공함", BaseTransientBottomBar.LENGTH_LONG).show();
            } else { // 로직 실패
                Toast.makeText(mContext, "인증 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }
}