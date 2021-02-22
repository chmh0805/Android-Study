package com.example.instaex01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.instaex01.adapter.MyFragmentPagerAdapter;
import com.example.instaex01.domain.User;
import com.example.instaex01.fragments.AddFragment;
import com.example.instaex01.fragments.FavorFragment;
import com.example.instaex01.fragments.HomeFragment;
import com.example.instaex01.fragments.ProfileFragment;
import com.example.instaex01.fragments.SearchFragment;
import com.example.instaex01.fragments.profile.Frag1;
import com.example.instaex01.fragments.profile.Frag2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initSetting();
        login();
    }

    private void init() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private void initSetting() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case (R.id.bottom_item_home):
                    fragment = new HomeFragment();
                    break;
                case (R.id.bottom_item_search):
                    fragment = new SearchFragment();
                    break;
                case (R.id.bottom_item_add):
                    fragment = new AddFragment();
                    break;
                case (R.id.bottom_item_favor):
                    fragment = new FavorFragment();
                    break;
                case (R.id.bottom_item_profile):
                    fragment = new ProfileFragment(MainActivity.this);
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

            return true;
        });
    }

    private void login() {
        user = User.builder()
                .id(1)
                .username("h_ggob")
                .realName("정민혁")
                .userBio("Bio from User. Hello Everyone !")
                .postNum(20)
                .followerNum(100)
                .followingNum(80)
                .build();

        String loginUserName = getString(R.string.login_username);
        loginUserName = user.getUsername();
    }
}