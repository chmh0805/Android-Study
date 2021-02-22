package com.example.instaex01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.instaex01.R;
import com.example.instaex01.domain.Post;

import java.util.List;

public class HomeListViewAdapter extends BaseAdapter {

    private final List<Post> posts;

    public HomeListViewAdapter(List<Post> posts) {
        this.posts = posts;
    }

    public void changeZero(int num) {
        posts.get(0).setUsername("username"+num);
        posts.get(0).setContent("content"+num);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.home_post_item, parent, false);
        }

        TextView tvPostItemUsername = convertView.findViewById(R.id.tv_post_item_username);
        TextView tvPostItemContent = convertView.findViewById(R.id.tv_post_item_content);

        tvPostItemUsername.setText(posts.get(position).getUsername());
        tvPostItemContent.setText(posts.get(position).getContent());

        return convertView;
    }
}
