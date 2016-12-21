package ainor.com.my.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void generateQuestions () {
        Random rand = new Random();

        //create a random number between 0-21
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        //update sumTextView text
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        //generate answers and location of the answers

        // location between 0 and 3
        locationOfCorrectAnswer = rand.nextInt(4);

        // remove the answers from arraylist each time it generates new answer

        answers.clear();

        int incorrectAnswer;

        for (int i =0; i<4; i++) {

            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);

            } else {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(41);

                }
                answers.add(incorrectAnswer);
            }
        }

        // assign the answers to each buttons

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer (View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score ++;
            resultTextView.setText("Correct!");

        } else {
            resultTextView.setText("Wrong!");
        }

        numberOfQuestions ++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestions();
    }

    public void start (View view) {
        startButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        generateQuestions();

        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf (l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your score" + Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
            }
        }.start();

    }
}
