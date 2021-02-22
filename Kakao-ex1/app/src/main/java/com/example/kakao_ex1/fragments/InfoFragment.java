package com.example.kakao_ex1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.kakao_ex1.MainActivity;
import com.example.kakao_ex1.R;

public class InfoFragment extends Fragment {

    private final MainActivity mainActivity;
    private final Toolbar mToolbar;

    public InfoFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mToolbar = mainActivity.mToolbar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initToolbar();

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        return view;
    }

    private void initToolbar() {
        mToolbar.setTitle(R.string.toolbar_title_info);
        Menu menu = mToolbar.getMenu();
        menu.findItem(R.id.toolbar_item1).setIcon(R.drawable.ic_search);
        menu.findItem(R.id.toolbar_item2).setIcon(R.drawable.ic_person_add);
        menu.findItem(R.id.toolbar_item3).setIcon(R.drawable.ic_music);
        menu.findItem(R.id.toolbar_item4).setIcon(R.drawable.ic_settings);
    }
}
