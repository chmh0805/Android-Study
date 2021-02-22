package com.example.instaex01.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.instaex01.MainActivity;
import com.example.instaex01.R;
import com.example.instaex01.adapter.MyFragmentPagerAdapter;
import com.example.instaex01.domain.User;
import com.example.instaex01.fragments.profile.Frag1;
import com.example.instaex01.fragments.profile.Frag2;
import com.google.android.material.tabs.TabLayout;

public class ProfileFragment extends Fragment {

    private TextView tvPostsNum;
    private TextView tvFollowersNum;
    private TextView tvFollowingNum;
    private TextView tvUserInfoName;
    private TextView tvUserInfoBio;
    private TabLayout tabs;
    private ViewPager vpProfile;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private final MainActivity mainActivity;
    private final User user;
    private View view;

    public ProfileFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.user = mainActivity.user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        init();
        initToLoginUser();
        mPager();

        return view;
    }

    private void init() {
        tabs = view.findViewById(R.id.tabs);
        vpProfile = view.findViewById(R.id.vp_profile);
        tvPostsNum = view.findViewById(R.id.tv_posts_num);
        tvFollowersNum = view.findViewById(R.id.tv_followers_num);
        tvFollowingNum = view.findViewById(R.id.tv_following_num);
        tvUserInfoName = view.findViewById(R.id.tv_user_info_name);
        tvUserInfoBio = view.findViewById(R.id.tv_user_info_bio);
    }

    private void initToLoginUser() {
        tvPostsNum.setText(user.getPostNum().toString());
        tvFollowersNum.setText(user.getFollowerNum().toString());
        tvFollowingNum.setText(user.getFollowingNum().toString());
        tvUserInfoName.setText(user.getRealName());
        tvUserInfoBio.setText(user.getUserBio());
    }

    private void mPager() {
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(mainActivity.getSupportFragmentManager(), 1);

        myFragmentPagerAdapter.addFragment(new Frag1());
        myFragmentPagerAdapter.addFragment(new Frag2());

        vpProfile.setAdapter(myFragmentPagerAdapter);
        tabs.setupWithViewPager(vpProfile);

        tabs.getTabAt(0).setIcon(R.drawable.ic_border_clear);
        tabs.getTabAt(1).setIcon(R.drawable.ic_frames);
    }
}
