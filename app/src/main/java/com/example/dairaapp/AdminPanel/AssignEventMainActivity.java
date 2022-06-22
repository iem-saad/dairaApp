package com.example.dairaapp.AdminPanel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dairaapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AssignEventMainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_assign_event_main);
        events_spinner = findViewById(R.id.eventsspinner);
        mentor_spinner = findViewById(R.id.mentorsspinner);

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
        FirebaseDatabase.getInstance().getReference().child("events").addValueEventListener(new ValueEventListener() {
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
        FirebaseDatabase.getInstance().getReference().child("mentors").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()){
                    mentor_list.add(data.child("name").getValue().toString());
                    mentor_mails.add(data.child("email").getValue().toString());
                    mentor_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void assign_event(View view) {
        String selected_mentor;
        selected_mentor = mentor_mails.get(mentor_spinner.getSelectedItemPosition());
        String selected_event;
        selected_event = event_ids.get(events_spinner.getSelectedItemPosition());
        FirebaseDatabase.getInstance().getReference().child("events").child(selected_event).child("mentor_id").setValue(selected_mentor);
        Log.d("TAG", "EVENT is "+events_spinner.getSelectedItem().toString()+" index is: "+events_spinner.getSelectedItemPosition());
    }
}