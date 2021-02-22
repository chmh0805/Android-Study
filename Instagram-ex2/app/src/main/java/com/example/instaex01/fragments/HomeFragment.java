package com.example.instaex01.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.instaex01.R;
import com.example.instaex01.adapter.HomeListViewAdapter;
import com.example.instaex01.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View view;
    private HomeListViewAdapter homeListViewAdapter;
    private ListView lvHome;
    private SwipeRefreshLayout homeSwipeRefresh;
    private List<Post> posts;
    private Integer num = 21;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        init();

        return view;
    }

    private void init() {
        lvHome = view.findViewById(R.id.lv_home);
        homeSwipeRefresh = view.findViewById(R.id.home_swipe_refresh);
        homeSwipeRefresh.setColorSchemeColors(Color.rgb(20, 126, 204));

        homeSwipeRefresh.setOnRefreshListener(() -> {
            homeListViewAdapter.changeZero(num);
            num++;

            homeSwipeRefresh.setRefreshing(false);
        });

        posts = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Post post = new Post(i, "username"+i, "content"+i);
            posts.add(post);
        }

        homeListViewAdapter = new HomeListViewAdapter(posts);
        lvHome.setAdapter(homeListViewAdapter);
    }
}
