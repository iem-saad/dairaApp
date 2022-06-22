package com.example.dairaapp.AdminPanel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;
import com.example.dairaapp.User;
import com.example.dairaapp.UsersAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayMentorsMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UsersAdapter usersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_mentors_main);
        recyclerView = findViewById(R.id.mentorsrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("mentors"), User.class)
                .build();

        usersAdapter = new UsersAdapter(options);
        usersAdapter.startListening();
        recyclerView.setAdapter(usersAdapter);
    }
}