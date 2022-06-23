package com.example.dairaapp.AdminPanel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.Event;
import com.example.dairaapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class EventsMainActivity extends AppCompatActivity {

    FloatingActionButton add_rec;
    RecyclerView recyclerView;
    EventsAdapter eventsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_main);
        add_rec = findViewById(R.id.event_add_float_btn);
        recyclerView = findViewById(R.id.subeventsrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Event> options = new FirebaseRecyclerOptions.Builder<Event>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("events"), Event.class)
                .build();
        Log.d("TAG", new StringBuilder().append("INDEX").append(options.toString()).toString());
        eventsAdapter = new EventsAdapter(options);
        eventsAdapter.startListening();
        recyclerView.setAdapter(eventsAdapter);

//        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Event, EventViewHolder>(options) {
//            @Override
//            public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                // Create a new instance of the ViewHolder, in this case we are using a custom
//                Log.d("TAG", "ANDR AGYAAAA");
//                // layout called R.layout.message for each item
//                View view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.events_recycler_row, parent, false);
//
//                return new EventViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(EventViewHolder holder, int position, Event model) {
//                Log.d("TAG", "ANDR AGYAAAA");
//                holder.setName(model.getName());
//                holder.setDesc(model.getDescription());
//            }
//        };
//        adapter.startListening();
//        recyclerView.setAdapter(adapter);
    }

    public void render_add_activity(View view) {
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
        finish();
    }
}