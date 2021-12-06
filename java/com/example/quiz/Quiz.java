package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Quiz extends AppCompatActivity {

    private static final long timeinmilis = 60000;
    private CountDownTimer countdowntime;
    private boolean timerRunning;
    private long timeleft = timeinmilis;

    private TextView questionsnumber;
    private TextView questiontext;
    private TextView timer;
    private AppCompatButton option1, option2, option3, option4;
    private AppCompatButton nextbutton;

    private String selectedOptionByUser="";


    private List<questionList> questionList = new ArrayList<>();

    private int CurrentQuestionNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        final Button backbuttton = (Button) findViewById(R.id.backarrow);
        final TextView topictittle = (TextView) findViewById(R.id.topic);


        final String getSelectedTopic = getIntent().getStringExtra("selectedtopic");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("/https://quiz-78c50-default-rtdb.firebaseio.com");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.child(getSelectedTopic).getChildren()){
                    final String getQuestion = dataSnapshot.child("question").getValue(String.class);
                    final String getoption1 = dataSnapshot.child("option1").getValue(String.class);
                    final String getoption2 = dataSnapshot.child("option2").getValue(String.class);
                    final String getoption3 = dataSnapshot.child("option3").getValue(String.class);
                    final String getoption4 = dataSnapshot.child("option4").getValue(String.class);
                    final String getanswer = dataSnapshot.child("answer").getValue(String.class);
                    final String getuseranswer="";

                    questionList QuestionList = new questionList(getQuestion,getoption1,getoption2,getoption3,getoption4,getanswer,getuseranswer);
                    questionList.add(QuestionList);
                }
                questionsnumber.setText((CurrentQuestionNumber + 1) + "/" + questionList.size());
                questiontext.setText(questionList.get(0).getQuestion());
                option1.setText(questionList.get(0).getOption1());
                option2.setText(questionList.get(0).getOption2());
                option3.setText(questionList.get(0).getOption3());
                option3.setText(questionList.get(0).getOption3());
                option4.setText(questionList.get(0).getOption4());

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        timer= findViewById(R.id.timer);
        questionsnumber = findViewById(R.id.questionnumber);
        questiontext = findViewById(R.id.questiontext);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextbutton = findViewById(R.id.nextbutton);
        topictittle.setText(getSelectedTopic);

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning){
                    changeNextQuestion();

                }
                else {
                    startTimer();
                }
            }
        });






        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option1.getText().toString();

                    option1.setBackgroundResource(R.drawable.redback);
                    option1.setTextColor(Color.WHITE);

                    revalAnswer();
                     questionList.get(CurrentQuestionNumber).setUserSelectedAnswer(selectedOptionByUser);

                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option2.getText().toString();

                    option2.setBackgroundResource(R.drawable.redback);
                    option2.setTextColor(Color.WHITE);

                    revalAnswer();
                    questionList.get(CurrentQuestionNumber).setUserSelectedAnswer(selectedOptionByUser);

                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option3.getText().toString();

                    option3.setBackgroundResource(R.drawable.redback);
                    option3.setTextColor(Color.WHITE);

                    revalAnswer();
                    questionList.get(CurrentQuestionNumber).setUserSelectedAnswer(selectedOptionByUser);

                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = option4.getText().toString();

                    option4.setBackgroundResource(R.drawable.redback);
                    option4.setTextColor(Color.WHITE);

                    revalAnswer();
                        questionList.get(CurrentQuestionNumber).setUserSelectedAnswer(selectedOptionByUser);

                }
            }
        });







        backbuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdowntime.cancel();
                startActivity(new Intent(Quiz.this,Home.class));
            }
        });

    }
    private void changeNextQuestion(){
        CurrentQuestionNumber++;

        if ((CurrentQuestionNumber+1) == questionList.size()){
            nextbutton.setText("Submit");
            nextbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countdowntime.cancel();
                    Intent intent = new Intent(Quiz.this,quizresults.class);
                    intent.putExtra("Correctanswers",getcorrectanswer());
                    intent.putExtra("Incorrect",getincorrect());
                    startActivity(intent);
                }
            });

        }
        if (CurrentQuestionNumber < questionList.size()){
            selectedOptionByUser="";

            option1.setBackgroundResource(R.drawable.stroke);
            option1.setTextColor(Color.parseColor("#1F6888"));
            option2.setBackgroundResource(R.drawable.stroke);
            option2.setTextColor(Color.parseColor("#1F6888"));
            option3.setBackgroundResource(R.drawable.stroke);
            option3.setTextColor(Color.parseColor("#1F6888"));
            option4.setBackgroundResource(R.drawable.stroke);
            option4.setTextColor(Color.parseColor("#1F6888"));

            questionsnumber.setText((CurrentQuestionNumber + 1) + "/" + questionList.size());
            questiontext.setText(questionList.get(CurrentQuestionNumber).getQuestion());
            option1.setText(questionList.get(CurrentQuestionNumber).getOption1());
            option2.setText(questionList.get(CurrentQuestionNumber).getOption2());
            option3.setText(questionList.get(CurrentQuestionNumber).getOption3());
            option3.setText(questionList.get(CurrentQuestionNumber).getOption3());
            option4.setText(questionList.get(CurrentQuestionNumber).getOption4());

        }
        else {
            Intent intent = new Intent(Quiz.this, quizresults.class);
            intent.putExtra("Correctanswers",getcorrectanswer());
            intent.putExtra("Incorrect",getincorrect());
            startActivity(intent);

            finish();

        }
    }
    private void startTimer(){
        countdowntime = new CountDownTimer(timeleft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleft = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                timerRunning=false;

                Toast.makeText(Quiz.this,"Time's up",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Quiz.this, quizresults.class);
                intent.putExtra("Correct answers",getcorrectanswer());
                intent.putExtra("Incorrect",getincorrect());
                startActivity(intent);


            }
        }.start();
        timerRunning=true;
        nextbutton.setText("Next question");
    }
    private void updateCountDownText(){
        int minutes = (int) (timeleft/1000)/60;
        int seconds = (int) (timeleft/1000)%60;

        String timeleftFromat = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        timer.setText(timeleftFromat);

    }




        private int getcorrectanswer(){

        int CorrectAnswers=0;

        for (int i=0; i<questionList.size();i++){
            final String getUserSelectedAnswer = questionList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionList.get(i).getAnswer();

            if(getUserSelectedAnswer.equals(getAnswer)){
                CorrectAnswers++;
            }
        }
        return CorrectAnswers;

        }

    private int getincorrect(){

        int IncorrectAnswers=0;

        for (int i=0; i<questionList.size();i++){
            final String getUserSelectedAnswer = questionList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionList.get(i).getAnswer();

            if(!getUserSelectedAnswer.equals(getAnswer)){
                IncorrectAnswers++;
            }

        }
        return IncorrectAnswers;

    }

    private void revalAnswer(){

        final String getanswer = questionList.get(CurrentQuestionNumber).getAnswer();
        if (option1.getText().toString().equals(getanswer)){
            option1.setBackgroundResource(R.drawable.greenback);
            option1.setTextColor(Color.WHITE);
        }
        else if (option2.getText().toString().equals(getanswer)){
            option2.setBackgroundResource(R.drawable.greenback);
            option2.setTextColor(Color.WHITE);

        }
        else if (option3.getText().toString().equals(getanswer)){
            option3.setBackgroundResource(R.drawable.greenback);
            option3.setTextColor(Color.WHITE);

        }
        else if (option4.getText().toString().equals(getanswer)){
            option4.setBackgroundResource(R.drawable.greenback);
            option4.setTextColor(Color.WHITE);

        }


    }




}


