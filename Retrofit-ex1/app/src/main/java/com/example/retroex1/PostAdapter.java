package com.example.retroex1;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    public void addItem(Post post) {
        posts.add(post);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        posts.remove(position);
        notifyDataSetChanged();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setItem(posts.get(position));
        if (position % 2 == 1) {
            holder.getView().setBackgroundColor(Color.rgb(244, 255, 232));
        } else {
            holder.getView().setBackgroundColor(Color.rgb(232, 255, 244));
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId, tvTitle, tvText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvText = itemView.findViewById(R.id.tv_text);
        }

        public void setItem(Post post) {
            tvId.setText(post.getId().toString());
            tvTitle.setText(post.getTitle());
            tvText.setText(post.getText());
        }

        public View getView() {
            return this.itemView;
        }
    }
}
