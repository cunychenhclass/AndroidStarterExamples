package randomcolor.cisc3171.bc.cuny.edu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.util.Log;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "RandomColor";
//    public class MyClickListner implements View.OnClickListener {
//        @Override
//        public void onClick(View view) {
//
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textHelloWorld = findViewById(R.id.text_hello_world);
//        MyClickListner myListner = new MyClickListner();
        textHelloWorld.setOnClickListener(new View.OnClickListener() {
            private Random rng = new Random();
            private int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA};

            @Override
            public void onClick(View view) {
                int color = colors[rng.nextInt(colors.length)];
                Log.d(TAG, "color = " + color);
                textHelloWorld.setBackgroundColor(color);
            }
        });
    }
}