package com.example.dairaapp.ParticipantPanel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;
import com.example.dairaapp.Scoreboard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParticipantScoreMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SimpleScoreAdapter scoreAdapter;
    ArrayList<String> reg_events;
    ArrayList<Scoreboard> scoreboards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_scoreboard_main);
        recyclerView = findViewById(R.id.scoreboardrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reg_events = new ArrayList<String>();
        scoreboards = new ArrayList<Scoreboard>();

//        FirebaseRecyclerOptions<Scoreboard> options = new FirebaseRecyclerOptions.Builder<Scoreboard>()
//                .setQuery(FirebaseDatabase.getInstance().getReference().child("scoreboards"), Scoreboard.class)
//                .build();
//
//        evVenueAdapter = new ScoreboardAdapter(options);
//        evVenueAdapter.startListening();
//        recyclerView.setAdapter(evVenueAdapter);

        FirebaseDatabase.getInstance().getReference().child("reg_participants " +  FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    reg_events.add(child.child("sb_id").getValue().toString());
                }
                FirebaseDatabase.getInstance().getReference().child("scoreboards").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            Scoreboard temp;
                            if (reg_events.contains(child.child("event_id").getValue().toString())){
                                temp = new Scoreboard(child.child("event_id").getValue().toString(), child.child("event_name").getValue().toString(), child.child("oc_id").getValue().toString(), child.child("player1").getValue().toString() , child.child("player2").getValue().toString() , child.child("player3").getValue().toString());
                                scoreboards.add(temp);
                            }
                        }
                        scoreAdapter = new SimpleScoreAdapter(scoreboards);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(scoreAdapter);
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
    }
}