package edu.cuny.bc.cisc3171.toolsandgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Button;

public class TemperatureConverterConfigActivity extends AppCompatActivity {
    private int fromUnit = TemperatureModel.FAHRENHEIT;
    private int toUnit = TemperatureModel.CELSIUS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter_config);

        final RadioButton radioFToC = findViewById(R.id.radio_fahrenheit_to_celsius);
        final RadioButton radioFToK = findViewById(R.id.radio_fahrenheit_to_kevin);
        final Button btnConfirm = findViewById(R.id.btn_confirm_config);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();

                if (view.getId() == R.id.radio_fahrenheit_to_celsius) {
                    if (checked) {
                        fromUnit = TemperatureModel.FAHRENHEIT;
                        toUnit = TemperatureModel.CELSIUS;
                    }
                } else if (view.getId() == R.id.radio_fahrenheit_to_kevin) {
                    if (checked) {
                        fromUnit = TemperatureModel.FAHRENHEIT;
                        toUnit = TemperatureModel.KELVIN;
                    }
                } else {
                    throw new IllegalStateException("Unsupported radio button with id " + view.getId());
                }
            }
        };
        radioFToC.setOnClickListener(listener);
        radioFToK.setOnClickListener(listener);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("fromUnit", fromUnit);
                intent.putExtra("toUnit", toUnit);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}