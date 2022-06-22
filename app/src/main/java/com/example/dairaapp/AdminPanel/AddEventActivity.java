package com.example.dairaapp.AdminPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddEventActivity extends AppCompatActivity {

    EditText name, description;
    String in_name, in_description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        name = findViewById(R.id.addeventname);
        description = findViewById(R.id.addeventdescription);
    }

    public void submit_event(View view) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("description", description.getText().toString());
        map.put("mentor_id", "");
        FirebaseDatabase.getInstance().getReference().child("events").push().setValue(map).
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                name.setText("");
                                description.setText("");
                                Intent intent = new Intent(AddEventActivity.this, EventsMainActivity.class);
                                startActivity(intent);
                                Toast.makeText(AddEventActivity.this, "Added Event!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddEventActivity.this, "Error in Adding Event!", Toast.LENGTH_SHORT).show();
                            }
                        });

    }

    public void moveBack(View view) {
        finish();
    }
}