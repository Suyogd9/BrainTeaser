package com.suyog.brainteaser;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    Button optionA;
    Button optionB;
    Button optionC;
    Button optionD;
    Button playAgain;
    TextView questions;
    TextView scoretv;
    TextView timer;
    TextView result;
    TextView heading;
    RelativeLayout relativeLayout;
    int locationOfAnswer;
    int score =0;
    int numberOfQuestion=0;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    public void playAgain(View view){
        score=0;
        numberOfQuestion=0;
        generateQuestion();
        result.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {

                timer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                result.setText("Your score " + Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
                result.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);

            }
        }.start();
    }


    public void generateQuestion(){
        Random rand= new Random();

        int a = rand.nextInt(21);
        int b= rand.nextInt(21);

        questions.setText(Integer.toString(a) + "*" +Integer.toString(b));
        locationOfAnswer = rand.nextInt(4);
        answers.clear();
        int i;
        int incorrectanswer;
        for (i=0;i<4;i++){

            if(i==locationOfAnswer){
                answers.add(a * b);
            }else{

                incorrectanswer=rand.nextInt(401);

                while (incorrectanswer ==(a*b))
                {

                    incorrectanswer=rand.nextInt(400);
                }

                answers.add(incorrectanswer);

            }


        }

        optionA.setText(Integer.toString(answers.get(0)));
        optionB.setText(Integer.toString(answers.get(1)));
        optionC.setText(Integer.toString(answers.get(2)));
        optionD.setText(Integer.toString(answers.get(3)));


    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfAnswer))){

            score++;
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT).show();
        }

        numberOfQuestion++;
        scoretv.setText(Integer.toString(score)+ "/" +Integer.toString(numberOfQuestion));
        generateQuestion();
    }


    public void startApp(View view){
        questions.setVisibility(View.VISIBLE);
        scoretv.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        optionA.setVisibility(View.VISIBLE);
        optionB.setVisibility(View.VISIBLE);
        optionC.setVisibility(View.VISIBLE);
        optionD.setVisibility(View.VISIBLE);
        heading.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // startApp(goButton =(Button)findViewById(R.id.goButton));

        goButton =(Button)findViewById(R.id.goButton);
        optionA =(Button)findViewById(R.id.optionA);
        optionB=(Button)findViewById(R.id.optionB);
        optionC =(Button)findViewById(R.id.optionC);
        optionD =(Button)findViewById(R.id.optionD);
        questions=(TextView)findViewById(R.id.questionTextView);
        scoretv=(TextView)findViewById(R.id.scoreTextView);
        timer=(TextView)findViewById(R.id.timerTextView);
        result=(TextView)findViewById(R.id.resultTextView);
        playAgain=(Button)findViewById(R.id.playAgainButton);
        relativeLayout=(RelativeLayout)findViewById(R.id.relativeLayout);
        heading=(TextView)findViewById(R.id.Heading);
        generateQuestion();
        playAgain(findViewById(R.id.playAgainButton));




    }
}
