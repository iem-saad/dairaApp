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

public class AddScoreBoardActivity extends AppCompatActivity {

    Spinner events_spinner;
    ArrayList<String> event_list;
    ArrayList<String> event_ids;
    ArrayAdapter<String> event_adapter;
    EditText player1, player2, player3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score_board);
        events_spinner = findViewById(R.id.subeventspinnerscore);
        player1 = findViewById(R.id.playerone);
        player2 = findViewById(R.id.player2);
        player3 = findViewById(R.id.player3);
        event_list = new ArrayList<String>();
        event_ids = new ArrayList<String>();
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

    public void moveBack(View view) {
        finish();
    }

    public void submit_scoreboard(View view) {
        Map<String, Object> map = new HashMap<>();
        map.put("player1", player1.getText().toString());
        map.put("player2", player2.getText().toString());
        map.put("player3", player3.getText().toString());
        CurrentUser currentUser = CurrentUser.getInstance();
        String oc_id;
        oc_id = "e8882c9702";
//        oc_id = currentUser.getUserName(this);
        map.put("oc_id",oc_id);
        map.put("event_name", event_list.get(events_spinner.getSelectedItemPosition()));
        map.put("event_id", event_ids.get(events_spinner.getSelectedItemPosition()));
        FirebaseDatabase.getInstance().getReference().child("scoreboards").child(event_ids.get(events_spinner.getSelectedItemPosition())).setValue(map).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent = new Intent(AddScoreBoardActivity.this, SubEventMainActivity.class);
                        startActivity(intent);
                        Toast.makeText(AddScoreBoardActivity.this, "Added SUB Event!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddScoreBoardActivity.this, "Error in Adding SUB Event!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}