package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class quizresults extends AppCompatActivity {

    private TextView congrat, correct, correctanser, incorrect, incorrectanswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizresults);

        congrat = (TextView) findViewById(R.id.congrat);
        correct = (TextView) findViewById(R.id.correct);
        correctanser = (TextView) findViewById(R.id.correctanser);
        incorrect = (TextView) findViewById(R.id.incorrect);
        incorrectanswer = (TextView) findViewById(R.id.incorrectanswer);

        final int getcorrectanswer = getIntent().getIntExtra("Correctanswers",0);
        final int getinccorect = getIntent().getIntExtra("Incorrect",0);

        correctanser.setText(String.valueOf(getcorrectanswer));
        incorrectanswer.setText(String.valueOf(getinccorect));


    }
}