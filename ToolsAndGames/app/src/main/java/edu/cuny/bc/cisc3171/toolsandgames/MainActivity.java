package edu.cuny.bc.cisc3171.toolsandgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnTemperatureConverter = findViewById(R.id.btn_temperature_converter);

        btnTemperatureConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TemperatureConverterActivity.class);
                startActivity(intent);
            }
        });

        final EditText textName = findViewById(R.id.text_user_name);
        final EditText textLevel = findViewById(R.id.text_level);
        final Button btnGuessNumber = findViewById(R.id.btn_guess_number);
        btnGuessNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GuessNumberGameActivity.class);
                intent.putExtra("name", textName.getText().toString());
                int level = Integer.parseInt(textLevel.getText().toString());
                if (!GuessNumberGameModel.isValidLevel(level)) {
                    Locale current = getResources().getConfiguration().locale;
                    Toast.makeText(MainActivity.this,
                            String.format(current, "Level expected between %d and %d", GuessNumberGameModel.getLowestLevel(), GuessNumberGameModel.getHighestLevel()),
                            Toast.LENGTH_LONG).show();
                    return;
                }
                intent.putExtra("level", level);
                startActivity(intent);
            }
        });


    }
}