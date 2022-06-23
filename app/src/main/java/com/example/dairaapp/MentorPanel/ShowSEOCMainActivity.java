package com.example.dairaapp.MentorPanel;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ShowSEOCMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SBOCAdapter participantAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_seocmain);

        recyclerView = findViewById(R.id.subeventocdis);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<OCEV> options = new FirebaseRecyclerOptions.Builder<OCEV>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("subeventoc"), OCEV.class)
                .build();
        Log.d("TAG", new StringBuilder().append("INDEX").append(options.toString()).toString());
        participantAdapter = new SBOCAdapter(options);
        participantAdapter.startListening();
        recyclerView.setAdapter(participantAdapter);
    }
}