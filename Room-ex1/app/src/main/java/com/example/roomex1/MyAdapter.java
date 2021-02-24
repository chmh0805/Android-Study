package com.example.roomex1;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomex1.databinding.UserItemBinding;
import com.example.roomex1.model.User;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<User> users;
    private final MainActivity mActivity;

    public MyAdapter(MainActivity mainActivity) {
        this.mActivity = mainActivity;
        this.users = mActivity.users;
    }

    public void setUsers() {
        this.users = mActivity.users;
        notifyDataSetChanged();
    }

    public void deleteUser(int position) {
        mActivity.users.remove(position);
        setUsers();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemBinding userItemBinding = UserItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(userItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.userItemBinding.setUser(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private UserItemBinding userItemBinding;

        public MyViewHolder(@NonNull UserItemBinding userItemBinding) {
            super(userItemBinding.getRoot());
            this.userItemBinding = userItemBinding;
        }
    }
}
