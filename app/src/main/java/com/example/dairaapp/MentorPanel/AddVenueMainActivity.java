package com.example.dairaapp.MentorPanel;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddVenueMainActivity extends AppCompatActivity {

    EditText in_name;
    String name;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firestore;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_venue_main);
        in_name = findViewById(R.id.addvenuename);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
    }

    public void show_venues(View view) {
        Intent intent = new Intent(this, VenuesMainActivity.class);
        startActivity(intent);
    }

    public void add_venue(View view) {

        name = in_name.getText().toString();
        if(name.isEmpty()){
            in_name.setError("Please Set Valid Name");
        }
        else{
            progressDialog.setMessage("Please be patient, adding venue is in progress!");
            progressDialog.setTitle("Addition in Progress!");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            Map<String, Object> map = new HashMap<>();
            map.put("name", name);

            FirebaseDatabase.getInstance().getReference().child("venues").push().setValue(map).
                    addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            in_name.setText("");
                            progressDialog.dismiss();
                            Toast.makeText(AddVenueMainActivity.this, "Added Venue!", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddVenueMainActivity.this, "Error in Adding Venue!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}