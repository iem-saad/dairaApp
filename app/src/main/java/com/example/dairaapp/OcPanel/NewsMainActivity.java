package com.example.dairaapp.OcPanel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.EventNews;
import com.example.dairaapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class NewsMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);
        recyclerView = findViewById(R.id.newsrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<EventNews> options = new FirebaseRecyclerOptions.Builder<EventNews>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("ev_news"), EventNews.class)
                .build();

        newsAdapter = new NewsAdapter(options);
        newsAdapter.startListening();
        recyclerView.setAdapter(newsAdapter);

    }
}