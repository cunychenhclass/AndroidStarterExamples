package edu.cuny.bc.cisc3171.teachaverageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textFeedback = findViewById(R.id.text_feedback);
        Button buttonCheckAnswer = findViewById(R.id.button_check_answer);
        buttonCheckAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textFeedback.setText(getString(R.string.expect_inputs));
            }
        });
    }
}