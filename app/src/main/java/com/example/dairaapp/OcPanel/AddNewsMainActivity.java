package com.example.dairaapp.OcPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dairaapp.CurrentUser;
import com.example.dairaapp.MentorPanel.SubEventMainActivity;
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

public class AddNewsMainActivity extends AppCompatActivity {

    Spinner events_spinner;
    ArrayList<String> event_list;
    ArrayList<String> event_ids;
    ArrayAdapter<String> event_adapter;
    EditText description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news_main);
        events_spinner = findViewById(R.id.newssbspinner);
        description = findViewById(R.id.addnewsdesc);
        event_list = new ArrayList<String>();
        event_ids = new ArrayList<String>();
        event_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, event_list);
        events_spinner.setAdapter(event_adapter);
        load_event_data();
    }

    public void submit_news(View view) {
        Map<String, Object> map = new HashMap<>();
        map.put("sb_ev_name", event_list.get(events_spinner.getSelectedItemPosition()));
        map.put("sb_ev_id", event_ids.get(events_spinner.getSelectedItemPosition()));
        map.put("msg", description.getText().toString());
        CurrentUser currentUser = CurrentUser.getInstance();
        map.put("adder_username", currentUser.getUserName(this));

        FirebaseDatabase.getInstance().getReference().child("ev_news").push().setValue(map).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        description.setText("");
                        Intent intent = new Intent(AddNewsMainActivity.this, NewsMainActivity.class);
                        startActivity(intent);
                        Toast.makeText(AddNewsMainActivity.this, "Added Event News!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddNewsMainActivity.this, "Error in Adding Event News!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void moveBack_(View view) {
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
}