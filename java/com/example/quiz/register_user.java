package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class register_user extends AppCompatActivity implements View.OnClickListener {

    private TextView title, buttonR;
    private EditText fullname, editTextTextEmailAddress2, editTextTextPassword2;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        title = (TextView) findViewById(R.id.title);
        title.setOnClickListener(this);

        buttonR = (Button) findViewById(R.id.buttonR);
        buttonR.setOnClickListener(this);
        fullname = (EditText) findViewById(R.id.fullName);
        editTextTextEmailAddress2 = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        editTextTextPassword2 = (EditText) findViewById(R.id.editTextTextPassword2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title: startActivity(new Intent(this, MainActivity.class));
            break;
            case R.id.buttonR: registerUser();
        }

    }

    private void registerUser() {
        String fullnames = fullname.getText().toString().trim();
        String email = editTextTextEmailAddress2.getText().toString().trim();
        String password = editTextTextPassword2.getText().toString().trim();

        if(fullnames.isEmpty()){
            fullname.requestFocus();
            fullname.setError("Full name is required");
            return;
        }

        if(email.isEmpty()){
            editTextTextEmailAddress2.setError("E-mail is required");
            editTextTextEmailAddress2.requestFocus();
             return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextTextEmailAddress2.requestFocus();
            editTextTextEmailAddress2.setError("Please provide a valid e-mail!");
            return;
        }

        if (password.isEmpty()){
            editTextTextPassword2.requestFocus();
            editTextTextPassword2.setError("Password is required");
            return;
        }

        if (password.length()<6){
            editTextTextPassword2.setError("Min length is 6");
            editTextTextPassword2.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fullnames,email, password);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(register_user.this, "User registered",Toast.LENGTH_LONG).show();

                                    }
                                    else  Toast.makeText(register_user.this, "Failed to register",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        else Toast.makeText(register_user.this, "Failed to register",Toast.LENGTH_LONG).show();
                    }
                });


    }
}