package com.example.dairaapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class DisplayParticipants extends AppCompatActivity {

    RecyclerView recyclerView;
    ParticipantAdapter participantAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_participants);
        recyclerView = findViewById(R.id.participantsrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Participant> options = new FirebaseRecyclerOptions.Builder<Participant>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("participants"), Participant.class)
                .build();
        Log.d("TAG", new StringBuilder().append("INDEX").append(options.toString()).toString());
        participantAdapter = new ParticipantAdapter(options);
        participantAdapter.startListening();
        recyclerView.setAdapter(participantAdapter);
    }
}