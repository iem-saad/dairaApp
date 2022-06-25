package com.example.dairaapp.ParticipantPanel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterEventActivity extends AppCompatActivity {

    Spinner events_spinner;
    ArrayList<String> event_list;
    ArrayList<String> event_ids;
    ArrayList<String> mentor_mails;
    ArrayAdapter<String> event_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);
        Log.d("TAG", "USER EMAIL : "+
                FirebaseAuth.getInstance().getCurrentUser().getEmail());
        events_spinner = findViewById(R.id.regeventspinner);

        event_list = new ArrayList<String>();
        event_ids = new ArrayList<String>();
        mentor_mails = new ArrayList<String>();
        event_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, event_list);
        events_spinner.setAdapter(event_adapter);

        load_event_data();

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

    public void regester_event_(View view) {
        String selected_event;
        selected_event = event_ids.get(events_spinner.getSelectedItemPosition());

        Map<String, Object> map = new HashMap<>();
        map.put("sb_name", event_list.get(events_spinner.getSelectedItemPosition()));
        map.put("sb_id", selected_event);
        map.put("participant_id",  FirebaseAuth.getInstance().getCurrentUser().getUid());
        map.put("participant_mail",  FirebaseAuth.getInstance().getCurrentUser().getEmail());
        FirebaseDatabase.getInstance().getReference().child("reg_participants").push().setValue(map).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        FirebaseDatabase.getInstance().getReference().child("reg_participants " +  FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(map).
                                addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(RegisterEventActivity.this, ShowRegistrationMainActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(RegisterEventActivity.this, "Added Registration!", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterEventActivity.this, "Error in Adding Registration!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterEventActivity.this, "Error in Adding Registration!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}