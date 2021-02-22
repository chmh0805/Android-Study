package com.example.movie01;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private static final String TAG = "MovieAdapter";
    private final List<Integer> movies;

    public MovieAdapter(List<Integer> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setItem(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivItem;
        private float savedRating;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivItem = itemView.findViewById(R.id.iv_item);

            ivItem.setOnClickListener(v -> {
                View dialog = v.inflate(v.getContext(), R.layout.dialog_item, null);
                ImageView ivPopup = dialog.findViewById(R.id.iv_popup);
                int position = getAdapterPosition();
                ivPopup.setImageResource(movies.get(position));

                AlertDialog.Builder dig = new AlertDialog.Builder(v.getContext());

                SharedPreferences preferences = v.getContext().getSharedPreferences("pref", MainActivity.MODE_PRIVATE);

                RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
                TextView tvRating = dialog.findViewById(R.id.tv_rating);
                ratingBar.setRating(preferences.getFloat("rating"+position, 0));
                tvRating.setText(ratingBar.getRating()+"");

                ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
                    tvRating.setText(rating+"");
                    savedRating = rating;
                });

                dig.setTitle("큰 포스터");
                dig.setIcon(R.drawable.movie_icon);
                dig.setView(dialog);
                dig.setNegativeButton("닫기", null);
                dig.setPositiveButton("확인", ((dialog1, which) -> {
                    // MODE_PRIVATE : MainActivity에서만 사용할 수 있게 함 !!
                    SharedPreferences pref = v.getContext().getSharedPreferences("pref", MainActivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putFloat("rating"+position, savedRating);
                    editor.commit();
                }));
                dig.show();
            });
        }

        public void setItem(int resource) {
            ivItem.setImageResource(resource);
        }

    }

}
