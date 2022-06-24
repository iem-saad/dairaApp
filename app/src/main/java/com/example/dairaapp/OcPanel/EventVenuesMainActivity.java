package com.example.dairaapp.OcPanel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.EvVenue;
import com.example.dairaapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class EventVenuesMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EvVenueAdapter evVenueAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_venues_main);
        recyclerView = findViewById(R.id.sbevvenuesrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<EvVenue> options = new FirebaseRecyclerOptions.Builder<EvVenue>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("sb_ev_venue"), EvVenue.class)
                .build();

        evVenueAdapter = new EvVenueAdapter(options);
        evVenueAdapter.startListening();
        recyclerView.setAdapter(evVenueAdapter);
    }
}