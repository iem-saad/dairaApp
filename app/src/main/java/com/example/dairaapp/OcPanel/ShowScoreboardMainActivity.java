package com.example.dairaapp.OcPanel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;
import com.example.dairaapp.Scoreboard;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowScoreboardMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ScoreboardAdapter evVenueAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_scoreboard_main);
        recyclerView = findViewById(R.id.scoreboardrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Scoreboard> options = new FirebaseRecyclerOptions.Builder<Scoreboard>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("scoreboards"), Scoreboard.class)
                .build();

        evVenueAdapter = new ScoreboardAdapter(options);
        evVenueAdapter.startListening();
        recyclerView.setAdapter(evVenueAdapter);
    }
}