package edu.cuny.bc.cisc3171.mathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edittextOp1 = findViewById(R.id.edittext_op_1);
        EditText edittextOp2 = findViewById(R.id.edittext_op_2);
        Button btnAverage = findViewById(R.id.button_average);
        btnAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edittextOp1.getText().toString().matches("")  ||
                        edittextOp2.getText().toString().matches("")) {
                    Snackbar.make(btnAverage, R.string.expect_two_inputs, Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}