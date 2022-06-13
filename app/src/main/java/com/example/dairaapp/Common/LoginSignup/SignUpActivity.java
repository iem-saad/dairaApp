package com.example.dairaapp.Common.LoginSignup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dairaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    public static final String EMAIL_VERIFICATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    ProgressDialog progressDialog;
    EditText in_name, in_email, in_password;
    String email, password, name;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        in_name = findViewById(R.id.signupname);
        in_email = findViewById(R.id.signupemail);
        in_password = findViewById(R.id.signuppass);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();

    }

    public void loginUser(View view) {
        Intent intent = new Intent(this, LoginActivityMain.class);
        startActivity(intent);
        finish();
    }

    public void registerUser(View view) {
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
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        firebaseUser = firebaseAuth.getCurrentUser();
                        DocumentReference df = firestore.collection("Users").document(firebaseUser.getUid());
                        Map<String,Object> userInfo = new HashMap<>();
                        // add remaining attributes here.
                        userInfo.put("name", name);
                        userInfo.put("role", 3); //3 means participant.
                        df.set(userInfo);
                        sendUserToNextActicity();
                        Toast.makeText(SignUpActivity.this, "Congratulations! Registration Completed!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SignUpActivity.this, "Ops! something went wrong! "+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void sendUserToNextActicity() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivityMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
//        finish();
    }
}