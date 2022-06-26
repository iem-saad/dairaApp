package com.example.dairaapp.OcPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dairaapp.CurrentUser;
import com.example.dairaapp.MentorPanel.MentorDashboard;
import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

// this activity is for mentor, added to OCPanel by mistake.
public class AddMessageMainActivity extends AppCompatActivity {
    EditText msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message_main);
        msg = findViewById(R.id.mentormsgadd);
    }

    public void submit_msg_(View view) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg.getText().toString());
        CurrentUser currentUser = CurrentUser.getInstance();
//        map.put("mentor_id", currentUser.getUserName(this));
        map.put("mentor_id", "0eadb2f221");
        FirebaseDatabase.getInstance().getReference().child("mentormsgs").child("0eadb2f221").setValue(map).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        msg.setText("");
                        Intent intent = new Intent(AddMessageMainActivity.this, MentorDashboard.class);
                        startActivity(intent);
                        Toast.makeText(AddMessageMainActivity.this, "Added Mentor Message!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddMessageMainActivity.this, "Error in Adding Mentor Message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void moveBack_(View view) {
        finish();
    }
}