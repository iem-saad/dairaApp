package com.example.dairaapp.Common.LoginSignup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dairaapp.AdminPanel.AdminDashboard;
import com.example.dairaapp.MentorPanel.MentorDashboard;
import com.example.dairaapp.OcPanel.OcDashboard;
import com.example.dairaapp.ParticipantPanel.ParticipantDashboard;
import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivityMain extends AppCompatActivity {

    EditText in_email, in_password;
    String email, password;
    ProgressDialog progressDialog;
    public static final String EMAIL_VERIFICATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        in_email = findViewById(R.id.editTextTextPersonName);
        in_password = findViewById(R.id.editTextTextPersonPassword);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
    }
    public void SignUpUser(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    public void loginUser(View view) {
        email = in_email.getText().toString();
        password = in_password.getText().toString();
        if(!email.matches(EMAIL_VERIFICATION)){
            in_email.setError("Please Enter Valid Email.");
        }
        else if(password.isEmpty() || password.length() < 6){
            in_password.setError("Please Enter Valid Password with more than 6 length");
        }
        else{
            progressDialog.setMessage("Please be patient, Seting up things for you!");
            progressDialog.setTitle("Login in Progress!");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        firebaseUser = firebaseAuth.getCurrentUser();
                        DocumentReference df = firestore.collection("Users").document(firebaseUser.getUid());
                        df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                sendUserToNextActicity(task.getResult().get("role").toString());
                                Toast.makeText(LoginActivityMain.this, "Congratulations! Signed in Successfully!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else{
                        Toast.makeText(LoginActivityMain.this, "Ops! Invalid Email or password! "+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActicity(String role) {
        Intent intent;
        if (role=="0"){
            intent = new Intent(LoginActivityMain.this, AdminDashboard.class);
        }
        else if(role=="1"){
            intent = new Intent(LoginActivityMain.this, MentorDashboard.class);
        }
        else if (role =="2"){
            intent = new Intent(LoginActivityMain.this, OcDashboard.class);
        }
        else{
            intent = new Intent(LoginActivityMain.this, ParticipantDashboard.class);
        }
//        Intent intent = new Intent(LoginActivityMain.this, SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
//        finish();
    }
}