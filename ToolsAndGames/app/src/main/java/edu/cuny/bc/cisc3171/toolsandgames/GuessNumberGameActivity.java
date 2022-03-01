package edu.cuny.bc.cisc3171.toolsandgames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

import java.util.Locale;

public class GuessNumberGameActivity extends AppCompatActivity {
    private GuessNumberGameModel game = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_number_game);

        final TextView textName = findViewById(R.id.text_guess_number_name);
        final TextView textLevel = findViewById(R.id.text_guess_number_level);
        final TextView textHint = findViewById(R.id.text_guess_number_hint);
        final EditText textNumberGuessed = findViewById(R.id.text_number_guessed);
        final Button btnConfirm = findViewById(R.id.btn_guess_number_confirm);
        final Button btnExit = findViewById(R.id.btn_guess_number_exit);

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        int level = extras.getInt("level");
        textName.setText(name);
        textLevel.setText(String.format(getString(R.string.level_level), level));

        game = new GuessNumberGameModel(level);
        Locale current = getResources().getConfiguration().locale;
        textHint.setText(String.format(current, "Enter a number between %d and %d (both inclusive)", game.getMinNum(), game.getMaxNum()));

        btnConfirm.setOnClickListener(view -> {
                int guessed = Integer.parseInt(textNumberGuessed.getText().toString());
                if (game.takeGuess(guessed) < 0) {
                    textHint.setText(getString(R.string.guess_too_small));
                } else if (game.takeGuess(guessed) > 0) {
                    textHint.setText(getString(R.string.guess_too_big));
                } else {
                    textHint.setText(getString(R.string.guessed_it));
                }
            });

        btnExit.setOnClickListener(view -> finish());
    }
}