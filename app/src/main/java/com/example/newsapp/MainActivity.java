package com.example.newsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView topStoriesRecyclerView, newsRecyclerView;
    List<News> topStoriesList = new ArrayList<>();
    List<News> latestNewsList = new ArrayList<>();
    NewsAdapter topStoriesAdapter, latestNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topStoriesRecyclerView = findViewById(R.id.topStoriesRecyclerView);
        newsRecyclerView = findViewById(R.id.newsRecyclerView);

        loadDummyData();

        // Set up adapters
        topStoriesAdapter = new NewsAdapter(topStoriesList, this::openDetailFragment);
        latestNewsAdapter = new NewsAdapter(latestNewsList, this::openDetailFragment);

        // Set up horizontal RecyclerViews
        topStoriesRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        topStoriesRecyclerView.setAdapter(topStoriesAdapter);

        newsRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        newsRecyclerView.setAdapter(latestNewsAdapter);
    }

    private void loadDummyData() {
        topStoriesList.add(new News("Top Story 1", R.drawable.news1, "Description of Top Story 1"));
        topStoriesList.add(new News("Top Story 2", R.drawable.news2, "Description of Top Story 2"));
        topStoriesList.add(new News("Top Story 3", R.drawable.news3, "Description of Top Story 3"));

        latestNewsList.add(new News("News 1", R.drawable.news2, "Description of News 1"));
        latestNewsList.add(new News("News 2", R.drawable.news3, "Description of News 2"));
        latestNewsList.add(new News("News 3", R.drawable.news1, "Description of News 3"));
    }


    private void openDetailFragment(News news) {
        NewsDetailFragment fragment = NewsDetailFragment.newInstance(news);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
