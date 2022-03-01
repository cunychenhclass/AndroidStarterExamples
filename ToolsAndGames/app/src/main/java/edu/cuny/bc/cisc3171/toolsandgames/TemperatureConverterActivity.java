package edu.cuny.bc.cisc3171.toolsandgames;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;

import java.util.Locale;

public class TemperatureConverterActivity extends AppCompatActivity {
    public final static String TAG = "TEMP_CONVERTER";
    private int fromUnit = TemperatureModel.FAHRENHEIT;
    private int toUnit = TemperatureModel.CELSIUS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        final EditText textFromTemperature = findViewById(R.id.edittext_from_temperature);
        final TextView textToTemperature = findViewById(R.id.textview_to_temperature);
        final Button btnConvert = findViewById(R.id.btn_convert);
        final Button btnConfigure = findViewById(R.id.btn_configure);

        btnConvert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    double fromTemperature = Double.parseDouble(textFromTemperature.getText().toString());
                    double toTemperature = TemperatureModel.convert(fromTemperature, fromUnit, toUnit);
                    String unit;
                    if (toUnit == TemperatureModel.CELSIUS) {
                        unit = "C";
                    } else if (toUnit == TemperatureModel.KELVIN) {
                        unit = "K";
                    } else {
                        throw new IllegalStateException("Unsupported temperature unit " + toUnit);
                    }
                    Locale current = getResources().getConfiguration().locale;
                    textToTemperature.setText(String.format(current, "%.1f %s", toTemperature, unit));
                } catch (NumberFormatException e) {
                    Toast.makeText(TemperatureConverterActivity.this, "Expected a valid temperature", Toast.LENGTH_LONG).show();
                }
            }
        });

        final ActivityResultLauncher<Intent> configActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) return;
                        Bundle extras = data.getExtras();
                        fromUnit = extras.getInt("fromUnit");
                        toUnit = extras.getInt("toUnit");
                    }
                }
            });

        btnConfigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TemperatureConverterActivity.TAG, "launching TemperatureConverterConfig activity");
                Intent intent = new Intent(TemperatureConverterActivity.this, TemperatureConverterConfigActivity.class);
                configActivityResultLauncher.launch(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        final EditText textFromTemperature = findViewById(R.id.edittext_from_temperature);
        final TextView textToTemperature = findViewById(R.id.textview_to_temperature);

        if (toUnit == TemperatureModel.CELSIUS) {
            textToTemperature.setText("? C");
        } else if (toUnit == TemperatureModel.FAHRENHEIT) {
            textToTemperature.setText("? F");
        } else if (toUnit == TemperatureModel.KELVIN) {
            textToTemperature.setText("? K");
        } else {
            throw new IllegalStateException("Unsupported toUnit " + toUnit);
        }

        if (fromUnit == TemperatureModel.CELSIUS) {
            textFromTemperature.setHint("? C");
        } else if (fromUnit == TemperatureModel.FAHRENHEIT) {
            textFromTemperature.setHint("? F");
        } else if (fromUnit == TemperatureModel.KELVIN) {
            textFromTemperature.setHint("? K");
        } else {
            throw new IllegalStateException("Unsupported toUnit " + fromUnit);
        }
    }
}