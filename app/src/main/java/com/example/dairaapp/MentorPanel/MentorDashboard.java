package com.example.dairaapp.MentorPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dairaapp.OcPanel.AddMessageMainActivity;
import com.example.dairaapp.R;
import com.example.dairaapp.SplashActivity;
import com.google.android.material.navigation.NavigationView;

public class MentorDashboard extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_dashboard);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_subeventreg:
                Intent intent = new Intent(this, SubEventMainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_assignsubevents:
                Intent intent1 = new Intent(this, AssignOCSubeventMainActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_eventreg:
                Intent intent2 = new Intent(this, RegisterOCMainActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_viewreg:
                Intent intent4 = new Intent(this, DIsplayMRegistrationsMainActivity.class);
                startActivity(intent4);
                break;
            case R.id.nav_location:
                Intent intent3 = new Intent(this, AddVenueMainActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_sendupdate:
                Intent intent5 = new Intent(this, AddMessageMainActivity.class);
                startActivity(intent5);
                break;
            case R.id.nav_login:
                break;
            case R.id.nav_profile:
                break;
            case R.id.nav_logout:
                Intent i = new Intent(MentorDashboard.this, SplashActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
        }
        return true;
    }
}