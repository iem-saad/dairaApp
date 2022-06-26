package com.example.dairaapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dairaapp.Common.LoginSignup.OnBoarding;

public class SplashActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    ImageView splashImage;
    TextView welcome, glad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        lottieAnimationView = findViewById(R.id.lottieonboard);
        splashImage = findViewById(R.id.splashimg);
        welcome = findViewById(R.id.welcomemsg);
        glad = findViewById(R.id.welcometext);
        lottieAnimationView.animate().translationY(2000).setDuration(7000).setStartDelay(4000);
        splashImage.animate().translationY(2000).setDuration(7000).setStartDelay(4000);
        welcome.animate().translationY(2000).setDuration(7000).setStartDelay(4000);
        glad.animate().translationY(2000).setDuration(7000).setStartDelay(4000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, OnBoarding.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}