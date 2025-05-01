package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_IMAGE_RES_ID = "imageResId";
    private static final String ARG_DESC = "desc";

    private String title;
    private int imageResId;
    private String description;

    public static NewsDetailFragment newInstance(News news) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, news.getTitle());
        args.putInt(ARG_IMAGE_RES_ID, news.getImageResId());
        args.putString(ARG_DESC, news.getDescription());
        fragment.setArguments(args);
        return fragment;
    }

    public NewsDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            imageResId = getArguments().getInt(ARG_IMAGE_RES_ID);
            description = getArguments().getString(ARG_DESC);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView imageView = view.findViewById(R.id.detailImage);
        TextView descView = view.findViewById(R.id.detailDesc);
        RecyclerView relatedRecyclerView = view.findViewById(R.id.relatedNewsRecyclerView);

        Glide.with(requireContext())
                .load(imageResId)
                .into(imageView);

        descView.setText(description);

        List<News> relatedNews = generateDummyRelatedNews();

        NewsAdapter adapter = new NewsAdapter(relatedNews, clicked -> {
            NewsDetailFragment newFragment = NewsDetailFragment.newInstance(clicked);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainLayout, newFragment)
                    .addToBackStack(null)
                    .commit();
        });

        relatedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        relatedRecyclerView.setAdapter(adapter);
    }

    private List<News> generateDummyRelatedNews() {
        List<News> list = new ArrayList<>();
        list.add(new News(title + " - Related 1", R.drawable.news1, "Detail for related news 1"));
        list.add(new News(title + " - Related 2", R.drawable.news2, "Detail for related news 2"));
        list.add(new News(title + " - Related 3", R.drawable.news3, "Detail for related news 3"));
        return list;
    }
}
