package com.example.dairaapp.AdminPanel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dairaapp.CurrentUser;
import com.example.dairaapp.R;
import com.google.android.material.navigation.NavigationView;

public class AdminDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        CurrentUser currentUser = CurrentUser.getInstance();
        Log.d("TAG", "AGYAAAA USER JEEEE0 " + currentUser.getUserName(this));
    }


    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_eventreg:
                Intent intent = new Intent(this, EventsMainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_assignevent:
                Intent intent1 = new Intent(this, AssignEventMainActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_mentorreg:
                Intent intent2 = new Intent(this, RegisterMentorMainActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_viewreg:
                Intent intent3 = new Intent(this, DisplayMentorsMainActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_login:
                break;
            case R.id.nav_profile:
                break;
            case R.id.nav_logout:
                break;
        }
        return true;
    }
}