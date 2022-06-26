package com.example.dairaapp.Common.LoginSignup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dairaapp.AdminPanel.AdminDashboard;
import com.example.dairaapp.MentorPanel.MentorDashboard;
import com.example.dairaapp.OcPanel.OcDashboard;
import com.example.dairaapp.ParticipantPanel.ParticipantDashboard;
import com.example.dairaapp.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class ManagementLoginMainActivity extends AppCompatActivity {

    EditText in_email, in_password;
    String email, password;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firestore;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_login_main);
        in_email = findViewById(R.id.editTextTextPersonName);
        in_password = findViewById(R.id.editTextTextPersonPassword);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
        sharedPreferences = getSharedPreferences("auth", 0);
    }

    public void loginManagement(View view) {
        email = in_email.getText().toString();
        password = in_password.getText().toString();
        if(password.isEmpty() || password.length() < 6){
            in_password.setError("Please Enter Valid Password with more than 6 length");
        }
        else{
            progressDialog.setMessage("Please be patient, Seting up things for you!");
            progressDialog.setTitle("Login in Progress!");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            Log.d("TAG", "SNAPSHOT OUTSIDE");
            FirebaseDatabase.getInstance().getReference().child("mentors").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.d("TAG", "SNAPSHOT INSIDE");
                    if(snapshot.hasChild(email)){
                        Log.d("TAG", "SNAPSHOT INSIDE 2");
                        String saved_pass = snapshot.child(email).child("password").getValue().toString();
                        Log.d("TAG", "SAVED PASS "+ saved_pass);
                        Log.d("TAG", "PASS "+ password);
                        // issue in comparison....
                        if (saved_pass.equals(password)){
                            Log.d("TAG", "SNAPSHOT INSIDE 3");
                            SharedPreferences.Editor editor=  sharedPreferences.edit();
                            editor.clear();
                            editor.putString("usrename", email);
                            editor.commit();
                            progressDialog.dismiss();
                            sendUserToNextActicity("1");
                        }

                    }
                    else{
//                        progressDialog.dismiss();
                        Snackbar.make(view, "Invalid Username for mentors", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            FirebaseDatabase.getInstance().getReference().child("admin").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(email)){
                        String saved_pass = snapshot.child(email).child("password").getValue().toString();
                        if (saved_pass.equals(password)){
                            SharedPreferences.Editor editor=  sharedPreferences.edit();
                            editor.clear();
                            editor.putString("usrename", email);
                            editor.commit();
                            progressDialog.dismiss();
                            sendUserToNextActicity("0");
                        }

                    }
                    else{
                        progressDialog.dismiss();
                        Snackbar.make(view, "Invalid Username for admins", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            FirebaseDatabase.getInstance().getReference().child("ocs").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(email)){
                        String saved_pass = snapshot.child(email).child("password").getValue().toString();
                        if (saved_pass.equals(password)){
                            SharedPreferences.Editor editor=  sharedPreferences.edit();
                            editor.clear();
                            editor.putString("usrename", email);
                            editor.commit();
                            progressDialog.dismiss();
                            sendUserToNextActicity("2");
                        }

                    }
                    else{
                        progressDialog.dismiss();
                        Snackbar.make(view, "Invalid Username for admins", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void sendUserToNextActicity(String role) {
        Intent intent;
        if (role=="0"){
            intent = new Intent(ManagementLoginMainActivity.this, AdminDashboard.class);
        }
        else if(role=="1"){
            intent = new Intent(ManagementLoginMainActivity.this, MentorDashboard.class);
        }
        else if (role =="2"){
            intent = new Intent(ManagementLoginMainActivity.this, OcDashboard.class);
        }
        else{
            intent = new Intent(ManagementLoginMainActivity.this, ParticipantDashboard.class);
        }
//        Intent intent = new Intent(LoginActivityMain.this, SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
//        finish();
    }

    public void move_back(View view) {
        Intent intent = new Intent(this, OnBoarding.class);
        startActivity(intent);
        finish();
    }
}