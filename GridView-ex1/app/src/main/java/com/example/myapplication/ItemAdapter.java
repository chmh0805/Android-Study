package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

// 1. 컬렉션 정보
public class ItemAdapter extends BaseAdapter {

    private final List<Movie> movies;
    private static final String TAG = "ItemAdapter";

    public ItemAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    // 전체 크기를 확인하기 위해서 필요
    @Override
    public int getCount() {
        return movies.size();
    }

    // 클릭하거나 어떤 이벤트 발생시에 컬렉션 정보를 확인하기 위해 필요
    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d(TAG, "getView: " + position);

        // 1. 부모 컨텍스트 가져오기 (Context <- MainActivity)
        Context mContext = parent.getContext();

        // 2. 인플레이터 객체 생성 완료 (xml을 자바 객체로 만들게 해주는 도구)
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_item, parent, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(movies.get(position).getPic());

        view.setOnClickListener(v -> {
//            Toast.makeText(mContext, "클릭됨"+position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("movie", movies.get(position));
            mContext.startActivity(intent);
        });

        view.setOnLongClickListener(v -> {
            Toast.makeText(mContext, movies.get(position).getTitle()+" 삭제됨", Toast.LENGTH_SHORT).show();
            movies.remove(position);
            this.notifyDataSetChanged();
            return true;
        });

        return view;
    }
}
