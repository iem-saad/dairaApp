package com.example.dairaapp.Common.LoginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dairaapp.R;

public class OnBoarding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_1);
    }

    public void loginUser(View view) {
        Intent intent = new Intent(this, LoginActivityMain.class);
        startActivity(intent);
    }

    public void SignUpUser(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}