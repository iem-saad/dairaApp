package com.example.dairaapp.ParticipantPanel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dairaapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ShowRegistrationMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RegistrationAdapter registrationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_registration_main);
        recyclerView = findViewById(R.id.reg_pr_spinner);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Registration> options = new FirebaseRecyclerOptions.Builder<Registration>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("reg_participants " +  FirebaseAuth.getInstance().getCurrentUser().getUid()), Registration.class)
                .build();

        registrationAdapter = new RegistrationAdapter(options);
        registrationAdapter.startListening();
        recyclerView.setAdapter(registrationAdapter);

    }
}