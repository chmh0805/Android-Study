package com.example.kakao_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.example.kakao_ex1.domain.Chat;
import com.example.kakao_ex1.fragments.ChatFragment;
import com.example.kakao_ex1.fragments.InfoFragment;
import com.example.kakao_ex1.fragments.MenuFragment;
import com.example.kakao_ex1.fragments.SharpFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private BottomNavigationView bottomNavigationView;
    public Toolbar mToolbar;
    public List<Chat> chats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initSetting();
    }
    
    private void init() {
        // Chat 컬렉션 생성 및 요소 추가
        chats = new ArrayList<>();
        chats.add(new Chat(1, R.drawable.profile1, "민혁", "안녕", "오전 11:25"));
        chats.add(new Chat(2, R.drawable.profile1, "혜선", "사진을 보냈습니다.", "오후 1:12"));
        chats.add(new Chat(3, R.drawable.profile1, "상길", "술먹자", "오후 3:25"));
        chats.add(new Chat(4, R.drawable.profile1, "김씨", "머함", "오후 4:17"));
        chats.add(new Chat(5, R.drawable.profile1, "정씨", "🍔", "오후 5:25"));
        chats.add(new Chat(6, R.drawable.profile1, "강씨", "😀😀", "오후 7:25"));
        chats.add(new Chat(7, R.drawable.profile1, "동현", "바쁨?", "오후 9:14"));

        mToolbar = findViewById(R.id.toolbar_info);
        Log.d(TAG, "mToolbar : " + mToolbar);

        // 하단 네비게이션 생성
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }
    
    private void initSetting() {
        // 초기화면 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoFragment(MainActivity.this)).commit();

        // 하단 네비게이션 Action 추가
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = new Fragment();
            switch (item.getItemId()) {
                case (R.id.bottom_info):
                    fragment = new InfoFragment(MainActivity.this);
                    break;
                case (R.id.bottom_chat):
                    fragment = new ChatFragment(MainActivity.this);
                    break;
                case (R.id.bottom_sharp):
                    fragment = new SharpFragment(MainActivity.this);
                    break;
                case (R.id.bottom_menu):
                    fragment = new MenuFragment(MainActivity.this);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        });
    }
}