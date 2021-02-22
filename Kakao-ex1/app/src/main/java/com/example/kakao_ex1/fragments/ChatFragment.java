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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kakao_ex1.MainActivity;
import com.example.kakao_ex1.R;
import com.example.kakao_ex1.adapter.ChatAdapter;
import com.example.kakao_ex1.domain.Chat;

import java.util.List;

public class ChatFragment extends Fragment {

    private RecyclerView rvChatList;
    private ChatAdapter chatAdapter;
    private final MainActivity mainActivity;
    private final List<Chat> chats;
    private Toolbar mToolbar;

    public ChatFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.chats = mainActivity.chats;
        this.mToolbar = mainActivity.mToolbar;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initToolbar();

        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        rvChatList = view.findViewById(R.id.rv_chat_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity, RecyclerView.VERTICAL, false);

        chatAdapter = new ChatAdapter(chats);
        rvChatList.setLayoutManager(linearLayoutManager);
        rvChatList.setAdapter(chatAdapter);

        return view;
    }

    private void initToolbar() {
        mToolbar.setTitle(R.string.toolbar_title_chat);
        Menu menu = mToolbar.getMenu();
        menu.findItem(R.id.toolbar_item1).setIcon(R.drawable.ic_search);
        menu.findItem(R.id.toolbar_item2).setIcon(R.drawable.ic_chat_add);
        menu.findItem(R.id.toolbar_item3).setIcon(R.drawable.ic_music);
        menu.findItem(R.id.toolbar_item4).setIcon(R.drawable.ic_settings);
    }
}
