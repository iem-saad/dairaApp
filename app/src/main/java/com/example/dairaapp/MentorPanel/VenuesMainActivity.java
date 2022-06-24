package com.example.dairaapp.MentorPanel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;
import com.example.dairaapp.Venue;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class VenuesMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    VenuesAdapter usersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues_main);
        recyclerView = findViewById(R.id.venuesrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Venue> options = new FirebaseRecyclerOptions.Builder<Venue>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("venues"), Venue.class)
                .build();

        usersAdapter = new VenuesAdapter(options);
        usersAdapter.startListening();
        recyclerView.setAdapter(usersAdapter);
    }
}