package com.example.dairaapp.AdminPanel;

import android.app.ProgressDialog;
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
import java.util.UUID;

public class RegisterMentorMainActivity extends AppCompatActivity {

    public static final String EMAIL_VERIFICATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    EditText in_name, in_email, in_password;
    String email, password, name;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firestore;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mentor_main);
        in_name = findViewById(R.id.addmentorname);
        in_email = findViewById(R.id.addmentoremail);
        in_password = findViewById(R.id.addmentorpass);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
    }

    public void add_mentor(View view) {
        email = in_email.getText().toString();
        password = in_password.getText().toString();
        name = in_name.getText().toString();
        if(!email.matches(EMAIL_VERIFICATION)){
            in_email.setError("Please Enter Valid Email.");
        }
        else if(password.isEmpty() || password.length() < 6){
            in_password.setError("Please Set Valid Password with more than 6 length");
        }
        else{
            progressDialog.setMessage("Please be patient, registration is in progress!");
            progressDialog.setTitle("Registration in Progress!");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            UUID randomUUID = UUID.randomUUID();

            String username = randomUUID.toString().replaceAll("_", "");
            username = username.replaceAll("-", "");
            username = username.substring(0,10);

            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            map.put("email", email);
            map.put("password", password);
            map.put("username", username);
            map.put("role", "1");

            FirebaseDatabase.getInstance().getReference().child("mentors").child(username).setValue(map).
                    addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            in_name.setText("");
                            in_password.setText("");
                            in_email.setText("");
                            progressDialog.dismiss();
                            Toast.makeText(RegisterMentorMainActivity.this, "Added Mentor!", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterMentorMainActivity.this, "Error in Adding Mentor!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}