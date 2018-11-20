package com.example.anish.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startbutton;

    ArrayList<Integer> answers=new ArrayList<>();

    int locationOfCorrectAnswer;

    TextView resultTextView;

    TextView pointsTextView;

    int score=0;

    int numberOfQuestions=0;

    TextView sumtextView;
    TextView timerTextView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    Button playAgain;

    ConstraintLayout gameConstraintLayout;
    public void playAgain(final View view)
    {
        score=0;
        numberOfQuestions=0;
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgain.setVisibility(view.INVISIBLE);
        generateQuestions();
        new CountDownTimer(30100,100)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");

            }

            @Override
            public void onFinish() {
                //pointsTextView.setEnabled(false);
                //timerTextView.setEnabled(false);
                playAgain.setVisibility(view.VISIBLE);
                resultTextView.setText("Your Score is "+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));

            }
        }.start();
    }

    public void generateQuestions()
    {
        Random random=new Random();

        int a=random.nextInt(21);
        int b=random.nextInt(21);

        sumtextView.setText(Integer.toString(a)+"+"+Integer.toString(b));

        locationOfCorrectAnswer=random.nextInt(4);

        int incorrectAnswer;

        answers.clear();

        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswer)
            {
                answers.add(a+b);
            }
            else
            {
                incorrectAnswer=random.nextInt(41);
                while (incorrectAnswer==a+b)
                {
                    incorrectAnswer=random.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        //The above for loop is just for adding correct answer only at one randomly generated position from
        // 0 to 3 and while other locations will get incorrect answers.

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

    }

    public void start(View view)
    {
        startbutton.setVisibility(view.INVISIBLE);
        gameConstraintLayout.setVisibility(view.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            score++;
            resultTextView.setText("Correct!");
        }
        else
        {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestions();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbutton=(Button) findViewById(R.id.startbutton);

        gameConstraintLayout=(ConstraintLayout) findViewById(R.id.gameContraintLayout);
        sumtextView=(TextView) findViewById(R.id.sumtextView);
         button1=(Button) findViewById(R.id.button1);
         button2=(Button) findViewById(R.id.button2);
         button3=(Button) findViewById(R.id.button3);
         button4=(Button) findViewById(R.id.button4);
        resultTextView=(TextView) findViewById(R.id.resultTextView);
        pointsTextView=(TextView) findViewById(R.id.scoretextView);
        timerTextView=(TextView) findViewById(R.id.timertextView);
        playAgain=(Button) findViewById(R.id.playAgainButton);
    }
}
