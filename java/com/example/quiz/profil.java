package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profil extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Button logout;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        logout =(Button) findViewById(R.id.logoutbutton);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(profil.this, MainActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView emails = (TextView) findViewById(R.id.emailaddres);
        final TextView names = (TextView) findViewById(R.id.names);
        final TextView pont = (TextView) findViewById(R.id.pont);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    String fullname = userProfile.fullname;
                    String email = userProfile.email;
                     emails.setText(email);
                     names.setText(fullname);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profil.this,"Something not okay",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.homeitem:
                startActivity(new Intent(profil.this, Home.class));

                return true;
            case R.id.profilitem:

                return true;
        }
        return false;
    }
}