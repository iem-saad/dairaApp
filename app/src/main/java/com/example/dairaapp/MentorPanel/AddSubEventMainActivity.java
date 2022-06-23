package com.example.dairaapp.MentorPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

public class AddSubEventMainActivity extends AppCompatActivity {

    Spinner events_spinner;
    ArrayList<String> event_list;
    ArrayList<String> event_ids;
    ArrayAdapter<String> event_adapter;
    EditText name, description, url;
    String in_name, in_description, in_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_event_main);
        events_spinner = findViewById(R.id.subeventspinner);
        name = findViewById(R.id.addsubeventname);
        description = findViewById(R.id.addsubeventdescription);
        url = findViewById(R.id.addsubeventimg);
        event_list = new ArrayList<String>();
        event_ids = new ArrayList<String>();
        event_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, event_list);
        events_spinner.setAdapter(event_adapter);
        load_event_data();
    }

    public void submit_subevent(View view) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("description", description.getText().toString());
        map.put("url", url.getText().toString());
        map.put("event_name", event_list.get(events_spinner.getSelectedItemPosition()));
        map.put("event_id", event_ids.get(events_spinner.getSelectedItemPosition()));
        FirebaseDatabase.getInstance().getReference().child("subevents").push().setValue(map).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        name.setText("");
                        description.setText("");
                        Intent intent = new Intent(AddSubEventMainActivity.this, SubEventMainActivity.class);
                        startActivity(intent);
                        Toast.makeText(AddSubEventMainActivity.this, "Added SUB Event!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddSubEventMainActivity.this, "Error in Adding SUB Event!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void moveBack(View view) {
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
}