package ainor.com.my.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;

    public void start (View view) {
        startButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        TextView sumTextView = (TextView) findViewById(R.id.sumTextView);

        Random rand = new Random();

        //create a random number between 0-21
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        //update sumTextView text
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
    }
}
