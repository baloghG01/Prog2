package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenu;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private  String selectedTopicName = "";
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        final LinearLayout history = findViewById(R.id.historylay);
        final LinearLayout muscic = findViewById(R.id.musiclay);
        final LinearLayout fizika = findViewById(R.id.fizikalay);
        final LinearLayout tech = findViewById(R.id.techlay);
        final Button startbutton = findViewById(R.id.startbutton);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "History";

                history.setBackgroundResource(R.drawable.stroke);
                muscic.setBackgroundResource(R.drawable.feherhatter);
                fizika.setBackgroundResource(R.drawable.feherhatter);
                tech.setBackgroundResource(R.drawable.feherhatter);
            }
        });

        muscic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "Music";

                history.setBackgroundResource(R.drawable.feherhatter);
                muscic.setBackgroundResource(R.drawable.stroke);
                fizika.setBackgroundResource(R.drawable.feherhatter);
                tech.setBackgroundResource(R.drawable.feherhatter);

            }
        });

        fizika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "Physics";

                history.setBackgroundResource(R.drawable.feherhatter);
                muscic.setBackgroundResource(R.drawable.feherhatter);
                fizika.setBackgroundResource(R.drawable.stroke);
                tech.setBackgroundResource(R.drawable.feherhatter);
            }
        });

        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "Tech";

                history.setBackgroundResource(R.drawable.feherhatter);
                muscic.setBackgroundResource(R.drawable.feherhatter);
                fizika.setBackgroundResource(R.drawable.feherhatter);
                tech.setBackgroundResource(R.drawable.stroke);
            }
        });

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTopicName.isEmpty()){
                    Toast.makeText(Home.this, "Please select a topic", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Home.this,Quiz.class);
                    intent.putExtra("selectedtopic", selectedTopicName);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.homeitem:

                return true;
            case R.id.profilitem:
                startActivity(new Intent(Home.this, profil.class));
                return true;
        }
        return false;
    }
}