package com.example.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final List<News> newsList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(News news);
    }

    public NewsAdapter(List<News> newsList, OnItemClickListener listener) {
        this.newsList = newsList;
        this.listener = listener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.newsTitle.setText(news.getTitle());

        Glide.with(holder.itemView.getContext())
                .load(news.getImageResId())
                .into(holder.newsImage);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(news));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTitle;

        public NewsViewHolder(View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.newsImage);
            newsTitle = itemView.findViewById(R.id.newsTitle);
        }
    }
}
