package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private EditText email, password;
    private Button login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register= (TextView) findViewById(R.id.Register);
        register.setOnClickListener(this);

        login = (Button) findViewById(R.id.button);
        login.setOnClickListener(this);

        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password=(EditText) findViewById(R.id.editTextTextPassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Register:
                startActivity(new Intent(this, register_user.class));
                break;
            case R.id.button:
                userLogin();
                break;

    }
}

    private void userLogin() {
        String emails = email.getText().toString().trim();
        String passwords = password.getText().toString().trim();

        if (emails.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
            email.requestFocus();
            email.setError("Please provide a valid e-mail!");
            return;
        }

        if (passwords.isEmpty()){
            password.requestFocus();
            password.setError("Password is required");
            return;
        }

        if (passwords.length()<6){
            password.setError("Min length is 6");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(emails,passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){
                        startActivity(new Intent(MainActivity.this,Home.class));
                    }
                    else{
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this,"Check your e-mail to verify your account", Toast.LENGTH_LONG).show();
                    }

                }
                else Toast.makeText(MainActivity.this,"Email or password is not correct", Toast.LENGTH_LONG).show();
            }
        });




    }
}