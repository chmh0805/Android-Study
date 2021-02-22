package com.example.kakao_ex1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.kakao_ex1.MainActivity;
import com.example.kakao_ex1.R;

public class SharpFragment extends Fragment {

    private final MainActivity mainActivity;
    private final Toolbar mToolbar;

    public SharpFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        mToolbar = mainActivity.mToolbar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sharp, container, false);
        return view;
    }
}
