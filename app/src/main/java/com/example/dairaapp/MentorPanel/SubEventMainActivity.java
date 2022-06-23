package com.example.dairaapp.MentorPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class SubEventMainActivity extends AppCompatActivity {

    FloatingActionButton add_rec;
    RecyclerView recyclerView;
    SubEventAdapter eventsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_event_main);
        add_rec = findViewById(R.id.event_add_float_btn);
        recyclerView = findViewById(R.id.subeventsrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<SubEvent> options = new FirebaseRecyclerOptions.Builder<SubEvent>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("subevents"), SubEvent.class)
                .build();
        eventsAdapter = new SubEventAdapter(options);
        eventsAdapter.startListening();
        recyclerView.setAdapter(eventsAdapter);
    }

    public void render_add_subevent_activity(View view) {
        Intent intent = new Intent(this, AddSubEventMainActivity.class);
        startActivity(intent);
        finish();
    }
}