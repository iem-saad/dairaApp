package com.example.dairaapp.OcPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllocateSbEventVenueMainActivity extends AppCompatActivity {

    Spinner events_spinner;
    ArrayList<String> event_list;
    ArrayList<String> event_ids;
    ArrayList<String> mentor_mails;
    ArrayAdapter<String> event_adapter;

    Spinner mentor_spinner;
    ArrayList<String> mentor_list;
    ArrayAdapter<String> mentor_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate_sb_event_venue_main);
        events_spinner = findViewById(R.id.sbevvspinner);
        mentor_spinner = findViewById(R.id.venuespinner);

        event_list = new ArrayList<String>();
        event_ids = new ArrayList<String>();
        mentor_mails = new ArrayList<String>();
        event_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, event_list);
        events_spinner.setAdapter(event_adapter);

        mentor_list = new ArrayList<String>();
        mentor_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mentor_list);
        mentor_spinner.setAdapter(mentor_adapter);
        load_event_data();
        load_mentor_data();
    }
    public void load_event_data(){
        FirebaseDatabase.getInstance().getReference().child("subevents").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()){
                    event_list.add(data.child("name").getValue().toString());
                    event_ids.add(data.getKey());
                    event_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void load_mentor_data(){
        FirebaseDatabase.getInstance().getReference().child("venues").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()){
                    mentor_list.add(data.child("name").getValue().toString());
                    mentor_mails.add(data.getKey());;
                    mentor_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void assign_location(View view) {
        String selected_mentor;
        selected_mentor = mentor_mails.get(mentor_spinner.getSelectedItemPosition());
        String selected_event;
        selected_event = event_ids.get(events_spinner.getSelectedItemPosition());

        Map<String, Object> map = new HashMap<>();
        map.put("venue_name", mentor_list.get(mentor_spinner.getSelectedItemPosition()));
        map.put("venue_id", selected_mentor);
        map.put("sb_name", event_list.get(events_spinner.getSelectedItemPosition()));
        map.put("sb_id", selected_event);
        FirebaseDatabase.getInstance().getReference().child("sb_ev_venue").push().setValue(map).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent = new Intent(AllocateSbEventVenueMainActivity.this, EventVenuesMainActivity.class);
                        startActivity(intent);
                        Toast.makeText(AllocateSbEventVenueMainActivity.this, "Added Allocation!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AllocateSbEventVenueMainActivity.this, "Error in Adding Allocation!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}