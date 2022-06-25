package com.example.dairaapp.ParticipantPanel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.EventNews;
import com.example.dairaapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowPNewsMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SimpleNewsAdapter newsAdapter;
    ArrayList<String> reg_events;
    ArrayList<EventNews> news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_main);
        recyclerView = findViewById(R.id.newsrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reg_events = new ArrayList<String>();
        news = new ArrayList<EventNews>();

        FirebaseDatabase.getInstance().getReference().child("reg_participants " +  FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    reg_events.add(child.child("sb_id").getValue().toString());
                }
                FirebaseDatabase.getInstance().getReference().child("ev_news").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            EventNews temp;
                            if (reg_events.contains(child.child("sb_ev_id").getValue().toString())){
                                temp = new EventNews(child.child("adder_username").getValue().toString(), child.child("msg").getValue().toString(), child.child("sb_ev_id").getValue().toString(), child.child("sb_ev_name").getValue().toString());
                                news.add(temp);
                            }
                        }
                        newsAdapter = new SimpleNewsAdapter(news);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(newsAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        FirebaseRecyclerOptions<EventNews> options = new FirebaseRecyclerOptions.Builder<EventNews>()
//                .setQuery(FirebaseDatabase.getInstance().getReference().child("ev_news").orderByChild("sb_ev_id").equalTo("-N5EncuxMBAGEwNn4yMf -N5EnwzAE-mFzWzmmDoQ"), EventNews.class)
//                .build();


    }
}